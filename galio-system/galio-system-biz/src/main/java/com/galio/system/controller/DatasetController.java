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
import com.galio.system.model.vo.DatasetVo;
import com.galio.system.model.dto.DatasetDto;
import com.galio.system.service.DatasetService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据集信息接口
 * 前端访问路由地址为:/system/dataset
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    /**
     * 查询数据集信息列表
     */
    @SaCheckPermission("system:dataset:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return datasetService.queryPageList(pageDto);
    }

    /**
     * 获取数据集信息详细信息
     *
     * @param datasetId 主键
     */
    @SaCheckPermission("system:dataset:query")
    @GetMapping("/{datasetId}")
    public DatasetVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long datasetId) {
        return datasetService.queryById(datasetId);
    }

    /**
     * 新增数据集信息
     */
    @SaCheckPermission("system:dataset:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody DatasetDto dto) {
        return datasetService.insertByDto(dto);
    }

    /**
     * 修改数据集信息
     */
    @SaCheckPermission("system:dataset:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody DatasetDto dto) {
        return datasetService.updateByDto(dto);
    }

    /**
     * 删除数据集信息
     *
     * @param datasetIds 主键串
     */
    @SaCheckPermission("system:dataset:remove")
    @DeleteMapping("/{datasetIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] datasetIds) {
        return datasetService.deleteWithValidByIds(Arrays.asList(datasetIds), true);
    }
}
