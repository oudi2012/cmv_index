package com.ddky.fms.refund.controller.handle;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.configuration.CurrentUser;
import com.ddky.fms.refund.constants.CmvConstants;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.AbstractUser;
import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import com.ddky.fms.refund.service.TokenService;
import com.ddky.fms.refund.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/***
 * @author a
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        AbstractUser abstractUser = (AbstractUser)nativeWebRequest.getAttribute(CmvConstants.CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
        logger.info("获取currentUser value : {}", JSON.toJSONString(abstractUser));
        if (!ObjectUtils.isEmpty(abstractUser)) {
            return abstractUser;
        }
        throw new BusinessException(501, "token 不存在");
    }
}
