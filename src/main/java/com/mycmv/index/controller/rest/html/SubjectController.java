package com.mycmv.index.controller.rest.html;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.books.entry.BookInfo;
import com.mycmv.server.service.html.HtmlSubjectService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/***
 * @author a
 */
@RestController("HtmlSubjectController")
@RequestMapping("html")
public class SubjectController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Resource
    private HtmlSubjectService htmlSubjectService;

    @ResponseBody
    @GetMapping("yuWen")
    public ResponseObject yuWen(String verType, String subType) {
        ResponseObject resObj = new ResponseObject();
        while (true) {
            logger.info("开始执行抓取科目信息,{},{}", verType, subType);
            List<? extends BookInfo> schoolInfoList = htmlSubjectService.listSubjectInfo(verType, subType);
            if (CollectionUtils.isEmpty(schoolInfoList)) {
                logger.info("抓取科目信息完成,0条");
                break;
            }
            logger.info("抓取科目信息完成, {}条", schoolInfoList.size());
        }
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
