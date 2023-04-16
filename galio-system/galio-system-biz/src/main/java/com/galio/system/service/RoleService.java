package com.galio.system.service;

import com.galio.system.model.vo.RoleVo;
import com.galio.system.model.dto.RoleDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 角色信息Service接口
 */
public interface RoleService {

    /**
     * 查询角色信息
     */
    RoleVo queryById(Long roleId);

    /**
     * 查询角色信息列表
     */
    PageVo<RoleVo> queryPageList(PageDto pageDto);

    /**
     * 查询角色信息列表
     */
    List<RoleVo> queryList(RoleDto dto);

    /**
     * 修改角色信息
     */
    Boolean insertByDto(RoleDto dto);

    /**
     * 修改角色信息
     */
    Boolean updateByDto(RoleDto dto);

    /**
     * 校验并批量删除角色信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
