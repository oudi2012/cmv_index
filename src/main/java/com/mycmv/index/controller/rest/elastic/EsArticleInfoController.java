package com.mycmv.index.controller.rest.elastic;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.article.elastic.ArticleInfoEs;
import com.mycmv.server.model.article.entry.ArticleInfo;
import com.mycmv.server.model.article.vo.EsArticleListVo;
import com.mycmv.server.model.base.vo.LongIdListVo;
import com.mycmv.server.service.InfoElasticService;
import com.mycmv.server.service.article.ArticleInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/***
 * 书籍管理
 * @author a
 */
@RestController
@RequestMapping("elastic/article")
public class EsArticleInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private InfoElasticService<ArticleInfoEs> articleInfoEsInfoElasticService;
    @Resource
    private ArticleInfoService articleInfoService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject pageList(@CurrentUser AbstractUser user, ArticleInfoEs item, int pageIndex, int pageSize) {
        String url = "elastic/article/pageList";
        logger.info("用户 {} ，访问 {} , 参数：{}，{}，{}", user.getUserName(), url, JSON.toJSON(item), pageIndex, pageSize);
        ResponseObject responseObject = new ResponseObject();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleInfoEs> pageInfo = articleInfoEsInfoElasticService.search(item, pageable);
        logger.info("返回结果 list 条数：{}", pageInfo.getSize());
        CommonUtils.executeSuccess(responseObject, pageInfo);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(@CurrentUser AbstractUser user, ArticleInfoEs item) {
        String url = "elastic/article/list";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        Pageable pageable = PageRequest.of(1, 20);
        Page<ArticleInfoEs> bookInfoEsPage = articleInfoEsInfoElasticService.search(item, pageable);
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
    public ResponseObject findById(@CurrentUser AbstractUser user, int id) {
        String url = "elastic/article/findById";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, id);
        ResponseObject responseObject = new ResponseObject();
        ArticleInfo tmp = articleInfoService.findById(id);
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
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody ArticleInfoEs item) {
        String url = "elastic/article/create";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        articleInfoEsInfoElasticService.insertOrUpdateOne(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody  ArticleInfoEs item) {
        String url = "elastic/article/create";
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), url, JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        articleInfoEsInfoElasticService.insertOrUpdateOne(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("batchCreate")
    public ResponseObject batchCreate(@CurrentUser AbstractUser user, @RequestBody EsArticleListVo articleListVo) {
        String url = "elastic/article/batchCreate";
        logger.info("用户 {} ，访问 {} , 数量：{}", user.getUserName(), url, articleListVo.getArticleInfoEsList().size());
        ResponseObject responseObject = new ResponseObject();
        articleInfoEsInfoElasticService.insertBatch(articleListVo.getArticleInfoEsList());
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 数量：{}", user.getUserName(), "elastic/article/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, articleInfoEsInfoElasticService.deleteByIds(longIdListVo.getIds()));
        return resObj;
    }

    @ResponseBody
    @GetMapping("initData")
    public ResponseObject initData() {
        //String url = "elastic/article/pageList";
        //logger.info("用户执行 article elastic index 初始化操作 {} ，访问 {}", user.getUserName(), url);
        ResponseObject responseObject = new ResponseObject();
        AtomicBoolean has = new AtomicBoolean(true);
        AtomicInteger pageIndex = new AtomicInteger(1);
        while (has.get()) {
            ArticleInfo articleInfo = new ArticleInfo();
            PageInfo<ArticleInfo> pageInfo = articleInfoService.pageList(articleInfo, pageIndex.get(), 500);
            List<ArticleInfoEs> articleInfoEsList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(pageInfo.getList())) {
                articleInfoEsList = pageInfo.getList().stream().map(item -> {
                    ArticleInfoEs articleInfoEs = new ArticleInfoEs();
                    BeanUtils.copyProperties(item, articleInfoEs);
                    return articleInfoEs;
                }).collect(Collectors.toList());
            }
            if (pageInfo.getPages() == 1 || pageInfo.getPages() == pageIndex.get()) {
                logger.info("返回结果 list 条数：{}", pageInfo.getSize());
                articleInfoEsInfoElasticService.insertBatch(articleInfoEsList);
                has.set(false);
            } else {
                if (!CollectionUtils.isEmpty(pageInfo.getList())) {
                    articleInfoEsInfoElasticService.insertBatch(articleInfoEsList);
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
