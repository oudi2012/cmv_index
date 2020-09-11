package com.ddky.fms.refund.service.impl.admin;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.mapper.admin.AdminInfoMapper;
import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import com.ddky.fms.refund.service.admin.AdminInfoService;
import com.ddky.fms.refund.utils.CmvDesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/***
 * AdminInfoService
 * @author a
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

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


    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Override
    public PageInfo<AdminInfo> list(AdminInfo adminInfo, int pageIndex, int pageSize) {
        logger.info(LOG_LIST_PARAM, JSON.toJSONString(adminInfo), pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" id desc");
        List<AdminInfo> adminInfos = adminInfoMapper.list(adminInfo);
        logger.info(LOG_LIST_RESULT, JSON.toJSONString(adminInfos));
        if (CollectionUtils.isEmpty(adminInfos)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(adminInfos);
    }

    @Override
    public AdminInfo findById(Long id) {
        logger.info(LOG_FIND_ID, id);
        AdminInfo result = adminInfoMapper.findById(id);
        logger.info(LOG_FIND_ID_RESULT, result);
        return result;
    }

    @Override
    public AdminInfo findByUserName(String userName) {
        logger.info(LOG_FIND_USERNAME, userName);
        AdminInfo param = new AdminInfo();
        param.setUserName(userName);
        AdminInfo result = adminInfoMapper.findOne(param);
        logger.info(LOG_FIND_USERNAME_RESULT, result);
        return result;
    }

    @Override
    public List<AdminInfo> listByUserNameList(List<String> list) {
        logger.info("listByUserNameList param {}", JSON.toJSONString(list));
        List<AdminInfo> result = adminInfoMapper.listByUserNameList(list);
        logger.info("listByUserNameList result {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void insert(AdminInfo item) {
        logger.info(LOG_INSERT, JSON.toJSONString(item));
        if (!StringUtils.isEmpty(item.getUserName())) {
            AdminInfo tmpItem = findByUserName(item.getUserName());
            if (!ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "用户名" + item.getUserName() + "已经注册");
            }
        }
        item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        adminInfoMapper.insert(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void batchInsert(List<AdminInfo> list) {
        logger.info(LOG_INSERT_LIST, JSON.toJSONString(list));
        List<String> userNameList = list.stream().filter(a -> a.getUserName() != null).map(AdminInfo::getUserName).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userNameList)) {
            List<AdminInfo> adminInfoList = this.listByUserNameList(userNameList);
            if (!CollectionUtils.isEmpty(adminInfoList)) {
                StringBuilder sbInfo = new StringBuilder();
                sbInfo.append("用户名：");
                adminInfoList.forEach(item -> {
                    sbInfo.append(item.getUserName()).append(",");
                });
                sbInfo.deleteCharAt(sbInfo.length() - 1);
                sbInfo.append("已经注册");
                throw new BusinessException(501, sbInfo.toString());
            }
        }
        list.forEach(item -> {
            item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        });
        adminInfoMapper.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int edit(AdminInfo item) {
        logger.info(LOG_EDIT, JSON.toJSONString(item));
        return adminInfoMapper.edit(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int delete(List<Long> idList) {
        logger.info(LOG_REMOVE, JSON.toJSONString(idList));
        return adminInfoMapper.delete(idList);
    }
}
