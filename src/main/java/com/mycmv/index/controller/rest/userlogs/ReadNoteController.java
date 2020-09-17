package com.mycmv.index.controller.rest.userlogs;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.userlogs.entry.ReadNote;
import com.mycmv.server.service.userlog.ReadNoteService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs ReadNote
 * @author a
 */
@RestController
@RequestMapping("userLogs/readNote")
public class ReadNoteController {

    @Resource
    private ReadNoteService opLogService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(ReadNote termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, opLogService.list(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody ReadNote termInfo) {
        ResponseObject resObj = new ResponseObject();
        opLogService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        ReadNote termInfo = opLogService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
