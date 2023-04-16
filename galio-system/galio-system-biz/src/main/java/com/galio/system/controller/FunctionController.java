package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.vo.FunctionVo;
import com.galio.system.model.dto.FunctionDto;
import com.galio.system.service.FunctionService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 功能接口
 * 前端访问路由地址为:/system/function
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/function")
public class FunctionController {

    private final FunctionService functionService;

    /**
     * 查询功能列表
     */
    @SaCheckPermission("system:function:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return functionService.queryPageList(pageDto);
    }

    /**
     * 获取功能详细信息
     *
     * @param functionId 主键
     */
    @SaCheckPermission("system:function:query")
    @GetMapping("/{functionId}")
    public FunctionVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long functionId) {
        return functionService.queryById(functionId);
    }

    /**
     * 新增功能
     */
    @SaCheckPermission("system:function:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody FunctionDto dto) {
        return functionService.insertByDto(dto);
    }

    /**
     * 修改功能
     */
    @SaCheckPermission("system:function:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody FunctionDto dto) {
        return functionService.updateByDto(dto);
    }

    /**
     * 删除功能
     *
     * @param functionIds 主键串
     */
    @SaCheckPermission("system:function:remove")
    @DeleteMapping("/{functionIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] functionIds) {
        return functionService.deleteWithValidByIds(Arrays.asList(functionIds), true);
    }
}
