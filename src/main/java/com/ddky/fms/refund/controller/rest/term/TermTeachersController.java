package com.ddky.fms.refund.controller.rest.term;

import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.term.entry.TermTeachers;
import com.ddky.fms.refund.service.term.TermTeachersService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/***
 * 学期老师
 * @author a
 */
@RestController
@RequestMapping("term/teachers/")
public class TermTeachersController {

    @Autowired
    private TermTeachersService termTeachersService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(TermTeachers termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, termTeachersService.pageList(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody TermTeachers termInfo) {
        ResponseObject resObj = new ResponseObject();
        termTeachersService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        TermTeachers termInfo = termTeachersService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
