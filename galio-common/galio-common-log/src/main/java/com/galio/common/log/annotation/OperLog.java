package com.galio.common.log.annotation;

import com.galio.common.log.enums.OperTypeEnum;

import java.lang.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-05-30 22:09:47
 * @Description: 操作日志记录注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    /**
     * 模块
     */
    String operModul() default "";

   /**
     * 说明
     */
    String operDesc() default "";

    /**
     * 功能类别
     */
    OperTypeEnum operType() default OperTypeEnum.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
