package com.galio.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 密码配置
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "member.password")
public class PasswordProperties {

    /**
     * 密码最大错误次数
     */
    private Integer maxRetryCount;

    /**
     * 密码锁定时间（默认10分钟）
     */
    private Integer lockTime;

}
