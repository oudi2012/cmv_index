package com.ddky.fms.refund.controller.rest.term;

import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.term.entry.TermBooks;
import com.ddky.fms.refund.service.term.TermBooksService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * 学期管理
 * @author a
 */
@RestController
@RequestMapping("term/books/")
public class TermBooksController {

    @Autowired
    private TermBooksService termBooksService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(TermBooks termBooks, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, termBooksService.pageList(termBooks, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody TermBooks termBooks) {
        ResponseObject resObj = new ResponseObject();
        termBooksService.insert(termBooks);
        CommonUtils.executeSuccess(resObj, termBooks);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        TermBooks termBooks = termBooksService.findById(id);
        CommonUtils.executeSuccess(resObj, termBooks);
        return resObj;
    }

}
