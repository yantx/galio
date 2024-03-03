package com.galio.generator.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringHelper {


    /**
     * 将输入字符转成驼峰命名方式
     * 例如：
     *
     * @param rawStr
     * @param isFirstCharUp
     * @return
     */
    public static String toCamelCase(String rawStr, boolean isFirstCharUp) {
        if (rawStr == null || "".equals(rawStr.trim())) {
            return null;
        }
        rawStr = rawStr.trim();
        char[] chars = rawStr.toCharArray();
        boolean needUpper = isFirstCharUp;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ('-' == c || '_' == c) {
                needUpper = true;
                continue;
            }
            if (needUpper) {
                if (c >= 'a' && c <= 'z') {
                    c -= 32;
                    chars[i] = c;
                }
                needUpper = false;
            } else {
                if (c >= 'A' && c <= 'Z') {
                    c += 32;
                    chars[i] = c;
                }
            }
        }
        return new String(chars).replaceAll("_|-", "");
    }


    /**
     * 替换patternExp格式的数据为外部传递的参数值 repalceParams
     *
     * @param str
     * @param params
     * @return
     * @throws Exception
     */
    public static String repalceParams(String str,
                                       VelocityContext params, Pattern pattern) {
        if (str == null || "".equalsIgnoreCase(str.trim())) {
            return null;
        }
        if (pattern == null) {
            pattern = Pattern.compile("\\$\\{\\s*(\\w+)\\s*}");
        }
        Matcher paraMatcher = pattern.matcher(str);
        StringBuffer paraStrBuf = new StringBuffer();
        while (paraMatcher.find()) {
            String paraName = paraMatcher.group(1).trim();
            if (!params.containsKey(paraName)) {
                log.error("未设置参数{}", paraName);
                return null;
            }
            String paraValue = params.get(paraName).toString();
            paraMatcher.appendReplacement(paraStrBuf, paraValue);
        }
        paraMatcher.appendTail(paraStrBuf);
        str = paraStrBuf.toString();
        return str;

    }

}
