package com.ddky.fms.refund.controller.rest.students;

import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.students.entry.ClassInfo;
import com.ddky.fms.refund.service.student.ClassInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/***
 * 班级
 * @author a
 */
@RestController
@RequestMapping("class")
public class ClassInfoController {

    @Resource
    private ClassInfoService classInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list() {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, classInfoService.list());
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody ClassInfo classInfo) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.insert(classInfo);
        CommonUtils.executeSuccess(resObj, classInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer gradeId) {
        ResponseObject resObj = new ResponseObject();
        ClassInfo classInfo = classInfoService.findById(gradeId);
        CommonUtils.executeSuccess(resObj, classInfo);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody ClassInfo gradeInfo) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.update(gradeInfo);
        CommonUtils.executeSuccess(resObj, gradeInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("remove")
    public ResponseObject remove(Integer id) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.delete(id);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
