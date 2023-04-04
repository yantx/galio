package com.galio.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 系统模块
 */
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SystemApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("系统模块启动成功！！！");
    }
}
