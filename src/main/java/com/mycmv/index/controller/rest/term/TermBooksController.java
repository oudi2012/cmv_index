package com.mycmv.index.controller.rest.term;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.term.entry.TermBooks;
import com.mycmv.server.service.term.TermBooksService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 * 学期管理
 * @author a
 */
@RestController
@RequestMapping("term/books/")
public class TermBooksController {

    @Resource
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
