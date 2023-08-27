package com.galio.http.config;

import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.helper.ResponseHelper;
import com.galio.core.model.BaseResponse;
import com.galio.core.utils.JsonUtils;
import com.galio.core.utils.ObjectUtil;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.*;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.reactive.function.client.support.ClientResponseWrapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

/**
 * @Author: galio
 * @Date: 2023-04-19 13:36:11
 * @Description: 远程调用地址配置
 */
@Slf4j
@AutoConfiguration
public class WebClientConfig {

    /**
     * 网关地址代理
     * @return
     */
    @Bean
    public WebClient webClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .filters(filter -> {
                    filter.add(headerFilter());
                    filter.add(modifyResponseBody());
                })
                .build();
        return client;
    }

    private ExchangeFilterFunction headerFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            ClientRequest.Builder request = ClientRequest.from(clientRequest);
            request.headers(httpHeaders -> httpHeaders.setBearerAuth(StpUtil.getTokenValue()));
            return Mono.just(request.build());
        });
    }
    private ExchangeFilterFunction modifyResponseBody() {

        // exchange可以理解为发送请求，返回一个ClientResponse的对象，它的默认实现类是DefaultClientResponse。
        // DefaultClientResponse类中有一个字段代表了实际的响应，就是ClientHttpResponse类型的字段response，它的默认实现类型是ReactorClientHttpResponse，自定义一个类来代替它。用一个自定义的ClientResponseWrapper类来代替DefaultClientResponse。
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse ->
                Mono.just(new GalioResponseBody(clientResponse))
        );
    }

    private class GalioResponseBody extends ClientResponseWrapper {

        /**
         * Create a new {@code ClientResponseWrapper} that wraps the given response.
         *
         * @param delegate the response to wrap
         */
        public GalioResponseBody(ClientResponse delegate) {
            super(delegate);

            Class<?> clazz = null;
            Field responseField = null;
            try {
                // 获取DefaultClientResponse类，它是包私有的
                clazz = Class.forName("org.springframework.web.reactive.function.client.DefaultClientResponse");
                // 获取response类私有字段，
                responseField = clazz.getDeclaredField("response");
                responseField.setAccessible(true);
                responseField.set(delegate, new ResponseDecorator((ClientHttpResponse) responseField.get(delegate)));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new CustomException(ResponseEnum.REMOTE_ANALYZE_RESPONSE_ERROR);
            }
        }

    }

    private class ResponseDecorator extends ClientHttpResponseDecorator {

        private final Flux<DataBuffer> flux;

        /**
         * Create a new {@code ClientResponseWrapper} that wraps the given response.
         *
         * @param delegate the response to wrap
         */
        public ResponseDecorator(ClientHttpResponse delegate) {
            super(delegate);
            this.flux = delegate.getBody();
        }

        @Override
        public Flux<DataBuffer> getBody() {
            return this.flux.map(dataBuffer -> {
                byte[] content = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(content);
                DataBufferUtils.release(dataBuffer);
                String bodyStr = new String(content, StandardCharsets.UTF_8);
                BaseResponse baseResponse = JsonUtils.toObject(bodyStr, BaseResponse.class);
                log.info("BaseResponse: {} ", JsonUtils.toString(baseResponse));
                if (baseResponse.getCode() != ResponseEnum.SUCCESS.getCode()) {
                    throw new CustomException(baseResponse.getCode(), baseResponse.getMsg());
                }
                byte[] bytes;
                if (ObjectUtil.isNull(baseResponse.getData())) {
                    return dataBuffer;
                }
                if (baseResponse.getData() instanceof Boolean) {
                    bytes = (Boolean) baseResponse.getData() ? "true".getBytes() : "false".getBytes();
                } else {
                    bytes = JsonUtils.toString(baseResponse.getData()).getBytes();
                }
                NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                buffer.write(bytes);
                return buffer;
            });
        }
    }

    /**
     * 此方法会报多次订阅的异常但是不影响处理结果 优化后可使用
     *
     * @return
     */
    private ExchangeFilterFunction modifyResponseBody1() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            clientResponse.bodyToMono(String.class).share();
            return clientResponse.bodyToMono(String.class).map(s -> {
                BaseResponse tmp = JsonUtils.toObject(s, BaseResponse.class);
                if (!ResponseHelper.isSuccess(tmp)) {
                    throw new CustomException(tmp.getCode(), tmp.getMsg());
                }
                if (ObjectUtil.isNull(tmp.getData())) {
                    return clientResponse.mutate().body(Flux.empty()).build();
                } else {
                    if (tmp.getData() instanceof Boolean) {
                        return clientResponse.mutate().body((Boolean) tmp.getData() ? "true" : "false").build();
                    } else {
                        String realBody = JsonUtils.toString(tmp.getData());
                        return clientResponse.mutate().body(realBody).build();
                    }
                }
            });
        });
    }

}