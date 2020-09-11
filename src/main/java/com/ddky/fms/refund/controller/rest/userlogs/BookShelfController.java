package com.ddky.fms.refund.controller.rest.userlogs;


import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.userlogs.entry.BookShelf;
import com.ddky.fms.refund.service.userlog.BookShelfService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs BookShelf
 * @author a
 */
@RestController
@RequestMapping("userLogs/bookShelf")
public class BookShelfController {

    @Autowired
    private BookShelfService bookShelfService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(BookShelf termInfo, int pageIndex, int pageSize) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, bookShelfService.list(termInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody BookShelf termInfo) {
        ResponseObject resObj = new ResponseObject();
        bookShelfService.insert(termInfo);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        ResponseObject resObj = new ResponseObject();
        BookShelf termInfo = bookShelfService.findById(id);
        CommonUtils.executeSuccess(resObj, termInfo);
        return resObj;
    }

}
