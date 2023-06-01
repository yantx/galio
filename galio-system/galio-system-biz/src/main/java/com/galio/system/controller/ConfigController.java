package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Config;
import com.galio.system.model.vo.ConfigVo;
import com.galio.system.dto.ConfigDto;
import com.galio.system.service.ConfigService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息接口
 * 前端访问路由地址为:/system/config
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "应用信息API")
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    /**
     * 查询应用信息列表
     */
    @Operation(summary = "查询应用信息列表")
    @SaCheckPermission("system:config:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Config> pageData = configService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param configId 主键
     */
    @Operation(summary = "查询应用信息详情")
    @SaCheckPermission("system:config:query")
    @GetMapping("/{configId}")
    public ConfigVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long configId) {
        Config config = configService.queryById(configId);
        return ObjectUtil.copyObject(config, ConfigVo.class);
    }

    /**
     * 新增应用信息
     */
    @Operation(summary = "新增应用信息")
    @SaCheckPermission("system:config:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody ConfigDto dto) {
        return configService.insertByDto(dto);
    }

    /**
     * 修改应用信息
     */
    @Operation(summary = "修改应用信息")
    @SaCheckPermission("system:config:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody ConfigDto dto) {
        return configService.updateByDto(dto);
    }

    /**
     * 删除应用信息
     *
     * @param configIds 主键串
     */
    @Operation(summary = "删除应用信息")
    @SaCheckPermission("system:config:remove")
    @DeleteMapping("/{configIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] configIds) {
        return configService.deleteWithValidByIds(Arrays.asList(configIds), true);
    }
}
