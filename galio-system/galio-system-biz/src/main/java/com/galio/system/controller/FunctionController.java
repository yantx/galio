package com.galio.system.controller;

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
import com.galio.system.model.Function;
import com.galio.system.model.vo.FunctionVo;
import com.galio.system.dto.FunctionDto;
import com.galio.system.service.FunctionService;

import java.util.Arrays;
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

    /**
     * 查询功能列表
     */
    @Operation(summary = "查询功能列表")
    // @SaCheckPermission("system:function:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageRequestDto pageRequestDto) {
        IPage<Function> pageData = functionService.queryPageList(pageRequestDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取功能详细信息
     *
     * @param functionId 主键
     */
    @Operation(summary = "查询功能详情")
    // @SaCheckPermission("system:function:query")
    @GetMapping("/{functionId}")
    public FunctionVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long functionId) {
        Function function = functionService.queryById(functionId);
        return ObjectUtil.copyObject(function, FunctionVo.class);
    }

    /**
     * 新增功能
     */
    @Operation(summary = "新增功能")
    // @SaCheckPermission("system:function:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody FunctionDto dto) {
        return functionService.insertByDto(dto);
    }

    /**
     * 修改功能
     */
    @Operation(summary = "修改功能")
    // @SaCheckPermission("system:function:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody FunctionDto dto) {
        return functionService.updateByDto(dto);
    }

    /**
     * 删除功能
     *
     * @param functionIds 主键串
     */
    @Operation(summary = "删除功能")
    // @SaCheckPermission("system:function:remove")
    @DeleteMapping("/{functionIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] functionIds) {
        return functionService.deleteWithValidByIds(Arrays.asList(functionIds), true);
    }
}
