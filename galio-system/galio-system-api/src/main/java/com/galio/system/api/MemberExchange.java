package com.galio.system.api;

import com.galio.core.exception.CustomException;
import com.galio.system.dto.LoginMemberDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 人员信息交易接口
 */
@HttpExchange("system/remote/member")
public interface MemberExchange {
    /**
     * 通过登录名查询账号信息
     *
     * @param username 用户名
     * @return 会员信息
     */
    @GetExchange(value = "/getInfoByUsername")
    LoginMemberDTO getMemberInfo(@RequestParam(value = "username") String username) throws CustomException;

    /**
     * 通过登录名查询会员密码
     *
     * @param username 用户名
     * @return 密码串
     */
    @GetExchange(value = "/getPassword")
    String getPassword(@RequestParam(value = "username") String username) throws CustomException;

    /**
     * 通过手机号查询账号信息
     *
     * @param mobilePhone 手机号
     * @return 会员信息
     */
    LoginMemberDTO getMemberInfoByMobilePhone(String mobilePhone);

}
