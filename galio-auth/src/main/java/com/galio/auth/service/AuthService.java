package com.galio.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.auth.enums.AuthResponseEnum;
import com.galio.auth.properties.PasswordProperties;
import com.galio.common.log.event.AccessLogEvent;
import com.galio.core.constant.CacheConstants;
import com.galio.core.constant.CommonConstants;
import com.galio.core.enums.OperSideEnum;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.*;
import com.galio.redis.util.RedisUtils;
import com.galio.satoken.utils.LoginHelper;
import com.galio.system.api.MemberExchange;
import com.galio.system.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 账号认证业务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {


    private final MemberExchange memberExchange;
    private final PasswordProperties passwordProperties;

    /**
     * 登录
     */
    public String login(String username, String password) throws CustomException{

        // 登录改造 首先获取数据库中密码 登录成功后再获取所有用户信息。 密码应避免明文传输，
        // 加密策略待定 若加密需要盐则在用户输入账号时就应该验证用户是否存在 存在返回一个用户的盐，由前端将用户的密码+盐进行加密
        LoginMemberDto memberDto = memberExchange.getMemberInfo(username);
        Assert.notNull(memberDto, ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        checkLogin(username, () -> !BCrypt.checkpw(password, memberDto.getPassword()));
        // 获取登录token 目前仅PC端
        LoginHelper.loginByDevice(memberDto, OperSideEnum.PC);

        recordAccessLog(username, CommonConstants.LOGIN_SUCCESS, MessageUtils.message("member.login.success"));
        return StpUtil.getTokenValue();
    }

    /**
     * 退出登录
     */
    public void logout() {
        try {
            LoginMemberDto loginUser = LoginHelper.getLoginMember();
            StpUtil.logout();
            recordAccessLog(loginUser.getUsername(), CommonConstants.LOGOUT, MessageUtils.message("member.logout.success"));
        } catch (NotLoginException ignored) {
        }
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     */
    public void recordAccessLog(String username, String status, String message) {
        AccessLogEvent accessLogEvent = new AccessLogEvent();
        accessLogEvent.setUsername(username);
        accessLogEvent.setUsername(username);
        accessLogEvent.setStatus(status.equals(CommonConstants.LOGIN_FAIL) ? CommonConstants.LOGIN_FAIL_STATUS : CommonConstants.LOGIN_SUCCESS_STATUS);
        accessLogEvent.setMsg(message);
        accessLogEvent.setMsg(ServletUtils.getClientIP());
        SpringUtils.getContext().publishEvent(accessLogEvent);
        log.debug("username: {}, status: {}, message:{}", username, accessLogEvent.getStatus(), message);
    }

    /**
     * 登录校验
     */
    private void checkLogin(String username, Supplier<Boolean> supplier) {
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = CommonConstants.LOGIN_FAIL;
        Integer maxRetryCount = passwordProperties.getMaxRetryCount();
        Integer lockTime = passwordProperties.getLockTime();

        // 获取用户登录错误次数(可自定义限制策略 例如: key + username + ip)
        Integer errorNumber = RedisUtils.getCacheObject(errorKey);
        // 锁定时间内登录 则踢出
        if (ObjectUtil.isNotNull(errorNumber) && errorNumber.equals(maxRetryCount)) {
            recordAccessLog(username, loginFail, MessageUtils.message("member.password.retry.limit.exceed", maxRetryCount, lockTime));
            throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_EXCEED.withArgs(maxRetryCount, lockTime));
        }

        if (supplier.get()) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(maxRetryCount)) {
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                // TODO 如果日志推送异常则导致 后面的自定义密码错误异常将不会提示 这里需要处理一下, 可以try一下 或者访问日志接口不应该设置权限
                recordAccessLog(username, loginFail, MessageUtils.message("member.password.retry.limit.exceed", maxRetryCount, lockTime));
                throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_EXCEED.withArgs(maxRetryCount, lockTime));
            } else {
                // 未达到规定错误次数 则递增
                RedisUtils.setCacheObject(errorKey, errorNumber);
                recordAccessLog(username, loginFail, MessageUtils.message("member.password.retry.limit.count", maxRetryCount));
                throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_COUNT.withArgs(maxRetryCount));
            }
        }
        // 登录成功 清空错误次数
        RedisUtils.deleteObject(errorKey);
    }
}
