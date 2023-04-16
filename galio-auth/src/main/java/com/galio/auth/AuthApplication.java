package com.galio.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @Author: galio
 * @Date: 2023-01-17
 * @Description: 鉴权认证服务启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AuthApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("-----  鉴权认证服务启动成功   -----");
    }

}