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
import com.galio.system.model.vo.LogininforVo;
import com.galio.system.model.dto.LogininforDto;
import com.galio.system.service.LogininforService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 系统访问记录接口
 * 前端访问路由地址为:/system/logininfor
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/logininfor")
public class LogininforController {

    private final LogininforService logininforService;

    /**
     * 查询系统访问记录列表
     */
    @SaCheckPermission("system:logininfor:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return logininforService.queryPageList(pageDto);
    }

    /**
     * 获取系统访问记录详细信息
     *
     * @param infoId 主键
     */
    @SaCheckPermission("system:logininfor:query")
    @GetMapping("/{infoId}")
    public LogininforVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long infoId) {
        return logininforService.queryById(infoId);
    }

    /**
     * 新增系统访问记录
     */
    @SaCheckPermission("system:logininfor:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody LogininforDto dto) {
        return logininforService.insertByDto(dto);
    }

    /**
     * 修改系统访问记录
     */
    @SaCheckPermission("system:logininfor:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody LogininforDto dto) {
        return logininforService.updateByDto(dto);
    }

    /**
     * 删除系统访问记录
     *
     * @param infoIds 主键串
     */
    @SaCheckPermission("system:logininfor:remove")
    @DeleteMapping("/{infoIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] infoIds) {
        return logininforService.deleteWithValidByIds(Arrays.asList(infoIds), true);
    }
}
