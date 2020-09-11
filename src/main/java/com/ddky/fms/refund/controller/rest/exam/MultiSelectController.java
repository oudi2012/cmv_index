package com.ddky.fms.refund.controller.rest.exam;


import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.exam.entry.MultiSelectQuestion;
import com.ddky.fms.refund.model.exam.vo.MultiSelectVo;
import com.ddky.fms.refund.service.exam.MultiSelectService;
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
 * multiSelect
 * @author a
 */
@RestController
@RequestMapping("exam/multiSelect/")
public class MultiSelectController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private SelectQuestionService examService;
    @Resource
    private SelectQuestionOptionService questionOptionService;
    @Resource
    private MultiSelectService multiSelectService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject list(@CurrentUser AbstractUser user, MultiSelectQuestion item, int pageIndex, int pageSize) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/multiSelect/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        PageInfo<MultiSelectQuestion> multiSelectQuestionPageInfo = multiSelectService.pageList(item, pageIndex, pageSize);
        if (!CollectionUtils.isEmpty(multiSelectQuestionPageInfo.getList())) {
            PageInfo<MultiSelectVo> multiSelectVoPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(multiSelectQuestionPageInfo, multiSelectVoPageInfo);
            multiSelectVoPageInfo.setList(multiSelectService.listToVo(multiSelectQuestionPageInfo.getList()));
            CommonUtils.executeSuccess(resObj, multiSelectVoPageInfo);
        } else {
            CommonUtils.executeSuccess(resObj, multiSelectQuestionPageInfo);
        }
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody MultiSelectVo item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/multiSelect/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        item.setCreatorId(user.getId());
        multiSelectService.insert(item);
        if (!CollectionUtils.isEmpty(item.getSelectQuestionVoList())) {
            item.getSelectQuestionVoList().forEach(selectItem -> {
                selectItem.setMultiId(item.getId());
                examService.insert(selectItem);
                if (!CollectionUtils.isEmpty(selectItem.getSelectQuestionOptionList())) {
                    selectItem.getSelectQuestionOptionList().forEach(optionItem -> {
                        optionItem.setQuestionId(selectItem.getId());
                        optionItem.setState(1);
                    });
                    questionOptionService.batchInsert(selectItem.getSelectQuestionOptionList());
                }
            });
        }
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, Integer examId) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/multiSelect/findById", examId);
        ResponseObject resObj = new ResponseObject();
        MultiSelectQuestion item = multiSelectService.findById(examId);
        if (!ObjectUtils.isEmpty(item)) {
            MultiSelectVo multiSelectVo = multiSelectService.itemToVo(item);
            CommonUtils.executeSuccess(resObj, multiSelectVo);
        } else {
            CommonUtils.executeSuccess(resObj, item);
        }
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody MultiSelectQuestion item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/multiSelect/edit", JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        multiSelectService.update(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/multiSelect/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        multiSelectService.delete(longIdListVo.getIds());
        examService.deleteByMultiIds(longIdListVo.getIds());
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
