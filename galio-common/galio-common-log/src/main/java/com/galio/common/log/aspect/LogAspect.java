package com.galio.common.log.aspect;

import com.galio.common.log.annotation.OperateLog;
import com.galio.common.log.enums.OperStatus;
import com.galio.common.log.event.OperationLogEvent;
import com.galio.core.utils.*;
import com.galio.satoken.tools.helper.MemberContextHelper;
import io.netty.handler.codec.http.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: galio
 * @Date: 2023-05-30 22:33:51
 * @Description: 日志处理类
 */
@Slf4j
@Aspect
@AutoConfiguration
public class LogAspect {

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, OperateLog controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperateLog controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, OperateLog controllerLog, final Exception e, Object jsonResult) {
        try {
            // *========数据库日志=========*//
            OperationLogEvent operationLog = new OperationLogEvent();
            operationLog.setStatus(OperStatus.SUCCESS.ordinal());

            // 请求的地址
            operationLog.setOperationIp(ServletUtils.getClientIP());
            operationLog.setOperationUrl(StringUtil.substring(Objects.requireNonNull(ServletUtils.getRequest()).getRequestURI(), 0, 255));
            String username = MemberContextHelper.getUsername();
            if (StringUtil.isNotBlank(username)) {
                operationLog.setOperator(username);
            }

            if (e != null) {
                operationLog.setStatus(OperStatus.FAIL.ordinal());
                operationLog.setErrorMsg(StringUtil.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operationLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operationLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            operationLog.setOperationTime(LocalDateTime.now());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operationLog, jsonResult);
            // 发布事件保存数据库
            SpringUtils.getContext().publishEvent(operationLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage(), e);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, OperateLog log, OperationLogEvent operLog, Object jsonResult) {
        // 设置action动作
        operLog.setOperationType(log.operType().ordinal());
        // 设置模块
        operLog.setModel(log.operModul());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && ObjectUtil.isNotNull(jsonResult)) {
            operLog.setJsonResult(StringUtil.substring(JsonUtils.toString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, OperationLogEvent operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperationParam(StringUtil.substring(params, 0, 2000));
        } else {
            Map<String, String> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
            paramsMap.keySet().removeIf(key -> Arrays.asList(EXCLUDE_PROPERTIES).contains(key));
            operLog.setOperationParam(StringUtil.substring(JsonUtils.toString(paramsMap), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (ObjectUtil.isNotNull(o) && !isFilterObject(o)) {
                    String str = JsonUtils.toString(o);
                    LinkedHashMap<String, Object> map = JsonUtils.toObject(str, LinkedHashMap.class);
                    if (ObjectUtil.isNotNull(map) && !map.isEmpty()) {
                        map.keySet().removeIf(key -> Arrays.asList(EXCLUDE_PROPERTIES).contains(key));
                        str = JsonUtils.toString(map);
                    }
                    params.append(str).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
