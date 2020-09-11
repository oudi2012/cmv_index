package com.ddky.fms.refund.controller.rest.register;

import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import com.ddky.fms.refund.model.students.vo.StudentInfoListVo;
import com.ddky.fms.refund.service.student.StudentInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * student
 * @author a
 */
@RestController
@RequestMapping("register/student")
public class StudentInfoRegister {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private StudentInfoService studentInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody StudentInfo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(studentInfo, "用户信息不能为空");
        studentInfoService.insert(studentInfo);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody StudentInfoListVo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(studentInfo, "用户信息不能为空");
        studentInfoService.batchInsert(studentInfo.getStudentInfoList());
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
