package com.galio.system.api;

import com.galio.system.dto.LoginMemberDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 人员相关业务服务
 */
@HttpExchange("/remote/member")
public interface RemoteMemberClient {
    /**
     * 通过登录名查询账号信息
     *
     * @param username 用户名
     * @return 会员信息
     */
    @GetExchange(value = "/getInfoByUsername")
    LoginMemberDto getMemberInfo(@RequestParam(value = "username") String username);

    /**
     * 通过手机号查询账号信息
     *
     * @param mobilePhone 手机号
     * @return 会员信息
     */
    LoginMemberDto getMemberInfoByMobilePhone(String mobilePhone);

}