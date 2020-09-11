package com.ddky.fms.refund.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/***
 * cmv 文件配置
 * @author a
 */
@Data
@Configuration
public class CmvFileConfig {

    @Value("${download.savePath}")
    private String savePath;
}
