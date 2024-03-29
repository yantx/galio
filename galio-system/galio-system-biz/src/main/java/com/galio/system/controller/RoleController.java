package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDTO;
import com.galio.mybatis.page.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.entity.Role;
import com.galio.system.model.vo.RoleVo;
import com.galio.system.dto.RoleDTO;
import com.galio.system.service.RoleService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 角色信息接口
 * 前端访问路由地址为:/system/role
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "角色信息API")
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 查询角色信息列表
     */
    @Operation(summary = "查询角色信息列表")
    @SaCheckPermission("system:role:page")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<Role> pageData = roleService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取角色信息详细信息
     *
     * @param roleId 主键
     */
    @Operation(summary = "查询角色信息详情")
    @SaCheckPermission("system:role:query")
    @GetMapping("/{roleId}")
    public RoleVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        return ObjectUtil.copyObject(role, RoleVo.class);
    }

    /**
     * 新增角色信息
     */
    @Operation(summary = "新增角色信息")
    @SaCheckPermission("system:role:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody RoleDTO dto) {
        return roleService.save(dto);
    }

    /**
     * 修改角色信息
     */
    @Operation(summary = "修改角色信息")
    @SaCheckPermission("system:role:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody RoleDTO dto) {
        return roleService.update(dto);
    }

    /**
     * 删除角色信息
     *
     * @param roleIds 主键串
     */
    @Operation(summary = "删除角色信息")
    @SaCheckPermission("system:role:remove")
    @DeleteMapping("/{roleIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] roleIds) {
        return roleService.deleteWithValidByIds(Arrays.asList(roleIds));
    }
}
