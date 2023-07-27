package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Org;
import com.galio.system.model.vo.OrgVo;
import com.galio.system.dto.OrgDto;
import com.galio.system.service.OrgService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 机构接口
 * 前端访问路由地址为:/system/org
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "机构API")
@RequestMapping("/org")
public class OrgController {

    private final OrgService orgService;

    /**
     * 查询机构列表
     */
    @Operation(summary = "查询机构列表")
    @SaCheckPermission("system:org:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageRequestDto pageRequestDto) {
        IPage<Org> pageData = orgService.queryPageList(pageRequestDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取机构详细信息
     *
     * @param orgId 主键
     */
    @Operation(summary = "查询机构详情")
    @SaCheckPermission("system:org:query")
    @GetMapping("/{orgId}")
    public OrgVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long orgId) {
        Org org = orgService.queryById(orgId);
        return ObjectUtil.copyObject(org, OrgVo.class);
    }

    /**
     * 新增机构
     */
    @Operation(summary = "新增机构")
    @SaCheckPermission("system:org:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody OrgDto dto) {
        return orgService.insertByDto(dto);
    }

    /**
     * 修改机构
     */
    @Operation(summary = "修改机构")
    @SaCheckPermission("system:org:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody OrgDto dto) {
        return orgService.updateByDto(dto);
    }

    /**
     * 删除机构
     *
     * @param orgIds 主键串
     */
    @Operation(summary = "删除机构")
    @SaCheckPermission("system:org:remove")
    @DeleteMapping("/{orgIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] orgIds) {
        return orgService.deleteWithValidByIds(Arrays.asList(orgIds));
    }
}
