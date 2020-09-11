package com.ddky.fms.refund.controller.rest.admin;

import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import com.ddky.fms.refund.service.admin.AdminInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.google.common.base.Preconditions;
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
