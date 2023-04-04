package com.galio.security.annotation;

import java.lang.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-02-26
 * @Description: 内部认证注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth
{
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}