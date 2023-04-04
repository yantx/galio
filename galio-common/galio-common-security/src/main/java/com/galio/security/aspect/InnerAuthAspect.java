package com.galio.security.aspect;

import com.galio.core.constant.SecurityConstants;
import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.ServletUtils;
import com.galio.core.utils.StringUtil;
import com.galio.security.annotation.InnerAuth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Author: galio
 * @Date: 2023-02-26
 * @Description: 内部服务调用验证处理
 */
@Aspect
@Component
public class InnerAuthAspect implements Ordered {
    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint point, InnerAuth innerAuth) throws Throwable {
        String source = ServletUtils.getRequest().getHeader(SecurityConstants.FROM_SOURCE);
        // 内部请求验证
        if (!StringUtil.equals(SecurityConstants.INNER, source)) {
            throw new CustomException(ResponseCodeEnum.NO_PERMISSION);
        }

        String memberId = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_MEMBER_ID);
        String username = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USERNAME);
        // 用户信息验证
        if (innerAuth.isUser() && (StringUtil.isEmpty(memberId) || StringUtil.isEmpty(username))) {
            throw new CustomException(ResponseCodeEnum.NO_TOKEN);
        }
        return point.proceed();
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
