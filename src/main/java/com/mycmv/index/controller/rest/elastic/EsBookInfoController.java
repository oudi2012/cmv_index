package com.mycmv.index.controller.rest.elastic;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.base.vo.LongIdListVo;
import com.mycmv.server.model.books.elastic.BookInfoEs;
import com.mycmv.server.model.books.entry.BookInfo;
import com.mycmv.server.model.books.vo.EsBookListVo;
import com.mycmv.server.service.book.BookInfoElastic;
import com.mycmv.server.service.book.BookInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/***
 * 书籍管理
 * @author a
 */
@RestController
@RequestMapping("elastic/book")
public class EsBookInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private BookInfoElastic bookInfoElastic;
    @Resource
    private BookInfoService bookInfoService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject pageList(@CurrentUser AbstractUser user, BookInfoEs bookItem, int pageIndex, int pageSize) {
        String url = "elastic/book/pageList";
        logger.info("用户 {} ，访问 {} , 参数：{}，{}，{}", user.getUserName(), url, JSON.toJSON(bookItem), pageIndex, pageSize);
        ResponseObject responseObject = new ResponseObject();
        PageInfo<BookInfoEs> pageInfo = bookInfoElastic.pageList(bookItem, pageIndex, pageSize);
        logger.info("返回结果 list 条数：{}", pageInfo.getSize());
        CommonUtils.executeSuccess(responseObject, pageInfo);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(@CurrentUser AbstractUser user, BookInfoEs bookItem) {
        String url = "elastic/book/list";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(bookItem));
        ResponseObject responseObject = new ResponseObject();
        List<BookInfoEs> list = bookInfoElastic.list(bookItem);
        if (CollectionUtils.isEmpty(list)) {
            logger.info("返回结果 list 条数：0");
        } else {
            logger.info("返回结果 list 条数：{}", list.size());
        }
        CommonUtils.executeSuccess(responseObject, list);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, int bookId) {
        String url = "elastic/book/findById";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, bookId);
        ResponseObject responseObject = new ResponseObject();
        BookInfo tmp = bookInfoService.findById(bookId);
        if (ObjectUtils.isEmpty(tmp)) {
            logger.info("返回结果 null");
        } else {
            logger.info("返回结果 {}", JSON.toJSONString(tmp));
        }
        CommonUtils.executeSuccess(responseObject, tmp);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody BookInfoEs item) {
        String url = "elastic/book/create";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        bookInfoElastic.insert(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody  BookInfoEs item) {
        String url = "elastic/book/create";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        bookInfoElastic.update(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("batchCreate")
    public ResponseObject batchCreate(@CurrentUser AbstractUser user, @RequestBody EsBookListVo bookListVo) {
        String url = "elastic/book/batchCreate";
        logger.info("用户 {} ，访问 {} , 数量：{}", user.getUserName(), url, bookListVo.getEsBookInfoList().size());
        ResponseObject responseObject = new ResponseObject();
        bookInfoElastic.batchInsert(bookListVo.getEsBookInfoList());
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 数量：{}", user.getUserName(), "elastic/book/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, bookInfoElastic.deleteByIds(longIdListVo.getIds()));
        return resObj;
    }
}
