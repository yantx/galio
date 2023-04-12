package com.galio.doc.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: galio
 * @Date: 2023-04-03 08:07:49
 * @Description: mvc配置
 *
 */
@AutoConfiguration
public class SpringDocWebConfig implements WebMvcConfigurer {
    /**
     * spring.web.resources.add-mappings=false  为静态资源设置默认处理
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/","/static","/public","/META-INF/img/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
