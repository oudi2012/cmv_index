package com.ddky.fms.refund.service;

import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.base.vo.LoginVo;

import java.lang.reflect.Method;

/***
 * @author a
 */
public interface TokenService {

    /**
     * 获取token
     * @param loginVo loginVo
     * @return String
     */
    String getToken(LoginVo loginVo);

    /***
     * 根据token认证用户是否登录
     * @param method method
     * @param token token
     * @return AbstractUser
     */
    AbstractUser authUserByToken(Method method, String token);

    /***
     * 根据token获取用户信息
     * @param token token
     * @return AbstractUser
     */
    AbstractUser getUserByToken(String token);
}
