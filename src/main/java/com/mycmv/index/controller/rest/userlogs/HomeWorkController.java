package com.mycmv.index.controller.rest.userlogs;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.userlogs.entry.HomeWork;
import com.mycmv.server.service.userlog.HomeWorkService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs HomeWork
 * @author a
 */
@RestController
@RequestMapping("userLogs/homeWork")
public class HomeWorkController {

    @Resource
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
