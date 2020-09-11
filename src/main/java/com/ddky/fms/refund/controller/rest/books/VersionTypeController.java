package com.ddky.fms.refund.controller.rest.books;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.model.base.vo.LongIdListVo;
import com.ddky.fms.refund.model.books.entry.VersionType;
import com.ddky.fms.refund.service.VersionTypeService;
import com.ddky.fms.refund.utils.CommonUtils;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/***
 * 版本类型
 * @author a
 */
@RestController
@RequestMapping("version")
public class VersionTypeController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private VersionTypeService versionTypeService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject pageList(@CurrentUser AbstractUser user, int pageIndex, int pageSize) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/pageList");
        ResponseObject responseObject = new ResponseObject();
        PageInfo<VersionType> pageInfo = versionTypeService.pageList(pageIndex, pageSize);
        logger.info("返回结果 list 条数：{}", pageInfo.getSize());
        CommonUtils.executeSuccess(responseObject, pageInfo);
        return responseObject;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(@CurrentUser AbstractUser user) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/list");
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, versionTypeService.list());
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody VersionType item) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/create");
        ResponseObject resObj = new ResponseObject();
        versionTypeService.insert(item);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, int id) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/findById");
        ResponseObject responseObject = new ResponseObject();
        VersionType tmp = versionTypeService.findById(id);
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
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody VersionType item) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/edit");
        ResponseObject resObj = new ResponseObject();
        versionTypeService.update(item);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} ", user.getUserName(), "version/remove");
        Preconditions.checkArgument(!ObjectUtils.isEmpty(longIdListVo), "删除参数不能为空");
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, versionTypeService.delete(longIdListVo.getIds()));
        return resObj;
    }
}
