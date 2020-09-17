package com.mycmv.index.controller.rest.exam;

import com.alibaba.fastjson.JSON;
import com.mycmv.server.configuration.CurrentUser;
import com.mycmv.server.configuration.UserLoginToken;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.AbstractUser;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.base.vo.LongIdListVo;
import com.mycmv.server.model.exam.entry.PaperProctor;
import com.mycmv.server.service.exam.PaperProctorService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/***
 * PaperProctor
 * @author a
 */
@RestController
@RequestMapping("exam/paperProctor/")
public class PaperProctorController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private PaperProctorService examService;

    @UserLoginToken
    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject list(@CurrentUser AbstractUser user, PaperProctor item, int pageIndex, int pageSize) {
        logger.info("用户 {} ，访问 {} , 参数：{}，{}，{}", user.getUserName(), "exam/paperProctor/pageList", JSON.toJSON(item), pageIndex, pageSize);
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, examService.pageList(item, pageIndex, pageSize));
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@CurrentUser AbstractUser user, @RequestBody PaperProctor item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/paperProctor/create", JSON.toJSON(item));
        ResponseObject resObj = new ResponseObject();
        examService.insert(item);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(@CurrentUser AbstractUser user, Integer id) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/paperProctor/findById", id);
        ResponseObject resObj = new ResponseObject();
        PaperProctor item = examService.findById(id);
        CommonUtils.executeSuccess(resObj, item);
        return resObj;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@CurrentUser AbstractUser user, @RequestBody PaperProctor item) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/paperProctor/edit", JSON.toJSON(item));
        ResponseObject responseObject = new ResponseObject();
        examService.update(item);
        CommonUtils.executeSuccess(responseObject);
        return responseObject;
    }

    @UserLoginToken
    @ResponseBody
    @PostMapping("remove")
    public ResponseObject delete(@CurrentUser AbstractUser user, @RequestBody LongIdListVo longIdListVo) {
        logger.info("用户 {} ，访问 {} , 参数：{}", user.getUserName(), "exam/paperProctor/remove", JSON.toJSON(longIdListVo));
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, examService.delete(longIdListVo.getIds()));
        return resObj;
    }

}
