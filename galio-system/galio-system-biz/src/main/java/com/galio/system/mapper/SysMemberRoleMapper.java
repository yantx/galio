package com.galio.system.mapper;

import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.model.SysMemberRole;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 用户与角色关联表 持久层
 */
public interface SysMemberRoleMapper extends BaseMapperPlus<SysMemberRoleMapper, SysMemberRole, SysMemberRole> {

    List<Long> selectUserIdsByRoleId(Long roleId);

}
