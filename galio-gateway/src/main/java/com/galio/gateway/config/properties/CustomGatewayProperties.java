package com.galio.gateway.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: galio
 * @Date: 2023-03-01
 * @Description: 自定义gateway参数配置
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class CustomGatewayProperties {

    /**
     * 请求日志
     */
    private Boolean requestLog;

}
