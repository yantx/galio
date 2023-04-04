package com.galio.gateway.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-03-01
 * @Description: XSS跨站脚本配置
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties {
    /**
     * Xss开关
     */
    private Boolean enabled;

    /**
     * 排除路径
     */
    private List<String> excludeUrls = new ArrayList<>();

}
