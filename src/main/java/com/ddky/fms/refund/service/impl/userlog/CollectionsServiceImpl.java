package com.ddky.fms.refund.service.impl.userlog;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.mapper.userlogs.CollectionsMapper;
import com.ddky.fms.refund.model.userlogs.entry.Collections;
import com.ddky.fms.refund.service.userlog.CollectionsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * CollectionsService
 * @author a
 */
@Service
public class CollectionsServiceImpl implements CollectionsService {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.OPE_LOG);

    private static final String LOG_LIST_PARAM = "list param {},{},{}";
    private static final String LOG_LIST_RESULT = "list result {}";
    private static final String LOG_FIND_ID = "findById param {}";
    private static final String LOG_FIND_ID_RESULT = "findById result {}";
    private static final String LOG_FIND_PHONE = "findByPhone param {}";
    private static final String LOG_FIND_PHONE_RESULT = "findByPhone result {}";
    private static final String LOG_FIND_USERNAME = "userName param {}";
    private static final String LOG_FIND_USERNAME_RESULT = "userName result {}";
    private static final String LOG_INSERT = "insert param {}";
    private static final String LOG_INSERT_LIST = "batchInsert param {}";
    private static final String LOG_EDIT = "edit param {}";
    private static final String LOG_REMOVE = "delete param {}";

    @Autowired
    private CollectionsMapper termMapper;

    @Override
    public PageInfo<Collections> list(Collections termInfo, int pageIndex, int pageSize) {
        logger.info(LOG_LIST_PARAM, JSON.toJSONString(termInfo), pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" coletId desc");
        List<Collections> termTeachersList = termMapper.list(termInfo);
        logger.info(LOG_LIST_RESULT, JSON.toJSONString(termTeachersList));
        if (CollectionUtils.isEmpty(termTeachersList)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(termTeachersList);
    }

    @Override
    public Collections findById(int id) {
        logger.info(LOG_FIND_ID, id);
        return termMapper.findById(id);
    }

    @Override
    public void insert(Collections termInfo) {
        logger.info(LOG_INSERT, JSON.toJSONString(termInfo));
        termMapper.insert(termInfo);
    }

    @Override
    public void batchInsert(List<Collections> list) {
        logger.info(LOG_INSERT, JSON.toJSONString(list));
        termMapper.batchInsert(list);
    }

}
