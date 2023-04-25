package com.galio.system.config;

import com.galio.system.api.RemoteMemberClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @Author: galio
 * @Date: 2023-04-19 13:36:11
 * @Description: 远程调用地址配置
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RemoteMemberClient remoteMemberClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9211")
                .defaultHeader("Content-Type", "text/plain;charset=utf-8")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client))
                .build();
        return factory.createClient(RemoteMemberClient.class);
    }
}
