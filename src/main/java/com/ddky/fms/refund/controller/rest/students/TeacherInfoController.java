package com.ddky.fms.refund.controller.rest.students;


import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.students.entry.TeacherInfo;
import com.ddky.fms.refund.model.students.vo.TeacherInfoVo;
import com.ddky.fms.refund.service.student.TeacherInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Collections;

import static com.ddky.fms.refund.constants.CmvConstants.DEFAULT_PWD;

/***
 * TeacherInfo
 * @author a
 */
@RestController
@RequestMapping("teacher")
public class TeacherInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);
    private static final String MODEL_NAME = "teacher";

    @Resource
    private TeacherInfoService teacherInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(TeacherInfo teacherInfo, int pageIndex, int pageSize) {
        logger.info("调用接口 {} => list", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        PageInfo<TeacherInfo> teacherInfoPageInfo = teacherInfoService.list(teacherInfo, pageIndex, pageSize);
        if (!CollectionUtils.isEmpty(teacherInfoPageInfo.getList())) {
            PageInfo<TeacherInfoVo> teacherInfoVoPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(teacherInfoPageInfo, teacherInfoVoPageInfo);
            teacherInfoVoPageInfo.setList(teacherInfoService.listToVo(teacherInfoPageInfo.getList()));
            CommonUtils.executeSuccess(resObj, teacherInfoVoPageInfo);
        } else {
            CommonUtils.executeSuccess(resObj, teacherInfoPageInfo);
        }
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Long id) {
        logger.info("调用接口 {} => findById", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, teacherInfoService.findVoById(id));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody TeacherInfo teacherInfo) {
        logger.info("调用接口 {} => create", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        teacherInfo.setPassWord(DEFAULT_PWD);
        teacherInfoService.insert(teacherInfo);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody TeacherInfo teacherInfo) {
        logger.info("调用接口 {} => edit", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, teacherInfoService.edit(teacherInfo));
        return resObj;
    }

    @ResponseBody
    @PostMapping("remove")
    public ResponseObject remove(@RequestBody LongIdListVo longIdListVo) {
        logger.info("调用接口 {} => remove, 参数：{}", MODEL_NAME, JSON.toJSONString(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, teacherInfoService.delete(longIdListVo.getIds()));
        return resObj;
    }

}
