package com.galio.satoken.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.util.SaResult;
import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    /**
     * 注册sa-token的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    /**
     * 校验校验 Token 身份凭证
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                // 静态资源,knife4j接口文档相关资源放行
                .addExclude("/actuator/**","/*.html","/webjars/**","/favicon.ico","/auth/**","/**/api-docs/**","/generate/**","/member/**","/remote/**")
                .setAuth(obj -> SaSameUtil.checkCurrentRequestToken())
                .setError(e -> {
                    log.error(e.getMessage(),e);
                    return ResponseVo.createFail(ResponseCodeEnum.NO_TOKEN);
                });
    }

}
