package com.ddky.fms.refund.controller.rest.html;

import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.service.html.HtmlSubjectService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
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
