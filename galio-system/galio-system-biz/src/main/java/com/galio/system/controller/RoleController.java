package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Role;
import com.galio.system.model.vo.RoleVo;
import com.galio.system.model.dto.RoleDto;
import com.galio.system.service.RoleService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息接口
 * 前端访问路由地址为:/system/role
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 查询角色信息列表
     */
    @SaCheckPermission("system:role:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Role> pageData = roleService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取角色信息详细信息
     *
     * @param roleId 主键
     */
    @SaCheckPermission("system:role:query")
    @GetMapping("/{roleId}")
    public RoleVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long roleId) {
        Role role = roleService.queryById(roleId);
        return ObjectUtil.copyObject(role, RoleVo.class);
    }

    /**
     * 新增角色信息
     */
    @SaCheckPermission("system:role:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody RoleDto dto) {
        return roleService.insertByDto(dto);
    }

    /**
     * 修改角色信息
     */
    @SaCheckPermission("system:role:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody RoleDto dto) {
        return roleService.updateByDto(dto);
    }

    /**
     * 删除角色信息
     *
     * @param roleIds 主键串
     */
    @SaCheckPermission("system:role:remove")
    @DeleteMapping("/{roleIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] roleIds) {
        return roleService.deleteWithValidByIds(Arrays.asList(roleIds), true);
    }
}
