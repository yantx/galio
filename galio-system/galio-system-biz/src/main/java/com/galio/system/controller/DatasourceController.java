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
import com.galio.system.entity.Datasource;
import com.galio.system.model.vo.DatasourceVo;
import com.galio.system.dto.DatasourceDTO;
import com.galio.system.service.DatasourceService;

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
@RestController
@RequiredArgsConstructor
@Tag(name = "数据源信息API")
@RequestMapping("/datasource")
public class DatasourceController {

    private final DatasourceService datasourceService;

    /**
     * 查询数据源信息列表
     */
    @Operation(summary = "查询数据源信息列表")
    @SaCheckPermission("system:datasource:page")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<Datasource> pageData = datasourceService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取数据源信息详细信息
     *
     * @param datasourceId 主键
     */
    @Operation(summary = "查询数据源信息详情")
    @SaCheckPermission("system:datasource:query")
    @GetMapping("/{datasourceId}")
    public DatasourceVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long datasourceId) {
        Datasource datasource = datasourceService.getById(datasourceId);
        return ObjectUtil.copyObject(datasource, DatasourceVo.class);
    }

    /**
     * 新增数据源信息
     */
    @Operation(summary = "新增数据源信息")
    @SaCheckPermission("system:datasource:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody DatasourceDTO dto) {
        return datasourceService.save(dto);
    }

    /**
     * 修改数据源信息
     */
    @Operation(summary = "修改数据源信息")
    @SaCheckPermission("system:datasource:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody DatasourceDTO dto) {
        return datasourceService.update(dto);
    }

    /**
     * 删除数据源信息
     *
     * @param datasourceIds 主键串
     */
    @Operation(summary = "删除数据源信息")
    @SaCheckPermission("system:datasource:remove")
    @DeleteMapping("/{datasourceIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] datasourceIds) {
        return datasourceService.deleteWithValidByIds(Arrays.asList(datasourceIds));
    }
}
