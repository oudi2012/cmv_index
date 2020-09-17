package com.mycmv.index.configuration;

import com.mycmv.index.controller.handle.CurrentUserMethodArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/***
 * @author a
 */
@Configuration
public class CmvMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(CmvMvcConfig.class);

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        logger.info("添加当前用户的获取配置");
        resolvers.add(new CurrentUserMethodArgumentResolver());
    }
}
