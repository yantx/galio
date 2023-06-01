package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Dict;
import com.galio.system.model.vo.DictVo;
import com.galio.system.dto.DictDto;
import com.galio.system.service.DictService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典接口
 * 前端访问路由地址为:/system/dict
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "字典API")
@RequestMapping("/dict")
public class DictController {

    private final DictService dictService;

    /**
     * 查询字典列表
     */
    @Operation(summary = "查询字典列表")
    @SaCheckPermission("system:dict:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Dict> pageData = dictService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取字典详细信息
     *
     * @param dictId 主键
     */
    @Operation(summary = "查询字典详情")
    @SaCheckPermission("system:dict:query")
    @GetMapping("/{dictId}")
    public DictVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long dictId) {
        Dict dict = dictService.queryById(dictId);
        return ObjectUtil.copyObject(dict, DictVo.class);
    }

    /**
     * 新增字典
     */
    @Operation(summary = "新增字典")
    @SaCheckPermission("system:dict:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody DictDto dto) {
        return dictService.insertByDto(dto);
    }

    /**
     * 修改字典
     */
    @Operation(summary = "修改字典")
    @SaCheckPermission("system:dict:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody DictDto dto) {
        return dictService.updateByDto(dto);
    }

    /**
     * 删除字典
     *
     * @param dictIds 主键串
     */
    @Operation(summary = "删除字典")
    @SaCheckPermission("system:dict:remove")
    @DeleteMapping("/{dictIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] dictIds) {
        return dictService.deleteWithValidByIds(Arrays.asList(dictIds), true);
    }
}
