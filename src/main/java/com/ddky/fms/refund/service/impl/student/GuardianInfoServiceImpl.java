package com.ddky.fms.refund.service.impl.student;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.mapper.students.GuardianInfoMapper;
import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import com.ddky.fms.refund.model.students.vo.GuardianStudentVo;
import com.ddky.fms.refund.service.student.GuardianInfoService;
import com.ddky.fms.refund.service.student.StudentInfoService;
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
 * 监护人
 * @author a
 */
@Service
public class GuardianInfoServiceImpl implements GuardianInfoService {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

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
    private GuardianInfoMapper guardianInfoMapper;
    @Resource
    private StudentInfoService studentInfoService;

    @Override
    public PageInfo<GuardianInfo> list(GuardianInfo guardianInfo, int pageIndex, int pageSize) {
        logger.info(LOG_LIST_PARAM, JSON.toJSONString(guardianInfo), pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" id desc");
        List<GuardianInfo> guardianInfos = guardianInfoMapper.list(guardianInfo);
        logger.info(LOG_LIST_RESULT, JSON.toJSONString(guardianInfos));
        if (CollectionUtils.isEmpty(guardianInfos)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(guardianInfos);
    }

    @Override
    public GuardianInfo findById(Long id) {
        logger.info(LOG_FIND_ID, id);
        GuardianInfo result = guardianInfoMapper.findById(id);
        logger.info(LOG_FIND_ID_RESULT, result);
        return result;
    }

    @Override
    public GuardianInfo findByPhone(String phone) {
        logger.info(LOG_FIND_PHONE, phone);
        GuardianInfo param = new GuardianInfo();
        param.setPhone(phone);
        GuardianInfo result = guardianInfoMapper.findOne(param);
        logger.info(LOG_FIND_PHONE_RESULT, result);
        return result;
    }

    @Override
    public GuardianInfo findByUserName(String userName) {
        logger.info(LOG_FIND_USERNAME, userName);
        GuardianInfo param = new GuardianInfo();
        param.setUserName(userName);
        GuardianInfo result = guardianInfoMapper.findOne(param);
        logger.info(LOG_FIND_USERNAME_RESULT, result);
        return result;
    }

    @Override
    public List<GuardianInfo> listByPhoneList(List<String> list) {
        logger.info("listByPhoneList param {}", JSON.toJSONString(list));
        List<GuardianInfo> result = guardianInfoMapper.listByPhoneList(list);
        logger.info("listByPhoneList result {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public List<GuardianInfo> listByUserNameList(List<String> list) {
        logger.info("listByUserNameList param {}", JSON.toJSONString(list));
        List<GuardianInfo> result = guardianInfoMapper.listByUserNameList(list);
        logger.info("listByUserNameList result {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void insert(GuardianInfo item) {
        logger.info(LOG_INSERT, JSON.toJSONString(item));
        if (!StringUtils.isEmpty(item.getPhone())) {
            GuardianInfo tmpItem = findByPhone(item.getPhone());
            if (!ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "手机号" + item.getPhone() + "已经注册");
            }
        }
        if (!StringUtils.isEmpty(item.getUserName())) {
            GuardianInfo tmpItem = findByUserName(item.getUserName());
            if (!ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "用户名" + item.getUserName() + "已经注册");
            }
        }
        item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        guardianInfoMapper.insert(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void batchInsert(List<GuardianInfo> list) {
        logger.info(LOG_INSERT_LIST, JSON.toJSONString(list));
        List<String> phoneList = list.stream().filter(a -> a.getPhone() != null).map(GuardianInfo::getPhone).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(phoneList)) {
            List<GuardianInfo> studentInfos = this.listByPhoneList(phoneList);
            if (!CollectionUtils.isEmpty(studentInfos)) {
                StringBuilder sbInfo = new StringBuilder();
                sbInfo.append("手机号：");
                studentInfos.forEach(item -> {
                    sbInfo.append(item.getPhone()).append(",");
                });
                sbInfo.deleteCharAt(sbInfo.length() - 1);
                sbInfo.append("已经注册");
                throw new BusinessException(501, sbInfo.toString());
            }
        }
        List<String> userNameList = list.stream().filter(a -> a.getUserName() != null).map(GuardianInfo::getUserName).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userNameList)) {
            List<GuardianInfo> studentInfos = this.listByUserNameList(userNameList);
            if (!CollectionUtils.isEmpty(studentInfos)) {
                StringBuilder sbInfo = new StringBuilder();
                sbInfo.append("用户名：");
                studentInfos.forEach(item -> {
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
        guardianInfoMapper.batchInsert(list);
    }

    @Override
    public int bindGuardianInfo(GuardianStudentVo guardianStudentVo) {
        GuardianInfo guardianInfo = guardianStudentVo.getGuardianInfo();
        StudentInfo studentInfo = guardianStudentVo.getStudentInfo();
        studentInfo.setGuardianId(guardianInfo.getId());
        if (studentInfo.getId() == null) {
            throw new BusinessException(501, "请选择要绑定的学生");
        }
        return studentInfoService.edit(studentInfo);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int edit(GuardianInfo item) {
        logger.info(LOG_EDIT, JSON.toJSONString(item));
        return guardianInfoMapper.edit(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int delete(List<Long> idList) {
        logger.info(LOG_REMOVE, JSON.toJSONString(idList));
        return guardianInfoMapper.delete(idList);
    }
}
