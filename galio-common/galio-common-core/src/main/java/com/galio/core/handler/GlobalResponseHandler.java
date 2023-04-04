package com.galio.core.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galio.core.annotation.NotControllerResponseAdvice;
import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.ResponseVo;
import com.galio.core.utils.JsonUtils;
import com.galio.core.utils.ObjectUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: galio
 * @Date: 2022-12-02
 * @Description: 统一响应封装 对返回值是ResponseVo类型，或者使用了NotControllerResponseAdvice注解的都不进行包装
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是ResponseVo类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !(returnType.getParameterType().isAssignableFrom(ResponseVo.class)
                || returnType.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(ResponseVo.createSuccessWithData(body));
            } catch (JsonProcessingException e) {
                throw new CustomException(ResponseCodeEnum.JSON_PROCESSING_EXCEPTION);
            }
        }
        // 否则直接包装成ResponseVo返回
        return ResponseVo.createSuccessWithData(body);
    }
}
