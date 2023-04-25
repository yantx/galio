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
import com.galio.system.model.App;
import com.galio.system.model.vo.AppVo;
import com.galio.system.model.dto.AppDto;
import com.galio.system.service.AppService;

import java.util.List;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class AppController {

    private final AppService appService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("system:app:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<App> pageData = appService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("system:app:query")
    @GetMapping("/{appId}")
    public AppVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long appId) {
        App app = appService.queryById(appId);
        return ObjectUtil.copyObject(app, AppVo.class);
    }

    /**
     * 新增应用信息
     */
    @SaCheckPermission("system:app:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody AppDto dto) {
        return appService.insertByDto(dto);
    }

    /**
     * 修改应用信息
     */
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
    @SaCheckPermission("system:app:remove")
    @DeleteMapping("/{appIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] appIds) {
        return appService.deleteWithValidByIds(Arrays.asList(appIds), true);
    }
}
