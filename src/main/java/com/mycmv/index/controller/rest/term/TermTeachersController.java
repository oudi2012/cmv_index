package com.mycmv.index.controller.rest.term;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.term.entry.TermTeachers;
import com.mycmv.server.service.term.TermTeachersService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/***
 * 学期老师
 * @author a
 */
@RestController
@RequestMapping("term/teachers/")
public class TermTeachersController {

    @Resource
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
