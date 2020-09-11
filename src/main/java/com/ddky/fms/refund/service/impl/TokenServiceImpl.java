package com.ddky.fms.refund.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.CmvConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import com.ddky.fms.refund.model.base.vo.LoginVo;
import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import com.ddky.fms.refund.model.students.entry.TeacherInfo;
import com.ddky.fms.refund.service.TokenService;
import com.ddky.fms.refund.service.admin.AdminInfoService;
import com.ddky.fms.refund.service.student.GuardianInfoService;
import com.ddky.fms.refund.service.student.StudentInfoService;
import com.ddky.fms.refund.service.student.TeacherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/***
 * @author a
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TeacherInfoService teacherInfoService;
    @Resource
    private GuardianInfoService guardianInfoService;
    @Resource
    private StudentInfoService studentInfoService;

    @Override
    public String getToken(LoginVo loginVo) {
        String token = loginVo.getId() + CmvConstants.CHAR_COLON + loginVo.getUserType();
        logger.info("生成 token => {}", token);
        return JWT.create().withAudience(token)
                .sign(Algorithm.HMAC256(loginVo.getPassWord()));
    }

    @Override
    public AbstractUser authUserByToken(Method method, String token) {
        UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
        if (userLoginToken.required()) {
            // 获取 token 中的 user id
            String tokenInfo;
            try {
                tokenInfo = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new BusinessException("认证失败");
            }
            logger.info("获取token的真是信息：{}", tokenInfo);
            AbstractUser abstractUser = getUserByToken(tokenInfo);
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(abstractUser.getPassWord())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new BusinessException("用户名和密码不对");
            }
            return abstractUser;
        }
        return null;
    }

    @Override
    public AbstractUser getUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException("token 不存在，请重新登录");
        }
        if (!token.contains(CmvConstants.CHAR_COLON)) {
            throw new BusinessException("token 格式不对，请重新登录");
        }
        String[] tokenSplit = token.split(CmvConstants.CHAR_COLON);
        String userType = tokenSplit[1];
        switch (userType) {
            case "admin":
                AdminInfo adminInfo = adminInfoService.findById(Long.parseLong(tokenSplit[0]));
                if (ObjectUtils.isEmpty(adminInfo)) {
                    throw new RuntimeException("AdminInfo => 用户不存在，请重新登录");
                }
                return adminInfo;
            case "teacher":
                TeacherInfo teacherInfo = teacherInfoService.findById(Long.parseLong(tokenSplit[0]));
                if (ObjectUtils.isEmpty(teacherInfo)) {
                    throw new RuntimeException("teacher => 用户不存在，请重新登录");
                }
                return teacherInfo;
            case "guardian":
                GuardianInfo guardianInfo = guardianInfoService.findById(Long.parseLong(tokenSplit[0]));
                if (ObjectUtils.isEmpty(guardianInfo)) {
                    throw new RuntimeException("guardian => 用户不存在，请重新登录");
                }
                return guardianInfo;
            case "student":
                StudentInfo studentInfo = studentInfoService.findById(Long.parseLong(tokenSplit[0]));
                if (ObjectUtils.isEmpty(studentInfo)) {
                    throw new RuntimeException("student => 用户不存在，请重新登录");
                }
                return studentInfo;
            default:
                throw new RuntimeException("用户不存在，请重新登录");
        }
    }
}
