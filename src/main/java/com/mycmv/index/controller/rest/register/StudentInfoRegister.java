package com.mycmv.index.controller.rest.register;

import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.students.entry.StudentInfo;
import com.mycmv.server.model.students.vo.StudentInfoListVo;
import com.mycmv.server.service.student.StudentInfoService;
import com.mycmv.server.utils.CommonUtils;
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
