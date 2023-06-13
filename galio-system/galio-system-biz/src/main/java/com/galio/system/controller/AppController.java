package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.common.log.annotation.OperLog;
import com.galio.common.log.enums.OperTypeEnum;
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
import com.galio.system.model.App;
import com.galio.system.model.vo.AppVo;
import com.galio.system.dto.AppDto;
import com.galio.system.service.AppService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息接口
 * 前端访问路由地址为:/system/app
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "应用信息API")
@RequestMapping("/app")
public class AppController {

    private final AppService appService;

    /**
     * 查询应用信息列表
     */

    @Operation(summary = "查询应用信息列表")
    @SaCheckPermission("system:app:page")
    @PostMapping("/page")
    @OperLog(operModul = "应用信息-分页查询应用信息列表", operType = OperTypeEnum.SELECT)
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<App> pageData = appService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param appId 主键
     */
    @Operation(summary = "查询应用信息详情")
    @SaCheckPermission("system:app:query")
    @GetMapping("/{appId}")
    public AppVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long appId) {
        App app = appService.queryById(appId);
        return ObjectUtil.copyObject(app, AppVo.class);
    }

    /**
     * 新增应用信息
     */
    @Operation(summary = "新增应用信息")
    @SaCheckPermission("system:app:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody AppDto dto) {
        return appService.insertByDto(dto);
    }

    /**
     * 修改应用信息
     */
    @Operation(summary = "修改应用信息")
    @SaCheckPermission("system:app:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody AppDto dto) {
        return appService.updateByDto(dto);
    }

    /**
     * 删除应用信息
     *
     * @param appIds 主键串
     */
    @Operation(summary = "删除应用信息")
    @SaCheckPermission("system:app:remove")
    @DeleteMapping("/{appIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] appIds) {
        return appService.deleteWithValidByIds(Arrays.asList(appIds), true);
    }
}
