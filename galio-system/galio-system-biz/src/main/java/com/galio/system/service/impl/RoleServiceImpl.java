package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.RoleDto;
import com.galio.system.model.vo.RoleVo;
import com.galio.system.model.Role;
import com.galio.system.mapper.RoleMapper;
import com.galio.system.service.RoleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 角色信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    /**
     * 查询角色信息
     */
    @Override
    public RoleVo queryById(Long roleId) {
        return roleMapper.selectVoById(roleId);
    }

        /**
         * 查询角色信息列表
         */
        @Override
        public PageVo<RoleVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Role> lqw = Wrappers.lambdaQuery();
            IPage<RoleVo> pageData = roleMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询角色信息列表
     */
    @Override
    public List<RoleVo> queryList(RoleDto dto) {
        LambdaQueryWrapper<Role> lqw = buildQueryWrapper(dto);
        return roleMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Role> buildQueryWrapper(RoleDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Role> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getRoleName()), Role::getRoleName, dto.getRoleName());
                    lqw.eq(StringUtil.isNotBlank(dto.getRoleKey()), Role::getRoleKey, dto.getRoleKey());
                    lqw.eq(dto.getOrderNum() != null, Role::getOrderNum, dto.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(dto.getDataScope()), Role::getDataScope, dto.getDataScope());
                    lqw.eq(StringUtil.isNotBlank(dto.getFunctionCheckStrictly()), Role::getFunctionCheckStrictly, dto.getFunctionCheckStrictly());
                    lqw.eq(StringUtil.isNotBlank(dto.getOrgCheckStrictly()), Role::getOrgCheckStrictly, dto.getOrgCheckStrictly());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Role::getStatus, dto.getStatus());
                    lqw.eq(StringUtil.isNotBlank(dto.getDeleteFlag()), Role::getDeleteFlag, dto.getDeleteFlag());
                    lqw.eq(dto.getAppId() != null, Role::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增角色信息
     */
    @Override
    public Boolean insertByDto(RoleDto dto) {
        Role add = ObjectUtil.copyObject(dto, Role. class);
        validEntityBeforeSave(add);
        boolean flag = roleMapper.insert(add) > 0;
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
        Role update = ObjectUtil.copyObject(dto, Role. class);
        validEntityBeforeSave(update);
        return roleMapper.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return roleMapper.deleteBatchIds(ids) > 0;
    }
}
