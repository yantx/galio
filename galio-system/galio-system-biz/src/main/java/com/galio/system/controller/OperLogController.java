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
import com.galio.system.model.OperLog;
import com.galio.system.model.vo.OperLogVo;
import com.galio.system.dto.OperLogDto;
import com.galio.system.service.OperLogService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 操作日志记录接口
 * 前端访问路由地址为:/system/operLog
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "操作日志记录API")
@RequestMapping("/operLog")
public class OperLogController {

    private final OperLogService operLogService;

    /**
     * 查询操作日志记录列表
     */
    @Operation(summary = "查询操作日志记录列表")
    @SaCheckPermission("system:operLog:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<OperLog> pageData = operLogService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取操作日志记录详细信息
     *
     * @param operId 主键
     */
    @Operation(summary = "查询操作日志记录详情")
    @SaCheckPermission("system:operLog:query")
    @GetMapping("/{operId}")
    public OperLogVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long operId) {
        OperLog operLog = operLogService.queryById(operId);
        return ObjectUtil.copyObject(operLog, OperLogVo.class);
    }

    /**
     * 新增操作日志记录
     */
    @Operation(summary = "新增操作日志记录")
    @SaCheckPermission("system:operLog:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody OperLogDto dto) {
        return operLogService.insertByDto(dto);
    }

    /**
     * 修改操作日志记录
     */
    @Operation(summary = "修改操作日志记录")
    @SaCheckPermission("system:operLog:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody OperLogDto dto) {
        return operLogService.updateByDto(dto);
    }

    /**
     * 删除操作日志记录
     *
     * @param operIds 主键串
     */
    @Operation(summary = "删除操作日志记录")
    @SaCheckPermission("system:operLog:remove")
    @DeleteMapping("/{operIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] operIds) {
        return operLogService.deleteWithValidByIds(Arrays.asList(operIds), true);
    }
}
