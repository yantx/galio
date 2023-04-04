package com.galio.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @Author: galio
 * @Date: 2023-03-19 21:01:55
 * @Description: 代码生成器
 */
@SpringBootApplication(scanBasePackages = "com.galio.*.handler")
public class GenApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GenApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("代码生成器启动成功!!!!");
    }
}
