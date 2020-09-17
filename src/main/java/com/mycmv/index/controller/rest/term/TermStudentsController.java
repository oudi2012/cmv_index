package com.mycmv.index.controller.rest.term;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.term.entry.TermStudents;
import com.mycmv.server.service.term.TermStudentsService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/***
 * 学期 students
 * @author a
 */
@RestController
@RequestMapping("term/students/")
public class TermStudentsController {

    @Resource
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
