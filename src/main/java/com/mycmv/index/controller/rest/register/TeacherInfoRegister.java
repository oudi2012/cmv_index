package com.mycmv.index.controller.rest.register;


import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.students.entry.TeacherInfo;
import com.mycmv.server.model.students.vo.TeacherInfoListVo;
import com.mycmv.server.service.student.TeacherInfoService;
import com.mycmv.server.utils.CommonUtils;
import com.mycmv.server.utils.StringUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

/***
 * teacher
 * @author a
 */
@RestController
@RequestMapping("register/teacher")
public class TeacherInfoRegister {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.TEACH_LOG);

    @Resource
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
