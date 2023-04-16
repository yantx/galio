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
import com.galio.system.model.vo.ConfigVo;
import com.galio.system.model.dto.ConfigDto;
import com.galio.system.service.ConfigService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息接口
 * 前端访问路由地址为:/system/config
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("system:config:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return configService.queryPageList(pageDto);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param configId 主键
     */
    @SaCheckPermission("system:config:query")
    @GetMapping("/{configId}")
    public ConfigVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long configId) {
        return configService.queryById(configId);
    }

    /**
     * 新增应用信息
     */
    @SaCheckPermission("system:config:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody ConfigDto dto) {
        return configService.insertByDto(dto);
    }

    /**
     * 修改应用信息
     */
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
    @SaCheckPermission("system:config:remove")
    @DeleteMapping("/{configIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] configIds) {
        return configService.deleteWithValidByIds(Arrays.asList(configIds), true);
    }
}
