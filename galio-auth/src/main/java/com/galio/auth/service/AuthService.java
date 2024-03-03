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
import com.galio.satoken.tools.helper.LoginHelper;
import com.galio.satoken.tools.helper.MemberContextHelper;
import com.galio.system.api.MemberExchange;
import com.galio.system.dto.LoginMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 账号认证业务实现
 * 登录流程采用RSA与AES混合加密：
 *  客户端生成RSA密钥对（privateKey-A、publicKey-A），把公钥 publicKey-发A送给服务端
 *  服务端生成RSA密钥对（privateKey-B、publicKey-B），使用客户端的公钥publicKey-A加密自己的公钥publicKey-B ，将加密后的数据发送给客户端
 *  客户端使用自己的私钥privateKey-A解密获取到服务端的公钥，将AESKey使用服务端公钥publicKey-B加密发送给服务端
 *  服务端使用自己的私钥解密获取AESkey
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {


    private final MemberExchange memberExchange;
    private final PasswordProperties passwordProperties;
    private Map<String,String> rsaKeys;

    /**
     * 获取公钥
     * @return 公钥
     */
    public String getPublicKey() {
        rsaKeys = CryptoUtil.rsaGenerateKeyPair(CryptoUtil.RSA_KEY_SIZE_1024);
        return rsaKeys.get(CryptoUtil.RSA_PUBLIC_KEY);
    }

    /**
     * 登录接口
     * @param username 登录名
     * @param password 密码-RSA公钥加密后
     * @param securityKey 密钥 -RSA公钥加密后的aes密钥
     * @return LoginMemberDTO对象
     */
    public LoginMemberDTO login(String username, String password, String securityKey) throws CustomException{

        String aesKey =  CryptoUtil.rsaPrivateDecrypt(securityKey,rsaKeys.get(CryptoUtil.RSA_PRIVATE_KEY));
        String relPass = CryptoUtil.aesDecrypt(aesKey, password);

        checkLogin(username, () -> !BCrypt.checkpw(relPass, getPassword(username)));
        // 登录验证成功后查询用户信息
        LoginMemberDTO memberDTO = memberExchange.getMemberInfo(username);
        // 获取登录token 目前仅PC端
        LoginHelper.loginByDevice(memberDTO, OperSideEnum.PC);
        memberDTO.setToken(StpUtil.getTokenValue());
        recordAccessLog(username, CommonConstants.LOGIN_SUCCESS, MessageUtils.message("member.login.success"));
        return memberDTO;
    }
    public String getPassword(String username){
        String safePassword = memberExchange.getPassword(username);
        if(StringUtil.isEmpty(safePassword)){
            recordAccessLog(username, CommonConstants.LOGIN_FAIL, MessageUtils.message(ResponseEnum.MEMBER_NOT_EXITS.getMsg(), username));
            throw new CustomException(ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        }
        return safePassword;
    }
    /**
     * 后门接口, 传入用户名后模拟登录返回token 其他外部服务可通过此token来调用系统接口
     */
    public String getToken(String username) throws CustomException{
        String safePassword = memberExchange.getPassword(username);
        Assert.notNull(safePassword, ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        // 登录验证成功后查询用户信息并返回给前端
        LoginMemberDTO memberDTO = memberExchange.getMemberInfo(username);
        // 获取登录token 目前仅PC端
        LoginHelper.loginByDevice(memberDTO, OperSideEnum.PC);
        return StpUtil.getTokenValue();
    }

    /**
     * 退出登录
     */
    public void logout() {
        try {
            LoginMemberDTO loginUser = MemberContextHelper.getLoginMember();
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
        accessLogEvent.setStatus(status.equals(CommonConstants.LOGIN_FAIL) ? CommonConstants.LOGIN_FAIL_STATUS : CommonConstants.LOGIN_SUCCESS_STATUS);
        accessLogEvent.setMsg(message);
        accessLogEvent.setIpaddr(ServletUtils.getClientIP());
        SpringUtils.getContext().publishEvent(accessLogEvent);
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
