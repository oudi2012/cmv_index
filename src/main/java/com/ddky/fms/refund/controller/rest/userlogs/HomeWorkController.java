package com.ddky.fms.refund.controller.rest.userlogs;


import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.userlogs.entry.HomeWork;
import com.ddky.fms.refund.service.userlog.HomeWorkService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs HomeWork
 * @author a
 */
@RestController
@RequestMapping("userLogs/homeWork")
public class HomeWorkController {

    @Autowired
    private HomeWorkService opLogService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(HomeWork termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, opLogService.list(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody HomeWork termInfo) {
        ResponseObject resObj = new ResponseObject();
        opLogService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        HomeWork termInfo = opLogService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
