package com.galio.system.service;

import com.galio.system.dto.LoginMemberDTO;
import com.galio.system.entity.Role;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-07-30 08:03:16
 * @Description: 会员关联业务接口 (会员权限)
 */
public interface MemberBizService {

    LoginMemberDTO queryMemberInfo(String username);
    List<Role> queryRoleWithMember(Long memberId);
}
