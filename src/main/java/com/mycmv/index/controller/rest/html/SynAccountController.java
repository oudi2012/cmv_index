package com.mycmv.index.controller.rest.html;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.service.html.HtmlSubjectService;
import com.mycmv.server.utils.CommonUtils;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 同步数据
 * @author a
 */
@RestController
@RequestMapping("html")
public class SynAccountController {

    @Resource
    private HtmlSubjectService htmlSubjectService;

    @ResponseBody
    @GetMapping("income")
    public ResponseObject income() throws InterruptedException {
        ResponseObject resObj = new ResponseObject();
        int start = -191;
        int end = -192;
        for (int d = start; d > end; d--) {
            String result = htmlSubjectService.synAccountIncome(d);
            if (!result.contains("成功")) {
                break;
            }
        }
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
