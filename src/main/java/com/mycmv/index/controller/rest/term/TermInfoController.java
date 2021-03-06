package com.mycmv.index.controller.rest.term;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.term.entry.TermInfo;
import com.mycmv.server.service.term.TermInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * 学期管理
 * @author a
 */
@RestController
@RequestMapping("term")
public class TermInfoController {

    @Resource
    private TermInfoService termInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(TermInfo termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, termInfoService.pageList(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody TermInfo termInfo) {
        ResponseObject resObj = new ResponseObject();
        termInfoService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        TermInfo termInfo = termInfoService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
