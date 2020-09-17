package com.mycmv.index.controller.rest.userlogs;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.userlogs.entry.Collections;
import com.mycmv.server.service.userlog.CollectionsService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs Collections
 * @author a
 */
@RestController
@RequestMapping("userLogs/collections")
public class CollectionsController {

    @Resource
    private CollectionsService opLogService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(Collections termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, opLogService.list(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody Collections termInfo) {
        ResponseObject resObj = new ResponseObject();
        opLogService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        Collections termInfo = opLogService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
