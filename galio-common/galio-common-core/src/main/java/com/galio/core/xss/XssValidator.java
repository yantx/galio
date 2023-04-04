package com.galio.core.xss;

import com.galio.core.utils.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: galio
 * @Date: 2023-02-26 09:11:03
 * @Description: 自定义xss校验注解实现
 */
public class XssValidator implements ConstraintValidator<Xss, String> {

    private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtil.isBlank(value)) {
            return true;
        }
        return !containsHtml(value);
    }

    public static boolean containsHtml(String value) {
        Pattern pattern = Pattern.compile(HTML_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
