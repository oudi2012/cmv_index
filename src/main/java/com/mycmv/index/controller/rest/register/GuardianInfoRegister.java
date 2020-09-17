package com.mycmv.index.controller.rest.register;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.students.entry.GuardianInfo;
import com.mycmv.server.model.students.vo.GuardianInfoListVo;
import com.mycmv.server.service.student.GuardianInfoService;
import com.mycmv.server.utils.CommonUtils;
import com.google.common.base.Preconditions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * guardian
 * @author a
 */
@RestController
@RequestMapping("register/guardian")
public class GuardianInfoRegister {

    @Resource
    private GuardianInfoService guardianInfoService;


    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody GuardianInfo guardianInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(guardianInfo, "用户信息不能为空");
        guardianInfoService.insert(guardianInfo);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody GuardianInfoListVo guardianInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(guardianInfo, "用户信息不能为空");
        guardianInfoService.batchInsert(guardianInfo.getGuardianInfoList());
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


}
