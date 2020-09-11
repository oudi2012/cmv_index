package com.ddky.fms.refund.service.impl.student;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.constants.LogConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.mapper.students.StudentInfoMapper;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import com.ddky.fms.refund.service.student.StudentInfoService;
import com.ddky.fms.refund.utils.CmvDesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/***
 * StudentInfoService
 * @author a
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

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
    private StudentInfoMapper studentInfoMapper;

    @Override
    public PageInfo<StudentInfo> list(StudentInfo studentInfo, int pageIndex, int pageSize) {
        logger.info(LOG_LIST_PARAM, JSON.toJSONString(studentInfo), pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" id desc");
        List<StudentInfo> studentInfos = studentInfoMapper.list(studentInfo);
        logger.info(LOG_LIST_RESULT, JSON.toJSONString(studentInfos));
        if (CollectionUtils.isEmpty(studentInfos)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(studentInfos);
    }

    @Override
    public StudentInfo findById(Long id) {
        logger.info(LOG_FIND_ID, id);
        StudentInfo result = studentInfoMapper.findById(id);
        logger.info(LOG_FIND_ID_RESULT, result);
        return result;
    }

    @Override
    public StudentInfo findByPhone(String phone) {
        logger.info(LOG_FIND_PHONE, phone);
        StudentInfo param = new StudentInfo();
        param.setPhone(phone);
        StudentInfo result = studentInfoMapper.findOne(param);
        logger.info(LOG_FIND_PHONE_RESULT, result);
        return result;
    }

    @Override
    public StudentInfo findByUserName(String userName) {
        logger.info(LOG_FIND_USERNAME, userName);
        StudentInfo param = new StudentInfo();
        param.setUserName(userName);
        StudentInfo result = studentInfoMapper.findOne(param);
        logger.info(LOG_FIND_USERNAME_RESULT, result);
        return result;
    }

    @Override
    public List<StudentInfo> listByPhoneList(List<String> list) {
        logger.info("listByPhoneList param {}", JSON.toJSONString(list));
        List<StudentInfo> result = studentInfoMapper.listByPhoneList(list);
        logger.info("listByPhoneList result {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public List<StudentInfo> listByUserNameList(List<String> list) {
        logger.info("listByUserNameList param {}", JSON.toJSONString(list));
        List<StudentInfo> result = studentInfoMapper.listByUserNameList(list);
        logger.info("listByUserNameList result {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void insert(StudentInfo item) {
        logger.info(LOG_INSERT, JSON.toJSONString(item));
        if (!StringUtils.isEmpty(item.getPhone())) {
            StudentInfo tmpItem = findByPhone(item.getPhone());
            if (!ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "手机号" + item.getPhone() + "已经注册");
            }
        }
        if (!StringUtils.isEmpty(item.getUserName())) {
            StudentInfo tmpItem = findByUserName(item.getUserName());
            if (!ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "用户名" + item.getUserName() + "已经注册");
            }
        }
        item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        studentInfoMapper.insert(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void batchInsert(List<StudentInfo> list) {
        logger.info(LOG_INSERT_LIST, JSON.toJSONString(list));
        List<String> phoneList = list.stream().filter(a -> a.getPhone() != null).map(StudentInfo::getPhone).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(phoneList)) {
            List<StudentInfo> studentInfos = this.listByPhoneList(phoneList);
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
        List<String> userNameList = list.stream().filter(a -> a.getUserName() != null).map(StudentInfo::getUserName).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userNameList)) {
            List<StudentInfo> studentInfos = this.listByUserNameList(userNameList);
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
        studentInfoMapper.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int edit(StudentInfo item) {
        logger.info(LOG_EDIT, JSON.toJSONString(item));
        return studentInfoMapper.edit(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int delete(List<Long> idList) {
        logger.info(LOG_REMOVE, JSON.toJSONString(idList));
        return studentInfoMapper.delete(idList);
    }
}
