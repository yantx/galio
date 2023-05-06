package com.galio.http.config;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseResponse;
import com.galio.core.utils.JsonUtils;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.ClientResponseWrapper;
import reactor.core.publisher.Flux;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

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
        // DefaultClientResponse类中有一个字段代表了实际的响应，就是ClientHttpResponse类型的字段response，它的默认实现类型是ReactorClientHttpResponse，我们需要自定义一个类来代替它。然后我们用一个自定义的ClientResponseWrapper类来代替DefaultClientResponse。至于为什么需要同时修改两个类，那是因为使用bodyToMono或者bodyToFlux时在两个地方判断了Content-Type，一次使用了ClientResponse的方法，一次使用了ClientHttpResponse的方法。
        return ((request, next) -> next.exchange(request).map(ModifyResponseBody::new));
    }
    private class ModifyResponseBody extends ClientResponseWrapper {
        Headers headers;

        /**
         * Create a new {@code ClientResponseWrapper} that wraps the given response.
         *
         * @param delegate the response to wrap
         */
        public ModifyResponseBody(ClientResponse delegate) {
            super(delegate);
            // 如果原响应的content-type是text/javascript;charset=UTF-8，则修改之
            if (delegate.headers().contentType().map(t -> t.toString().equals("text/javascript;charset=UTF-8")).get()) {
                // 包装一下DefaultClientResponse的错误的header
                this.headers = new ClientResponseWrapper.HeadersWrapper(delegate.headers()) {
                    @Override
                    public Optional<MediaType> contentType() {
                        return Optional.of(MediaType.APPLICATION_JSON);
                    }
                };

            } else {
                this.headers = delegate.headers();
            }
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
                log.error(e.getMessage(),e);
                throw new CustomException(ResponseEnum.REMOTE_RESPONSE_ERROR);
            }
        }

        // 一定不要忘记重载这个方法
        @Override
        public Headers headers() {
            return this.headers;
        }
    }

    private class ResponseDecorator extends ClientHttpResponseDecorator {

        private final HttpHeaders headers;

        private final Flux<DataBuffer> flux;

        /**
         * Create a new {@code ClientResponseWrapper} that wraps the given response.
         *
         * @param delegate the response to wrap
         */
        public ResponseDecorator(ClientHttpResponse delegate) {
            super(delegate);
            this.flux = delegate.getBody();
            this.headers = new HttpHeaders(this.getDelegate().getHeaders());
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
        @Override
        public Flux<DataBuffer> getBody() {
            // 解析响应体将其中的data返回给消费者
            return flux.buffer().map(dataBuffers -> {
                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                DataBuffer join = dataBufferFactory.join(dataBuffers);
                byte[] content = new byte[join.readableByteCount()];
                join.read(content);
                DataBufferUtils.release(join);
                String bodyStr = new String(content, StandardCharsets.UTF_8);
                BaseResponse result = JsonUtils.toObject(bodyStr, BaseResponse.class);
                byte[] bytes = JsonUtils.toStringPretty(result.getData()).getBytes();

                NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                buffer.write(bytes);
                return buffer;
            });
        }
    }
}
