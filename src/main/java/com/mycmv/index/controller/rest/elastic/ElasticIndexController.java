package com.mycmv.index.controller.rest.elastic;

import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.books.elastic.BookInfoEs;
import com.mycmv.server.service.elastic.ElasticService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * Elastic
 * @author a
 */
@RestController
@RequestMapping("elastic/index")
public class ElasticIndexController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private ElasticService elasticService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("createBookInfoIndex")
    public ResponseObject createBookInfoIndex(@CurrentUser AbstractUser user) {
        logger.info("创建 BookInfo index,操作人:{}", user.getUserName());
        elasticService.createIndex(BookInfoEs.class);
        ResponseObject responseObject = new ResponseObject();
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

}
