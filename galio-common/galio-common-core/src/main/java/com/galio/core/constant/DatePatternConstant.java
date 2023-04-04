package com.galio.core.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author: galio
 * @Date: 2023-03-30 06:51:50
 * @Description: 日期格式
 */
public interface DatePatternConstant {
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter NORM_DATE_FORMAT = DateTimeFormatter.ofPattern(NORM_DATE_PATTERN);

    public static final String PURE_DATE_PATTERN = "yyyyMMdd";
    public static final DateTimeFormatter PURE_DATE_FORMAT = DateTimeFormatter.ofPattern(PURE_DATE_PATTERN);

    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter NORM_DATETIME_FORMAT = DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN);
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter NORM_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(NORM_DATETIME_MINUTE_PATTERN);

    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    public static final DateTimeFormatter PURE_DATETIME_FORMAT = DateTimeFormatter.ofPattern(PURE_DATETIME_PATTERN);
    public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";
    public static final DateTimeFormatter PURE_DATETIME_MS_FORMAT = DateTimeFormatter.ofPattern(PURE_DATETIME_MS_PATTERN);

    public static final String EN_DATE_PATTERN = "yyyy/MM/dd";
    public static final DateTimeFormatter EN_DATE_FORMAT = DateTimeFormatter.ofPattern(EN_DATE_PATTERN);
    public static final String EN_DATE_MONTH_PATTERN = "yyyy/MM";
    public static final DateTimeFormatter EN_DATE_MONTH_FORMAT = DateTimeFormatter.ofPattern(EN_DATE_MONTH_PATTERN);
    public static final String EN_DATETIME_MINUTE_PATTERN = "yyyy/MM/dd HH:mm";
    public static final DateTimeFormatter EN_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(EN_DATETIME_MINUTE_PATTERN);
    public static final String EN_DATETIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter EN_DATETIME_FORMAT = DateTimeFormatter.ofPattern(EN_DATETIME_PATTERN);

    public static final String CN_DATE_PATTERN = "yyyy年MM月dd日";
    public static final DateTimeFormatter CN_DATE_FORMAT = DateTimeFormatter.ofPattern(CN_DATE_PATTERN);
    public static final String CN_DATE_MONTH_PATTERN = "yyyy年MM月";
    public static final DateTimeFormatter CN_DATE_MONTH_FORMAT = DateTimeFormatter.ofPattern(CN_DATE_MONTH_PATTERN);
    public static final String CN_DATETIME_MINUTE_PATTERN = "yyyy年MM月dd日 HH:mm";
    public static final DateTimeFormatter CN_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(CN_DATETIME_MINUTE_PATTERN);
    public static final String CN_DATETIME_PATTERN = "yyyy年MM月dd日 HH:mm:ss";
    public static final DateTimeFormatter CN_DATETIME_FORMAT = DateTimeFormatter.ofPattern(CN_DATETIME_PATTERN);

    public static final String SIMPLE_DATE_MONTH_PATTERN = "yyyy-MM";
    public static final DateTimeFormatter SIMPLE_DATE_MONTH_FORMAT = DateTimeFormatter.ofPattern(SIMPLE_DATE_MONTH_PATTERN);
    public static final String SIMPLE_DATE_DAY_PATTERN = "MM-dd";
    public static final DateTimeFormatter SIMPLE_DATE_DAY_FORMAT = DateTimeFormatter.ofPattern(SIMPLE_DATE_DAY_PATTERN);
    public static final String SIMPLE_DATETIME_MINUTE_PATTERN = "MM-dd HH:mm";
    public static final DateTimeFormatter SIMPLE_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(SIMPLE_DATETIME_MINUTE_PATTERN);
    public static final String SIMPLE_DATETIME_SECOND_PATTERN = "MM-dd HH:mm:ss";
    public static final DateTimeFormatter SIMPLE_DATETIME_SECOND_FORMAT = DateTimeFormatter.ofPattern(SIMPLE_DATETIME_SECOND_PATTERN);
    public static final String SIMPLE_TIME_MINUTE_PATTERN = "HH:mm";
    public static final DateTimeFormatter SIMPLE_TIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(SIMPLE_TIME_MINUTE_PATTERN);

    public static final String NORM_TIME_PATTERN = "HH:mm:ss";
    public static final DateTimeFormatter NORM_TIME_FORMAT = DateTimeFormatter.ofPattern(NORM_TIME_PATTERN);
    public static final String PURE_TIME_PATTERN = "HHmmss";
    public static final DateTimeFormatter PURE_TIME_FORMAT = DateTimeFormatter.ofPattern(PURE_TIME_PATTERN);

    public static final String EN_SIMPLE_DATE_DAY_PATTERN = "MM/dd";
    public static final DateTimeFormatter EN_SIMPLE_DATE_DAY_FORMAT = DateTimeFormatter.ofPattern(EN_SIMPLE_DATE_DAY_PATTERN);
    public static final String EN_SIMPLE_DATETIME_MINUTE_PATTERN = "MM/dd HH:mm";
    public static final DateTimeFormatter EN_SIMPLE_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(EN_SIMPLE_DATETIME_MINUTE_PATTERN);
    public static final String EN_SIMPLE_DATETIME_SECOND_PATTERN = "MM/dd HH:mm:ss";
    public static final DateTimeFormatter EN_SIMPLE_DATETIME_SECOND_FORMAT = DateTimeFormatter.ofPattern(EN_SIMPLE_DATETIME_SECOND_PATTERN);

    public static final String CN_SIMPLE_DATE_DAY_PATTERN = "MM月dd日";
    public static final DateTimeFormatter CN_SIMPLE_DATE_DAY_FORMAT = DateTimeFormatter.ofPattern(CN_SIMPLE_DATE_DAY_PATTERN);
    public static final String CN_SIMPLE_DATETIME_MINUTE_PATTERN = "MM月dd日 HH:mm";
    public static final DateTimeFormatter CN_SIMPLE_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern(CN_SIMPLE_DATETIME_MINUTE_PATTERN);
    public static final String CN_SIMPLE_DATETIME_SECOND_PATTERN = "MM月dd日 HH:mm:ss";
    public static final DateTimeFormatter CN_SIMPLE_DATETIME_SECOND_FORMAT = DateTimeFormatter.ofPattern(CN_SIMPLE_DATETIME_SECOND_PATTERN);

    public static final String SPECIAL_SIMPLE_DATE_PATTERN = "yyyy.MM.dd";
    public static final DateTimeFormatter SPECIAL_SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern(SPECIAL_SIMPLE_DATE_PATTERN);
    public static final String SPECIAL_SIMPLE_DATE_DAY_PATTERN = "MM.dd";
    public static final DateTimeFormatter SPECIAL_SIMPLE_DATE_DAY_FORMAT = DateTimeFormatter.ofPattern(SPECIAL_SIMPLE_DATE_DAY_PATTERN);

    public static final String AD_DATETIME_PATTERN = "G y-MM-dd Z E HH:mm:ss:SSS a";
    public static final DateTimeFormatter AD_DATETIME_FORMAT = DateTimeFormatter.ofPattern(AD_DATETIME_PATTERN);// 公元 2019-01-01 +0800 星期二 10:39:06:863 下午

    public static final String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
    public static final DateTimeFormatter HTTP_DATETIME_FORMAT = DateTimeFormatter.ofPattern(HTTP_DATETIME_PATTERN, Locale.US);

    public static final String JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final DateTimeFormatter JDK_DATETIME_FORMAT = DateTimeFormatter.ofPattern(JDK_DATETIME_PATTERN, Locale.US);

    public static final String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final DateTimeFormatter UTC_FORMAT = DateTimeFormatter.ofPattern(UTC_PATTERN);

    /**
     * 年
     */
    public static final String YEAR = "year";

    /**
     * 月
     */
    public static final String MONTH = "month";

    /**
     * 周
     */
    public static final String WEEK = "week";

    /**
     * 日
     */
    public static final String DAY = "day";

    /**
     * 时
     */
    public static final String HOUR = "hour";

    /**
     * 分
     */
    public static final String MINUTE = "minute";

    /**
     * 秒
     */
    public static final String SECOND = "second";
}
