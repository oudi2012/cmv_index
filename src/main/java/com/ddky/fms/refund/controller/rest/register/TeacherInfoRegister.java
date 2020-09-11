package com.ddky.fms.refund.controller.rest.register;


import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.students.entry.TeacherInfo;
import com.ddky.fms.refund.model.students.vo.TeacherInfoListVo;
import com.ddky.fms.refund.service.student.TeacherInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.ddky.fms.refund.utils.StringUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/***
 * teacher
 * @author a
 */
@RestController
@RequestMapping("register/teacher")
public class TeacherInfoRegister {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.TEACH_LOG);

    @Autowired
    private TeacherInfoService teacherInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody TeacherInfo teacherInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(teacherInfo, "用户信息不能为空");
        if (!StringUtils.isEmpty(teacherInfo.getUserName())) {
            Preconditions.checkArgument(teacherInfo.getUserName().length() < 6, "用户名不能少于6位");
        }
        teacherInfoService.insert(teacherInfo);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody TeacherInfoListVo teacherInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(teacherInfo, "用户信息不能为空");
        teacherInfo.getTeacherInfoList().forEach(item -> {
            if (!StringUtils.isEmpty(item.getUserName())) {
                Preconditions.checkArgument(item.getUserName().length() < 6, "用户名不能少于6位");
            }
        });
        teacherInfoService.batchInsert(teacherInfo.getTeacherInfoList());
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
