package com.ddky.fms.refund.controller.rest.exam;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.exam.entry.SelectQuestionOption;
import com.ddky.fms.refund.service.exam.SelectQuestionOptionService;
import com.ddky.fms.refund.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/***
 * SelectQuestionOption
 * @author a
 */
@RestController
@RequestMapping("exam/selectQuestionOptionOption/")
public class SelectQuestionOptionController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private SelectQuestionOptionService examService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject list(@CurrentUser AbstractUser user, SelectQuestionOption item, int pageIndex, int pageSize) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestionOption/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, examService.pageList(item, pageIndex, pageSize));
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody SelectQuestionOption item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestionOption/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        examService.insert(item);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, Integer id) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestionOption/findById", id);
        ResponseObject resObj = new ResponseObject();
        SelectQuestionOption item = examService.findById(id);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody SelectQuestionOption item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestionOption/edit", JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        examService.update(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestionOption/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, examService.delete(longIdListVo.getIds()));
        return resObj;
    }

}
