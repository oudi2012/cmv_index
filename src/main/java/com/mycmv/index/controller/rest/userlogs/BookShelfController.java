package com.mycmv.index.controller.rest.userlogs;


import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.userlogs.entry.BookShelf;
import com.mycmv.server.service.userlog.BookShelfService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/***
 *  userLogs BookShelf
 * @author a
 */
@RestController
@RequestMapping("userLogs/bookShelf")
public class BookShelfController {

    @Resource
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
