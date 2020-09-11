package com.ddky.fms.refund.controller.rest.userlogs;


import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.userlogs.entry.StudentWork;
import com.ddky.fms.refund.service.userlog.StudentWorkService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs StudentWork
 * @author a
 */
@RestController
@RequestMapping("userLogs/studentWork")
public class StudentWorkController {

    @Autowired
    private StudentWorkService opLogService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(StudentWork termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, opLogService.list(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody StudentWork termInfo) {
        ResponseObject resObj = new ResponseObject();
        opLogService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        StudentWork termInfo = opLogService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
