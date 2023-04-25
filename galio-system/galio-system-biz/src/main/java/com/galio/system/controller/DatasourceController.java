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
import com.galio.system.model.Datasource;
import com.galio.system.model.vo.DatasourceVo;
import com.galio.system.model.dto.DatasourceDto;
import com.galio.system.service.DatasourceService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据源信息接口
 * 前端访问路由地址为:/system/datasource
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

    private final DatasourceService datasourceService;

    /**
     * 查询数据源信息列表
     */
    @SaCheckPermission("system:datasource:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Datasource> pageData = datasourceService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取数据源信息详细信息
     *
     * @param datasourceId 主键
     */
    @SaCheckPermission("system:datasource:query")
    @GetMapping("/{datasourceId}")
    public DatasourceVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long datasourceId) {
        Datasource datasource = datasourceService.queryById(datasourceId);
        return ObjectUtil.copyObject(datasource, DatasourceVo.class);
    }

    /**
     * 新增数据源信息
     */
    @SaCheckPermission("system:datasource:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody DatasourceDto dto) {
        return datasourceService.insertByDto(dto);
    }

    /**
     * 修改数据源信息
     */
    @SaCheckPermission("system:datasource:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody DatasourceDto dto) {
        return datasourceService.updateByDto(dto);
    }

    /**
     * 删除数据源信息
     *
     * @param datasourceIds 主键串
     */
    @SaCheckPermission("system:datasource:remove")
    @DeleteMapping("/{datasourceIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] datasourceIds) {
        return datasourceService.deleteWithValidByIds(Arrays.asList(datasourceIds), true);
    }
}
