package com.galio.core.utils;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.enums.StatusCode;
import com.galio.core.exception.CustomException;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @Author: galio
 * @Date: 2023-05-06 13:35:15
 * @Description: 断言类
 */
public abstract class Assert {

    /**
     * 对象是null抛出异常
     * @param obj
     * @param messageSupplier
     */
    public static void notNull(Object obj, StatusCode messageSupplier) {
        if (ObjectUtil.isNull(obj)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 对象不是空抛出异常
     * @param obj
     * @param messageSupplier
     */
    public static void isNull(Object obj, StatusCode messageSupplier) {
        if (ObjectUtil.isNotNull(obj)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 检查对象必须为另一个特定类型的实例
     * @param type
     * @param obj
     * @param messageSupplier
     */
    public static void isInstanceOf(Class<?> type, @Nullable Object obj, StatusCode messageSupplier) {
        notNull(type, ResponseEnum.PARAM_NOT_NULL);
        if (!type.isInstance(obj)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 检查类型
     * @param superType
     * @param subType
     * @param messageSupplier
     */
    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, StatusCode messageSupplier) {
        notNull(superType, ResponseEnum.PARAM_NOT_NULL);
        if (ObjectUtil.isNull(subType) || !superType.isAssignableFrom(subType)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 只要不是null和空字符串就不会报异常
     * @param text
     * @param messageSupplier
     */
    public static void hasLength(@Nullable String text, StatusCode messageSupplier) {
        if (StringUtil.isEmpty(text)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 增强检查条件，字符串至少包含一个非空白字符，可以使用hasText()方法
     * @param text
     * @param messageSupplier
     */
    public static void hasText(@Nullable String text, StatusCode messageSupplier) {
        if (StringUtil.hasText(text)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 检查参数不包含特定子串
     * @param textToSearch
     * @param substring
     * @param messageSupplier
     */
    public static void doesNotContain(@Nullable String textToSearch, String substring, StatusCode messageSupplier) {
        if (StringUtil.isNotEmpty(textToSearch) && StringUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 条件为假抛出异常
     * @param expression
     * @param messageSupplier
     */
    public static void isTrue(boolean expression, StatusCode messageSupplier) {
        if (!expression) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 条件为假抛出异常
     * @param array
     * @param messageSupplier
     */
    public static void notEmpty(@Nullable Object[] array, StatusCode messageSupplier) {
        if (ObjectUtil.isEmpty(array)) {
            throw new CustomException(messageSupplier);
        }
    }

    /**
     * 确保数组不包含null元素
     * @param array
     * @param messageSupplier
     */
    public static void noNullElements(@Nullable Object[] array, StatusCode messageSupplier) {
        if (array != null) {
            Object[] arr = array;
            int arrSize = array.length;

            for(int i = 0; i < arrSize; ++i) {
                Object element = arr[i];
                if (element == null) {
                    throw new CustomException(messageSupplier);
                }
            }
        }

    }
}
