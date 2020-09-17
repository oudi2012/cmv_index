package com.mycmv.index.controller.rest.login;

import com.mycmv.server.configuration.PassToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.exception.BusinessException;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.admin.entry.AdminInfo;
import com.mycmv.server.model.base.config.UserTypeEnum;
import com.mycmv.server.model.base.vo.LoginVo;
import com.mycmv.server.service.admin.AdminInfoService;
import com.mycmv.server.service.TokenService;
import com.mycmv.server.utils.CmvDesUtils;
import com.mycmv.server.utils.CommonUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.mycmv.server.constants.CmvConstants.DEFAULT_PWD;

/***
 * @author a
 */
@RestController
@RequestMapping("login")
public class AdminLogin {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TokenService tokenService;

    @PassToken
    @PostMapping("admin")
    public ResponseObject login(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkArgument(!ObjectUtils.isEmpty(loginVo), "登录信息不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(loginVo.getUserName()), "登录名不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(loginVo.getPassWord()), "密码不能为空");
        if (!StringUtils.isEmpty(loginVo.getUserName())) {
            AdminInfo tmpItem = adminInfoService.findByUserName(loginVo.getUserName());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该用户名还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(loginVo.getPassWord());
            if (pwd != null && !pwd.equals(tmpItem.getPassWord())) {
                throw new BusinessException(501, "用户名和密码不对");
            }
            loginVo.setPassWord(pwd);
            loginVo.setId(tmpItem.getId());
            loginVo.setUserType(UserTypeEnum.USER_ADMIN.getCode());
            String token = tokenService.getToken(loginVo);
            logger.info("用户 {} 登录，生成的 token :{}", loginVo.getUserName(), token);
            loginVo.setPassWord(null);
            loginVo.setToken(token);
            CommonUtils.executeSuccess(resObj, loginVo);
        }
        return resObj;
    }

    @PassToken
    @PostMapping("reSetPassWd")
    public ResponseObject rePassWd(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(loginVo, "用户信息不能为空");
        if (!StringUtils.isEmpty(loginVo.getUserName())) {
            AdminInfo tmpItem = adminInfoService.findByUserName(loginVo.getUserName());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该用户名还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(DEFAULT_PWD);
            tmpItem.setPassWord(pwd);
            adminInfoService.edit(tmpItem);
            CommonUtils.executeSuccess(resObj, loginVo);
        } else {
            CommonUtils.executeFailure(resObj, "用户信息不能为空");
        }
        return resObj;
    }

}
