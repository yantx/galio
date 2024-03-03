package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDTO;
import com.galio.mybatis.page.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.galio.satoken.tools.helper.MemberContextHelper;
import com.galio.system.dto.LoginMemberDTO;
import com.galio.system.entity.Role;
import com.galio.system.service.MemberBizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.entity.Function;
import com.galio.system.model.vo.FunctionVo;
import com.galio.system.dto.FunctionDTO;
import com.galio.system.service.FunctionService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 功能接口
 * 前端访问路由地址为:/system/function
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "功能API")
@RequestMapping("/function")
public class FunctionController {

    private final FunctionService functionService;
    private final MemberBizService memberBizService;

    /**
     * 查询功能列表
     */
    @Operation(summary = "查询功能列表")
    @SaCheckPermission("system:function:page")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<Function> pageData = functionService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取功能详细信息
     *
     * @param functionId 主键
     */
    @Operation(summary = "查询功能详情")
    @SaCheckPermission("system:function:query")
    @GetMapping("/{functionId}")
    public FunctionVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long functionId) {
        Function function = functionService.getById(functionId);
        return ObjectUtil.copyObject(function, FunctionVo.class);
    }

    /**
     * 查询功能树
     *
     */
    @Operation(summary = "查询功能树")
    @SaCheckPermission("system:function:query")
    @GetMapping("/tree")
    public List<Function> getTree() {
        LoginMemberDTO memberDTO = MemberContextHelper.getLoginMember();
        List<Role> roleList = memberBizService.queryRoleWithMember(MemberContextHelper.getMemberId());
        List<Function> functionList;
        if (memberDTO.isAdmin() || memberDTO.isSuperAdmin()){
            functionList = functionService.list(new FunctionDTO());
        }else {
            Set<Long> roleIds = roleList.stream().map(Role::getRoleId).collect(Collectors.toSet());
            functionList = functionService.listByRoles(roleIds);
        }
        return functionService.getTree(functionList);
    }

    /**
     * 新增功能
     */
    @Operation(summary = "新增功能")
    @SaCheckPermission("system:function:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody FunctionDTO dto) {
        return functionService.save(dto);
    }

    /**
     * 修改功能
     */
    @Operation(summary = "修改功能")
    @SaCheckPermission("system:function:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody FunctionDTO dto) {
        return functionService.update(dto);
    }

    /**
     * 删除功能
     *
     * @param functionIds 主键串
     */
    @Operation(summary = "删除功能")
    @SaCheckPermission("system:function:remove")
    @DeleteMapping("/{functionIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] functionIds) {
        return functionService.deleteWithValidByIds(Arrays.asList(functionIds));
    }
}
