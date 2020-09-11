package com.ddky.fms.refund.controller.rest.exam;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.exam.entry.SelectQuestion;
import com.ddky.fms.refund.model.exam.vo.SelectQuestionVo;
import com.ddky.fms.refund.service.exam.SelectQuestionOptionService;
import com.ddky.fms.refund.service.exam.SelectQuestionService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/***
 * SelectQuestion
 * @author a
 */
@RestController
@RequestMapping("exam/selectQuestion/")
public class SelectQuestionController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private SelectQuestionService examService;
    @Resource
    private SelectQuestionOptionService questionOptionService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject list(@CurrentUser AbstractUser user, SelectQuestion item, int pageIndex, int pageSize) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestion/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        PageInfo<SelectQuestion> selectQuestionPageInfo = examService.pageList(item, pageIndex, pageSize);
        if (!CollectionUtils.isEmpty(selectQuestionPageInfo.getList())) {
            PageInfo<SelectQuestionVo> selectQuestionVoPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(selectQuestionPageInfo, selectQuestionVoPageInfo);
            selectQuestionVoPageInfo.setList(examService.listToVo(selectQuestionPageInfo.getList()));
            CommonUtils.executeSuccess(resObj, selectQuestionVoPageInfo);
        } else {
            CommonUtils.executeSuccess(resObj, selectQuestionPageInfo);
        }
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody SelectQuestionVo item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestion/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        item.setCreatorId(user.getId());
        if (item.getMultiId() == null) {
            item.setMultiId(0L);
        }
        examService.insert(item);
        if (!CollectionUtils.isEmpty(item.getSelectQuestionOptionList())) {
            item.getSelectQuestionOptionList().forEach(optionItem -> {
                optionItem.setQuestionId(item.getId());
                optionItem.setState(1);
            });
            questionOptionService.batchInsert(item.getSelectQuestionOptionList());
        }
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, Integer examId) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestion/findById", examId);
        ResponseObject resObj = new ResponseObject();
        SelectQuestion item = examService.findById(examId);
        if (!ObjectUtils.isEmpty(item)) {
            SelectQuestionVo selectQuestionVo = examService.itemToVo(item);
            CommonUtils.executeSuccess(resObj, selectQuestionVo);
        } else {
            CommonUtils.executeSuccess(resObj, item);
        }
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody SelectQuestionVo item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestion/edit", JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        examService.update(item);
        questionOptionService.deleteByQuestionId(item.getId());
        if (!CollectionUtils.isEmpty(item.getSelectQuestionOptionList())) {
            item.getSelectQuestionOptionList().forEach(optionItem -> {
                optionItem.setQuestionId(item.getId());
                optionItem.setState(1);
            });
            questionOptionService.batchInsert(item.getSelectQuestionOptionList());
        }
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/selectQuestion/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        examService.delete(longIdListVo.getIds());
        longIdListVo.getIds().forEach(id -> {
            questionOptionService.deleteByQuestionId(id);
        });
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
