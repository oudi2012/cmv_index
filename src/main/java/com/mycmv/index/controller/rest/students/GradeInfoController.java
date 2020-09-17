package com.mycmv.index.controller.rest.students;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.students.entry.GradeInfo;
import com.mycmv.server.service.student.GradeService;
import com.mycmv.server.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/***
 * 年级
 * @author a
 */
@RestController
@RequestMapping("grade")
public class GradeInfoController {

    @Resource
    private GradeService gradeService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list() {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, gradeService.list());
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody GradeInfo gradeInfo) {
        ResponseObject resObj = new ResponseObject();
        gradeService.insert(gradeInfo);
        CommonUtils.executeSuccess(resObj, gradeInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer gradeId) {
        ResponseObject resObj = new ResponseObject();
        GradeInfo gradeInfo = gradeService.findById(gradeId);
        CommonUtils.executeSuccess(resObj, gradeInfo);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody GradeInfo gradeInfo) {
        ResponseObject resObj = new ResponseObject();
        gradeService.update(gradeInfo);
        CommonUtils.executeSuccess(resObj, gradeInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("remove")
    public ResponseObject remove(Integer gradeId) {
        ResponseObject resObj = new ResponseObject();
        gradeService.delete(gradeId);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
