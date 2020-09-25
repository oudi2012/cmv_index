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
import com.mycmv.server.service.InfoElasticService;
import com.mycmv.server.service.book.BookInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * 书籍管理
 * @author a
 */
@RestController
@RequestMapping("elastic/book")
public class EsBookInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private InfoElasticService<BookInfoEs> bookInfoElastic;
    @Resource
    private BookInfoService bookInfoService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject pageList(@CurrentUser AbstractUser user, BookInfoEs bookItem, int pageIndex, int pageSize) {
        String url = "elastic/book/pageList";
        logger.info("用户 {} ，访问 {} , 参数：{}，{}，{}", user.getUserName(), url, JSON.toJSON(bookItem), pageIndex, pageSize);
        ResponseObject responseObject = new ResponseObject();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<BookInfoEs> pageInfo = bookInfoElastic.search(bookItem, pageable);
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
        Pageable pageable = PageRequest.of(1, 20);
        Page<BookInfoEs> bookInfoEsPage = bookInfoElastic.search(bookItem, pageable);
        if (CollectionUtils.isEmpty(bookInfoEsPage.getContent())) {
            logger.info("返回结果 list 条数：0");
            CommonUtils.executeSuccess(responseObject);
        } else {
            logger.info("返回结果 list 条数：{}", bookInfoEsPage.getContent().size());
            CommonUtils.executeSuccess(responseObject, bookInfoEsPage.getContent());
        }
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
        bookInfoElastic.insertOrUpdateOne(item);
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
        bookInfoElastic.insertOrUpdateOne(item);
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
        bookInfoElastic.insertBatch(bookListVo.getEsBookInfoList());
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

    @ResponseBody
    @GetMapping("initData")
    public ResponseObject initData() {
        //String url = "elastic/book/pageList";
        //logger.info("用户执行book elastic index 初始化操作 {} ，访问 {}", user.getUserName(), url);
        ResponseObject responseObject = new ResponseObject();
        AtomicBoolean has = new AtomicBoolean(true);
        AtomicInteger pageIndex = new AtomicInteger(1);
        while (has.get()) {
            BookInfo bookParam = new BookInfo();
            PageInfo<BookInfoEs> pageInfo = bookInfoService.pageListEs(bookParam, pageIndex.get(), 500);
            if (pageInfo.getPages() == 1) {
                logger.info("返回结果 list 条数：{}", pageInfo.getSize());
                if (!CollectionUtils.isEmpty(pageInfo.getList())) {
                    bookInfoElastic.insertBatch(pageInfo.getList());
                }
                has.set(false);
            } else {
                if (!CollectionUtils.isEmpty(pageInfo.getList())) {
                    bookInfoElastic.insertBatch(pageInfo.getList());
                } else {
                    has.set(false);
                }
            }
            pageIndex.getAndIncrement();
        }
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }
}
