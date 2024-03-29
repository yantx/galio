package com.galio.system.controller;

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
import com.galio.system.entity.AccessLog;
import com.galio.system.model.vo.AccessLogVo;
import com.galio.system.dto.AccessLogDTO;
import com.galio.system.service.AccessLogService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统访问记录接口
 * 前端访问路由地址为:/system/access_log
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "系统访问记录API")
@RequestMapping({"/access_log","/remote/access_log"})
public class AccessLogController {

    private final AccessLogService accessLogService;

    /**
     * 查询系统访问记录列表
     */
    @Operation(summary = "查询系统访问记录列表")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<AccessLog> pageData = accessLogService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取系统访问记录详细信息
     *
     * @param accessId 主键
     */
    @Operation(summary = "查询系统访问记录详情")
    @GetMapping("/{accessId}")
    public AccessLogVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long accessId) {
        AccessLog accessLog = accessLogService.getById(accessId);
        return ObjectUtil.copyObject(accessLog, AccessLogVo.class);
    }

    /**
     * 新增系统访问记录
     */
    @Operation(summary = "新增系统访问记录")
    @PostMapping()
    public Boolean add(@Validated(InsertGroup.class) @RequestBody AccessLogDTO dto) {
        return accessLogService.save(dto);
    }

    /**
     * 修改系统访问记录
     */
    @Operation(summary = "修改系统访问记录")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody AccessLogDTO dto) {
        return accessLogService.update(dto);
    }

    /**
     * 删除系统访问记录
     *
     * @param accessIds 主键串
     */
    @Operation(summary = "删除系统访问记录")
    @DeleteMapping("/{accessIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] accessIds) {
        return accessLogService.deleteWithValidByIds(Arrays.asList(accessIds));
    }
}
