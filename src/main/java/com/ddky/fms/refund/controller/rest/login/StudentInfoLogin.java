package com.ddky.fms.refund.controller.rest.login;

import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LoginVo;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import com.ddky.fms.refund.service.student.StudentInfoService;
import com.ddky.fms.refund.utils.CmvDesUtils;
import com.ddky.fms.refund.utils.CommonUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/***
 * @author a
 */
@RestController
@RequestMapping("login/student")
public class StudentInfoLogin {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Autowired
    private StudentInfoService studentInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject login(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(loginVo, "登录信息不能为空");
        Preconditions.checkNotNull(loginVo.getPassWord(), "密码不能为空");
        if (!StringUtils.isEmpty(loginVo.getPhone())) {
            StudentInfo tmpItem = studentInfoService.findByPhone(loginVo.getPhone());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该手机号还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(loginVo.getPassWord());
            if (pwd != null && !pwd.equals(tmpItem.getPassWord())) {
                throw new BusinessException(501, "用户名和密码不对");
            }
        }
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
