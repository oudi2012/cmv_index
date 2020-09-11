package com.ddky.fms.refund.controller.rest.books;

import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.books.entry.SubjectInfo;
import com.ddky.fms.refund.service.book.SubjectInfoService;
import com.ddky.fms.refund.utils.CommonUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/***
 * subject
 * @author a
 */
@RestController
@RequestMapping("subject")
public class SubjectController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);
    private static final String MODEL_NAME = "subject";

    @Resource
    private SubjectInfoService subjectInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list() {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, subjectInfoService.list());
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody SubjectInfo item) {
        logger.info("调用接口 {} => create", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        subjectInfoService.insert(item);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer id) {
        logger.info("调用接口 {} => findById", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, subjectInfoService.findById(id));
        return resObj;
    }

    @ResponseBody
    @GetMapping("findByName")
    public ResponseObject findByName(String name) {
        logger.info("调用接口 {} => findById", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, subjectInfoService.findByName(name));
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody SubjectInfo item) {
        logger.info("调用接口 {} => edit", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        subjectInfoService.update(item);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("remove")
    public ResponseObject remove(@RequestBody LongIdListVo longIdListVo) {
        Preconditions.checkArgument(!ObjectUtils.isEmpty(longIdListVo), "删除参数不能为空");
        logger.info("调用接口 {} => remove", MODEL_NAME);
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, subjectInfoService.delete(longIdListVo.getIds()));
        return resObj;
    }
}
