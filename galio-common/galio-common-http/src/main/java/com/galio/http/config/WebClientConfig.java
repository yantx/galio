package com.galio.http.config;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
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
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.ClientResponseWrapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

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
                .baseUrl("http://localhost:9211")
                .filter(modifyResponseBody())
                .build();
        return client;
    }

    private ExchangeFilterFunction modifyResponseBody() {

        // exchange可以理解为发送请求，返回一个ClientResponse的对象，它的默认实现类是DefaultClientResponse。
        // DefaultClientResponse类中有一个字段代表了实际的响应，就是ClientHttpResponse类型的字段response，它的默认实现类型是ReactorClientHttpResponse，我们需要自定义一个类来代替它。然后我们用一个自定义的ClientResponseWrapper类来代替DefaultClientResponse。
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse ->
                Mono.just(new ModifyResponseBody(clientResponse))
        );
//        return ((request, next) -> {
//            log.info("Request: {} {}", request.method(), request.url());
//            return next.exchange(request).map(ModifyResponseBody::new);
//        });
    }

    private class ModifyResponseBody extends ClientResponseWrapper {

        /**
         * Create a new {@code ClientResponseWrapper} that wraps the given response.
         *
         * @param delegate the response to wrap
         */
        public ModifyResponseBody(ClientResponse delegate) {
            super(delegate);

            Class<?> clazz = null;
            Field responseField = null;
            Field delegateField = null;
            try {
                // 获取DefaultClientResponse类，它是包私有的
                clazz = Class.forName("org.springframework.web.reactive.function.client.DefaultClientResponse");
                // 获取response类私有字段，
                responseField = clazz.getDeclaredField("response");
                responseField.setAccessible(true);

                delegateField = ClientResponseWrapper.class.getDeclaredField("delegate");
                delegateField.setAccessible(true);
                responseField.set(delegate, new ResponseDecorator((ClientHttpResponse) responseField.get(delegate)));

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new CustomException(ResponseEnum.REMOTE_RESPONSE_ERROR);
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
            AtomicReference<BaseResponse> responseAtomicReference = new AtomicReference<>(new BaseResponse<>());
            this.flux.subscribe(dataBuffer -> {
                byte[] content = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(content);
                DataBufferUtils.release(dataBuffer);
                String bodyStr = new String(content, StandardCharsets.UTF_8);
                responseAtomicReference.set(JsonUtils.toObject(bodyStr, BaseResponse.class));
            });
            BaseResponse baseResponse = responseAtomicReference.get();
            log.info("BaseResponse: {} ", JsonUtils.toString(baseResponse));
            if (ObjectUtil.isNull(baseResponse.getData())) {
                return Flux.empty();
            } else {
                byte[] bytes = JsonUtils.toString(baseResponse.getData()).getBytes();
                NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                buffer.write(bytes);
                return Flux.just(buffer);
            }

        }
    }
}
