package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.RoleDto;
import com.galio.system.model.Role;
import com.galio.system.repository.RoleRepository;
import com.galio.system.service.RoleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * 查询角色信息
     */
    @Override
    public Role queryById(Long roleId) {
        return roleRepository.selectById(roleId);
    }

        /**
         * 查询角色信息列表
         */
        @Override
        public Page<Role> queryPageList(PageRequestDto pageRequestDto) {
            return roleRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询角色信息列表
     */
    @Override
    public List<Role> queryList(RoleDto dto) {
        Role entity = ObjectUtil.copyObject(dto, Role.class);
        
        return roleRepository.selectList(entity);
    }

    /**
     * 新增角色信息
     */
    @Override
    public Boolean insertByDto(RoleDto dto) {
        Role add = ObjectUtil.copyObject(dto, Role.class);
        validEntityBeforeSave(add);
        boolean flag = roleRepository.insert(add) > 0;
        if (flag) {
            dto.setRoleId(add.getRoleId());
        }
        return flag;
    }

    /**
     * 修改角色信息
     */
    @Override
    public Boolean updateByDto(RoleDto dto) {
        Role update = ObjectUtil.copyObject(dto, Role.class);
        validEntityBeforeSave(update);
        return roleRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Role entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除角色信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return roleRepository.deleteBatchIds(ids) > 0;
    }
}
