package com.galio.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: galio
 * @Date: 2022-12-14
 * @Description: JSON工具类
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //序列化的时候序列对象的所有属性
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 配置[时间类型转换]
        JavaTimeModule timeModule = new JavaTimeModule();
        // LocalDateTime序列化与反序列化
        timeModule.addSerializer(new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        // LocalDate序列化与反序列化
        timeModule.addSerializer(new LocalDateSerializer(DATE_FORMATTER));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        // LocalTime序列化与反序列化
        timeModule.addSerializer(new LocalTimeSerializer(TIME_FORMATTER));
        timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
        OBJECT_MAPPER.registerModule(timeModule);
    }

    /**
     * 对象转Json格式字符串
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String toString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串会保证一定的结构性,提高可读性，增加字符串大小)
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String toStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 字符串转换为自定义对象
     * @param str 要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String str, Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 进行复杂类型反序列化工作 （自定义类型的集合类型）
     *
     * @param str 源字符串
     * @param typeReference 包含elementType与CollectionType的typeReference
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : OBJECT_MAPPER.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 进行复杂类型反序列化工作（可变类型数量的）
     *
     * @param str             需要进行反序列化的字符串
     * @param collectionClass 需要反序列化的集合类型 由于这里的类型未定，且为了防止与返回值类型T冲突，故采用<?>表示泛型
     * @param elementClasses  集合中的元素类型（可多个）   此处同上通过<?>...表示多个未知泛型
     * @param <T>             返回值的泛型类型是由javatype获取的
     */
    public static <T> T toObject(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return OBJECT_MAPPER.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }

}
