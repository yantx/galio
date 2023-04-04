package com.galio.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @Author: galio
 * @Date: 2023-01-17
 * @Description: 获取i18n资源文件提示语
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageUtils {

    private static final MessageSource MESSAGE_SOURCE = SpringUtils.getBean(MessageSource.class);

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param key 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String key, Object... args) {
        try {
            return MESSAGE_SOURCE.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return key;
        }
    }
}
