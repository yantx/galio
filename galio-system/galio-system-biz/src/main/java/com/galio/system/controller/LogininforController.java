package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Logininfor;
import com.galio.system.model.vo.LogininforVo;
import com.galio.system.dto.LogininforDto;
import com.galio.system.service.LogininforService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统访问记录接口
 * 前端访问路由地址为:/system/logininfor
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "系统访问记录API")
@RequestMapping("/logininfor")
public class LogininforController {

    private final LogininforService logininforService;

    /**
     * 查询系统访问记录列表
     */
    @Operation(summary = "查询系统访问记录列表")
    @SaCheckPermission("system:logininfor:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageRequestDto pageRequestDto) {
        IPage<Logininfor> pageData = logininforService.queryPageList(pageRequestDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取系统访问记录详细信息
     *
     * @param infoId 主键
     */
    @Operation(summary = "查询系统访问记录详情")
    @SaCheckPermission("system:logininfor:query")
    @GetMapping("/{infoId}")
    public LogininforVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long infoId) {
        Logininfor logininfor = logininforService.queryById(infoId);
        return ObjectUtil.copyObject(logininfor, LogininforVo.class);
    }

    /**
     * 新增系统访问记录
     */
    @Operation(summary = "新增系统访问记录")
    @SaCheckPermission("system:logininfor:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody LogininforDto dto) {
        return logininforService.insertByDto(dto);
    }

    /**
     * 修改系统访问记录
     */
    @Operation(summary = "修改系统访问记录")
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
    @Operation(summary = "删除系统访问记录")
    @SaCheckPermission("system:logininfor:remove")
    @DeleteMapping("/{infoIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] infoIds) {
        return logininforService.deleteWithValidByIds(Arrays.asList(infoIds));
    }
}
