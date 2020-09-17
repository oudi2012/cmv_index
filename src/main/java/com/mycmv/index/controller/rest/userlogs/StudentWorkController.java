package com.mycmv.index.controller.rest.userlogs;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.userlogs.entry.StudentWork;
import com.mycmv.server.service.userlog.StudentWorkService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs StudentWork
 * @author a
 */
@RestController
@RequestMapping("userLogs/studentWork")
public class StudentWorkController {

    @Resource
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
