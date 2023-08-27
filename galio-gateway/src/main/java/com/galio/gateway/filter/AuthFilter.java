package com.galio.gateway.filter;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
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
            .addExclude("/favicon.ico", "/actuator/**")
            // 鉴权方法：每次访问进入
            .setAuth(obj -> {
                SaRouter.match("/**")
                    .notMatch(ignoreWhite.getWhites())
                    .check(r -> {
                        StpUtil.checkLogin();
                    });
                // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//                SaRouter.match("/**", "/user/doLogin", r -> StpUtil.checkLogin());

                // 权限认证 -- 不同模块, 校验不同权限
//                SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//                SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//                SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//                SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
            }).setError(e -> {
                    log.error(e.getMessage(),e);
                    return SaResult.error(e.getMessage());
                });
    }
}
