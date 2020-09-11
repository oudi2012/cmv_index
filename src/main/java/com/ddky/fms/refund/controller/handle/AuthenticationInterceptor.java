package com.ddky.fms.refund.controller.handle;

import com.ddky.fms.refund.configuration.PassToken;
import com.ddky.fms.refund.configuration.UserLoginToken;
import com.ddky.fms.refund.constants.CmvConstants;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.ddky.fms.refund.constants.CmvConstants.TOKEN;

/***
 * 此处的注解的识别还可以用另一种方式来实现 ApplicationListener
 * @author a
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Resource
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader(TOKEN);
        logger.info("从 http 请求头中取出 token : {}", token);
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有 passToken 注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            AbstractUser abstractUser = tokenService.authUserByToken(method, token);
            if (!ObjectUtils.isEmpty(abstractUser)) {
                request.setAttribute(CmvConstants.CURRENT_USER, abstractUser);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
