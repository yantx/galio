package com.galio.security.interceptor;

import com.galio.core.constant.SecurityConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
import com.galio.security.holder.SecurityContextHolder;
import com.galio.core.utils.ServletUtils;
import com.galio.security.auth.AuthUtil;
import com.galio.security.utils.SecurityUtils;
import com.galio.system.dto.LoginMemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * @Author: galio
 * @Date: 2023-02-26
 * @Description: 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_MEMBER_ID));
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.MEMBER_KEY));

        String token = SecurityUtils.getToken();
        if (StringUtil.isNotEmpty(token)) {
            LoginMemberDto loginMember = AuthUtil.getLoginMember(token);
            if (ObjectUtil.isNotNull(loginMember)) {
                AuthUtil.verifyLoginMemberDtoExpire(loginMember);
                SecurityContextHolder.set(SecurityConstants.LOGIN_MEMBER, loginMember);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }
}
