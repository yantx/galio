package com.galio.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-01-31
 * @Description: 数据权限组
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

    DataColumn[] value();

}
