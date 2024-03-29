package com.galio.system.mapper;

import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Mapper接口
 */
public interface RoleMapper extends BaseMapperPlus<RoleMapper, Role> {
    List<Role> selectByMemberId(@Param("memberId") Long memberId);
    List<Role> selectByGroupId(@Param("groupId") Long groupId);
}
