package com.galio.core.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galio.core.annotation.NotControllerResponseAdvice;
import com.galio.core.constant.CommonConstants;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
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
@RestControllerAdvice(basePackages = {CommonConstants.BASE_PACKAGES})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否执行beforeBodyWrite方法， true为执行 false为不执行
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是ResponseVo类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !(returnType.getParameterType().isAssignableFrom(BaseResponse.class)
                || returnType.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }

    /**
     * 对 response 封装处理
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(BaseResponse.createSuccessWithData(body));
            } catch (JsonProcessingException e) {
                throw new CustomException(ResponseEnum.JSON_PROCESSING_EXCEPTION);
            }
        }
        // 否则直接包装成ResponseVo返回
        return BaseResponse.createSuccessWithData(body);
    }
}
