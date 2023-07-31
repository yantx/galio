package com.galio.system.service;

import com.galio.system.dto.LoginMemberDto;

/**
 * @Author: galio
 * @Date: 2023-07-30 08:03:16
 * @Description: 会员关联业务接口 (会员权限)
 */
public interface MemberBizService {

    LoginMemberDto queryMemberInfo(String username);
}
