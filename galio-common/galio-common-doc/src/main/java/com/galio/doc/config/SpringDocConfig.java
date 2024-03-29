package com.galio.doc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: galio
 * @Date: 2023-03-22 07:37:38
 * @Description: soring doc 配置
 */
@AutoConfiguration
public class SpringDocConfig {

    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Restful Galio API")
                        .description("Galio Detail API")
                        .version("v0.0.1")
                        .contact(new Contact().name("yantxd@qq.com"))
                        .license(new License().name("MIT ").url("https://github.com/yantx/galio")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Wiki Documentation")
                        .url("https://springdoc.org/v2"));
    }
}
