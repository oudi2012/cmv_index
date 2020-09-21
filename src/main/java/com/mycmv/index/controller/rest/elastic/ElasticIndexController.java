package com.mycmv.index.controller.rest.elastic;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.books.entry.BookInfo;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/***
 * Elastic
 * @author a
 */
@RestController
@RequestMapping("elastic/index")
public class ElasticIndexController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @UserLoginToken
    @ResponseBody
    @GetMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, BookInfo bookItem, int pageIndex, int pageSize) {
        String url = "book/pageList";
        logger.info("用户 {} ，访问 {} , 参数：{}，{}，{}", user.getUserName(), url, JSON.toJSON(bookItem), pageIndex, pageSize);
        ResponseObject responseObject = new ResponseObject();
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

}
