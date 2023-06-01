package com.galio.system.config;

import com.galio.core.utils.SpringUtils;
import com.galio.system.api.MemberExchange;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import com.galio.http.config.WebClientConfig;

/**
 * @Author: galio
 * @Date: 2023-04-19 13:36:11
 * @Description: 远程调用地址配置
 */
@Configuration
@AutoConfigureAfter(WebClientConfig.class)
public class RestClientConfig {
    @Bean
    public MemberExchange remoteMemberClient() {
        WebClient webClient = SpringUtils.getBean(WebClient.class);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(MemberExchange.class);
    }
}
