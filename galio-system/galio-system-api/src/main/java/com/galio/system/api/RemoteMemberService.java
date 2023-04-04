package com.galio.system.api;

import com.galio.core.exception.CustomException;
import com.galio.system.dto.LoginMemberDto;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 人员相关业务服务
 */
public interface RemoteMemberService {
    /**
     * 通过登录名查询账号信息
     *
     * @param username 用户名
     * @return 结果
     */
    LoginMemberDto getMemberInfo(String username) throws CustomException;

    /**
     * 通过手机号查询账号信息
     *
     * @param mobilePhone 手机号
     * @return 结果
     */
    LoginMemberDto getMemberInfoByMobilePhone(String mobilePhone) throws CustomException;

}
