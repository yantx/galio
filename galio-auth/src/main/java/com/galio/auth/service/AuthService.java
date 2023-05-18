package com.galio.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.auth.enums.AuthResponseEnum;
import com.galio.auth.properties.PasswordProperties;
import com.galio.core.constant.CacheConstants;
import com.galio.core.constant.CommonConstants;
import com.galio.core.enums.DeviceType;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.Assert;
import com.galio.core.utils.MessageUtils;
import com.galio.core.utils.ObjectUtil;
import com.galio.redis.util.RedisUtils;
import com.galio.satoken.utils.LoginHelper;
import com.galio.system.api.RemoteMemberClient;
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


    private final RemoteMemberClient remoteMemberClient;

    private final PasswordProperties passwordProperties;

    /**
     * 登录
     */
    public String login(String username, String password) throws CustomException{
        LoginMemberDto memberDto = remoteMemberClient.getMemberInfo(username);

        Assert.notNull(memberDto, ResponseEnum.MEMBER_NOT_EXITS.packageByArgs(username));
        checkLogin(username, () -> !BCrypt.checkpw(password, memberDto.getPassword()));
        // 获取登录token
        LoginHelper.loginByDevice(memberDto, DeviceType.PC);

        recordLogininfor(username, CommonConstants.LOGIN_SUCCESS, MessageUtils.message("member.login.success"));
        return StpUtil.getTokenValue();
    }

    /**
     * 退出登录
     */
    public void logout() {
        try {
            LoginMemberDto loginUser = LoginHelper.getLoginMember();
            StpUtil.logout();
            recordLogininfor(loginUser.getUsername(), CommonConstants.LOGOUT, MessageUtils.message("member.logout.success"));
        } catch (NotLoginException ignored) {
        }
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message) {
        log.debug("username: {}, status: {}, message:{}", username, status, message);
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
            recordLogininfor(username, loginFail, MessageUtils.message("member.password.retry.limit.exceed", maxRetryCount, lockTime));
            throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_EXCEED, maxRetryCount, lockTime);
        }

        if (supplier.get()) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(maxRetryCount)) {
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                recordLogininfor(username, loginFail, MessageUtils.message("member.password.retry.limit.exceed", maxRetryCount, lockTime));
                throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_EXCEED, maxRetryCount, lockTime);
            } else {
                // 未达到规定错误次数 则递增
                RedisUtils.setCacheObject(errorKey, errorNumber);
                recordLogininfor(username, loginFail, MessageUtils.message("member.password.retry.limit.count", maxRetryCount));
                throw new CustomException(AuthResponseEnum.MEMBER_PASSWORD_RETRY_LIMIT_COUNT, maxRetryCount);
            }
        }
        // 登录成功 清空错误次数
        RedisUtils.deleteObject(errorKey);
    }
}
