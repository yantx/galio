package com.galio.http.config;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseResponse;
import com.galio.core.utils.JsonUtils;
import com.galio.core.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: galio
 * @Date: 2023-04-19 13:36:11
 * @Description: 远程调用地址配置
 */
@Slf4j
@AutoConfiguration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .filter(modifyResponseBody())
                .build();
        return client;
    }

    private ExchangeFilterFunction modifyResponseBody() {

        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    String body = clientResponse.bodyToMono(String.class).block();
                    BaseResponse tmp = JsonUtils.toObject(body, BaseResponse.class);
                    if (tmp.getCode() != ResponseEnum.SUCCESS.getCode()) {
                        throw new CustomException(tmp.getCode(), tmp.getMsg());
                    }
                    if (ObjectUtil.isNull(tmp.getData())) {
                        return Mono.just(clientResponse.mutate().body(Flux.empty()).build());
                    } else {
                        if (tmp.getData() instanceof Boolean) {
                            return Mono.just(clientResponse.mutate().body((Boolean) tmp.getData() ? "true" : "false").build());
                        }
                        String realBody = JsonUtils.toString(tmp.getData());
                        return Mono.just(clientResponse.mutate().body(realBody).build());
                    }
                }
        );
    }
}
