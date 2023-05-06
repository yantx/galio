package com.galio.gateway.filter;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.model.BaseResponse;
import com.galio.gateway.config.properties.IgnoreWhiteProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: galio
 * @Date: 2023-03-01
 * @Description: [Sa-Token 权限认证] 拦截器
 */
@Slf4j
@AutoConfiguration
public class AuthFilter {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter(IgnoreWhiteProperties ignoreWhite) {
        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")
            .addExclude("/favicon.ico", "/actuator/**","/remote/**")
            // 鉴权方法：每次访问进入
            .setAuth(obj -> {
                // 登录校验 -- 拦截所有路由
                SaRouter.match("/**")
                    .notMatch(ignoreWhite.getWhites())
                    .check(r -> {
                        // 检查是否登录 是否有token
                        StpUtil.checkLogin();

                        // 有效率影响 用于临时测试
                         if (log.isDebugEnabled()) {
                             log.debug("剩余有效时间: {}", StpUtil.getTokenTimeout());
                             log.debug("临时有效时间: {}", StpUtil.getTokenActivityTimeout());
                         }
                    });
            }).setError(e -> {
                    log.error(e.getMessage(),e);
                    return BaseResponse.createFail(ResponseEnum.NO_TOKEN);
                });
    }
}
