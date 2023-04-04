package com.galio.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-01-31
 * @Description: 数据权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataColumn {

    /**
     * 占位符关键字
     */
    String[] key() default "orgId";

    /**
     * 占位符替换值
     */
    String[] value() default "org_id";

}
