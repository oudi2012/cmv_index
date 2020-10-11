package com.mycmv.index.controller.rest.banner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mycmv.server.constants.LogConstants;
import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.banner.entry.Banner;
import com.mycmv.server.model.banner.vo.BannerVo;
import com.mycmv.server.service.banner.BannerInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * banner
 * @author a
 */
@RestController
@RequestMapping("banner")
public class BannerInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.SYS_LOG);

    @Resource
    private BannerInfoService bannerInfoService;

    @ResponseBody
    @GetMapping("pageList")
    public ResponseObject pageList(Banner item, int pageIndex, int pageSize) {
        String url = "banner/pageList";
        logger.info("访问 {} , 参数：{}，{}，{}", url, JSON.toJSON(item), pageIndex, pageSize);
        ResponseObject responseObject = new ResponseObject();
        PageInfo<Banner> pageInfo = bannerInfoService.pageList(item, pageIndex, pageSize);
        logger.info("返回结果 list 条数：{}", pageInfo.getSize());
        CommonUtils.executeSuccess(responseObject, pageInfo);
        return responseObject;
    }


    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(Banner bookItem) {
        String url = "banner/list";
        logger.info("访问 {} , 参数：{}", url, JSON.toJSON(bookItem));
        ResponseObject responseObject = new ResponseObject();
        List<Banner> list = bannerInfoService.list(bookItem);
        if (CollectionUtils.isEmpty(list)) {
            logger.info("返回结果 list 条数：0");
        } else {
            logger.info("返回结果 list 条数：{}", list.size());
        }
        CommonUtils.executeSuccess(responseObject, list);
        return responseObject;
    }
}
