package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.system.dto.MemberDTO;
import com.galio.system.entity.MemberRole;
import com.galio.system.entity.RoleDataset;
import com.galio.system.entity.RoleFunction;
import com.galio.system.repository.RoleDatasetRepository;
import com.galio.system.repository.RoleFunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.RoleDTO;
import com.galio.system.entity.Role;
import com.galio.system.repository.RoleRepository;
import com.galio.system.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleFunctionRepository roleFunctionRepository;
    private final RoleDatasetRepository roleDatasetRepository;

    /**
     * 查询角色信息
     */
    @Override
    public Role getById(Long roleId) {
        return roleRepository.selectById(roleId);
    }

    /**
     * 查询角色信息列表
     */
    @Override
    public Page<Role> listPage(PageRequestDTO pageRequestDTO) {
        return roleRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
    }

    /**
     * 查询角色信息列表
     */
    @Override
    public Set<String> queryRolePermissions(Long memberId) {
        List<Role> roles = roleRepository.selectByMemberId(memberId);

        return roles.stream().map(Role::getRoleKey).collect(Collectors.toSet());
    }

    /**
     * 查询角色信息列表
     */
    @Override
    public List<Role> list(RoleDTO dto) {
        Role entity = ObjectUtil.copyObject(dto, Role.class);

        return roleRepository.selectList(entity);
    }

    /**
     * 新增角色信息
     */
    @Override
    @Transactional
    public Boolean save(RoleDTO dto) {
        Role add = ObjectUtil.copyObject(dto, Role.class);
        validEntityBeforeSave(add);
        boolean flag = roleRepository.insert(add) > 0;
        if (flag) {
            dto.setRoleId(add.getRoleId());
            flag = relevanceFunctionInfo(dto) && relevanceDatasetInfo(dto);
        }
        return flag;
    }

    /**
     * 修改角色信息
     */
    @Override
    @Transactional
    public Boolean update(RoleDTO dto) {
        Role update = ObjectUtil.copyObject(dto, Role.class);
        validEntityBeforeSave(update);
        boolean flag = roleRepository.updateById(update) > 0;
        if (flag) {
            flag = relevanceFunctionInfo(dto) && relevanceDatasetInfo(dto);
        }
        return flag;
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
    @Transactional
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        boolean flag = roleRepository.deleteBatchIds(ids) > 0;
        if (flag) {
            roleFunctionRepository.deleteRoleIds(ids);
            roleDatasetRepository.deleteRoleIds(ids);
        }
        return flag;
    }

    public boolean relevanceFunctionInfo(RoleDTO dto) {
        if (ObjectUtil.isEmpty(dto.getFunctionIds())) {
            return true;
        }
        roleFunctionRepository.deleteRoleId(dto.getRoleId());
        List<RoleFunction> roleFunctions = dto.getFunctionIds().stream()
                .map(o -> new RoleFunction(dto.getRoleId(), o)).collect(Collectors.toList());
        return roleFunctionRepository.insertBatch(roleFunctions);
    }

    public boolean relevanceDatasetInfo(RoleDTO dto) {
        if (ObjectUtil.isEmpty(dto.getDatasetIds())) {
            return true;
        }
        roleDatasetRepository.deleteRoleId(dto.getRoleId());
        List<RoleDataset> roleFunctions = dto.getDatasetIds().stream()
                .map(o -> new RoleDataset(dto.getRoleId(), o)).collect(Collectors.toList());
        return roleDatasetRepository.insertBatch(roleFunctions);
    }
}
