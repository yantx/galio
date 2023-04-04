package com.galio.core.utils;

import com.google.common.base.CaseFormat;
import com.galio.core.text.StrFormatter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-01-19
 * @Description: 字符串工具
 */
public class StringUtil extends StringUtils {

    /**
     * 驼峰转下划线 humpToLine --> hump_to_line
     *
     * @param hump
     * @return
     */
    public static String humpToLine(String hump) {
        String line = "";
        if (StringUtils.isNotBlank(hump)) {
            line = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, hump);
        }
        return line;
    }

    /**
     * 下划线转驼峰 line_to_hump --> lineToHump
     *
     * @param line
     * @return
     */
    public static String lineToHump(String line) {
        String hump = "";
        if (StringUtils.isNotBlank(line)) {
            hump = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, line);
        }
        return hump;
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if (ObjectUtils.isEmpty(params) || StringUtils.isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str  指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strs) {
        if (isEmpty(str) || CollectionUtils.isEmpty(strs)) {
            return false;
        }
        for (String pattern : strs) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url     需要匹配的url
     * @return
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    /**
     * 判断是否为空，并且不是空白字符
     *
     * @param str 要判断的value
     * @return 结果
     */
    public static boolean hasText(String str)
    {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str)
    {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }
}
