package com.galio.core.utils;

/**
 * @Author: galio
 * @Date: 2023-03-01 20:27:47
 * @Description: html工具类
 */
public class HtmlUtil {

    public static String stripHtmlTag(String context) {

        return context.isBlank() ? "" : context.replaceAll("\\<.*?>", "").replaceAll("\\n", "").replaceAll("\\t", "").replaceAll("\\r", "");

    }

}
