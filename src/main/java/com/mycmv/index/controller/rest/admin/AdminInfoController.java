package com.mycmv.index.controller.rest.admin;

import com.google.common.base.Preconditions;
import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.exception.BusinessException;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.admin.entry.AdminInfo;
import com.mycmv.server.service.admin.AdminInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @author a
 */
@RestController
@RequestMapping("admin")
public class AdminInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private AdminInfoService adminInfoService;

    @UserLoginToken
    @GetMapping("userInfo")
    public ResponseObject userInfo(@CurrentUser AbstractUser user) {
        ResponseObject resObj = new ResponseObject();
        AdminInfo tmpItem = adminInfoService.findById(user.getId());
        if (ObjectUtils.isEmpty(tmpItem)) {
            throw new BusinessException(502, "该用户不存在");
        }
        CommonUtils.executeSuccess(resObj, tmpItem);
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody AdminInfo adminInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(adminInfo, "管理员信息不能为空");
        Preconditions.checkNotNull(adminInfo.getUserName(), "用户名不能为空");
        Preconditions.checkNotNull(adminInfo.getPassWord(), "密码不能为空");
        if (adminInfo.getAreaId() == null) {
            adminInfo.setAreaId(0);
        }
        adminInfoService.insert(adminInfo);
        CommonUtils.executeSuccess(resObj, adminInfo);
        return resObj;
    }

}
