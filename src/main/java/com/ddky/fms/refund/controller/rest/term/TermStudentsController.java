package com.ddky.fms.refund.controller.rest.term;

import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.term.entry.TermStudents;
import com.ddky.fms.refund.service.term.TermStudentsService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/***
 * 学期 students
 * @author a
 */
@RestController
@RequestMapping("term/students/")
public class TermStudentsController {

    @Autowired
    private TermStudentsService termStudentsService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(TermStudents termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, termStudentsService.pageList(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody TermStudents termInfo) {
        ResponseObject resObj = new ResponseObject();
        termStudentsService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        TermStudents termInfo = termStudentsService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
