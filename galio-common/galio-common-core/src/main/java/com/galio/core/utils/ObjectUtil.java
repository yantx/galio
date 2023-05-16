package com.galio.core.utils;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: galio
 * @Date: 2023-02-26 15:42:05
 * @Description: 对象工具类
 */
public class ObjectUtil extends ObjectUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * 判断一个对象为Null且不包含任何元素
     *
     * @param obj Object
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            Optional<?> optional = (Optional) obj;
            return !optional.isPresent();
        } else if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            return charSequence.length() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            Collection<?> collection = (Collection) obj;
            return collection.isEmpty();
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map) obj;
            return map.isEmpty();
        } else {
            return false;
        }
    }
    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length ==0;
    }

    /**
     * * 判断一个对象是否非空且不包含任何元素
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    private static Map<String, BeanCopier> map = new ConcurrentHashMap<>();

    /**
     * 对象复制
     *
     * @param obj1   被复制对象，为空会抛出异常
     * @param clazz 复制类型
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object obj1, Class<T> clazz) {
        if (ObjectUtils.isEmpty(obj1) || ObjectUtils.isEmpty(clazz)) {
            return null;
        }
        T obj2 = null;
        try {
            obj2 = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CustomException(ResponseEnum.BEAN_INSTANTIATION_ERROR);
        } catch (InvocationTargetException e) {
            throw new CustomException(ResponseEnum.BEAN_INSTANTIATION_ERROR);
        } catch (NoSuchMethodException e) {
            throw new CustomException(ResponseEnum.BEAN_INSTANTIATION_ERROR);
        }
        String name = getClassName(obj1.getClass(), clazz);
        BeanCopier beanCopier;
        if (map.containsKey(name)) {
            beanCopier = map.get(name);
        } else {
            beanCopier = BeanCopier.create(obj1.getClass(), clazz, false);
            map.put(name, beanCopier);
        }
        beanCopier.copy(obj1, obj2, null);
        return obj2;
    }

    /**
     * 复制队列
     *
     * @param list   被复制队列
     * @param clazz 复制类型
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> list, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("被复制的队列为空!");
        }

        List<T> resultList = new LinkedList<>();
        for (Object obj1 : list) {
            resultList.add(copyObject(obj1, clazz));
        }
        return resultList;
    }

    private static String getClassName(Class<?> class1, Class<?> class2) {
        return class1.getName() + class2.getName();
    }

}
