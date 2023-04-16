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
import com.galio.system.model.vo.DictItemVo;
import com.galio.system.model.dto.DictItemDto;
import com.galio.system.service.DictItemService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典项接口
 * 前端访问路由地址为:/system/dictItem
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/dictItem")
public class DictItemController {

    private final DictItemService dictItemService;

    /**
     * 查询字典项列表
     */
    @SaCheckPermission("system:dictItem:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return dictItemService.queryPageList(pageDto);
    }

    /**
     * 获取字典项详细信息
     *
     * @param dictItemId 主键
     */
    @SaCheckPermission("system:dictItem:query")
    @GetMapping("/{dictItemId}")
    public DictItemVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long dictItemId) {
        return dictItemService.queryById(dictItemId);
    }

    /**
     * 新增字典项
     */
    @SaCheckPermission("system:dictItem:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody DictItemDto dto) {
        return dictItemService.insertByDto(dto);
    }

    /**
     * 修改字典项
     */
    @SaCheckPermission("system:dictItem:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody DictItemDto dto) {
        return dictItemService.updateByDto(dto);
    }

    /**
     * 删除字典项
     *
     * @param dictItemIds 主键串
     */
    @SaCheckPermission("system:dictItem:remove")
    @DeleteMapping("/{dictItemIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] dictItemIds) {
        return dictItemService.deleteWithValidByIds(Arrays.asList(dictItemIds), true);
    }
}
