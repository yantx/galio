package com.octopus.core.enums;

import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * @Author: octopus
 * @createTime: 2023-01-11
 * @Description: 脱敏策略，枚举类，针对不同的数据定制特定的策略
 */
@AllArgsConstructor
public enum SensitiveStrategy {

    /**
     * 用户名
     */
    USERNAME(s -> s.replaceAll("(\\S)\\S(\\S*)", "$1*$2")),

    /**
     * 密码
     */
    PASSWORD(s -> s.replaceAll("(?<=).", "*")),

    /**
     * 身份证
     */
    ID_CARD(s -> s.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2")),

    /**
     * 手机号
     */
    FIXED_PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),

    /**
     * 座机号
     */
    MOBILE_PHONE(s -> s.replaceAll("(?<=\\w{4})\\w(?=\\w{2})", "*")),

    /**
     * 邮箱
     */
    EMAIL(s -> s.replaceAll("(\\w+)\\w{3}@(\\w+)","$1***@$2")),

    /**
     * 银行卡号
     */
    BANKCARD(s -> s.replaceAll("(?<=\\w{4})\\w(?=\\w{4})","*")),

    /**
     * 地址
     */
    ADDRESS(s -> s.replaceAll("(\\S{3})\\S{2}(\\S*)\\S{2}", "$1****$2****"));

    //可自行添加其他脱敏策略
    private final Function<String, String> desensitizer;

    public Function<String, String> desensitizer() {
        return desensitizer;
    }

}
