package com.galio.satoken.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: Sa-Token 配置
 */
@Slf4j
@AutoConfiguration
public class SaTokenConfiguration implements WebMvcConfigurer {

    /**
     * 注入Jwt实现 simple模式
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

}
