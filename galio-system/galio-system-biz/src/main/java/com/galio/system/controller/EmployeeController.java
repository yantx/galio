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
import com.galio.system.model.vo.EmployeeVo;
import com.galio.system.model.dto.EmployeeDto;
import com.galio.system.service.EmployeeService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构接口
 * 前端访问路由地址为:/system/employee
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 查询机构列表
     */
    @SaCheckPermission("system:employee:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return employeeService.queryPageList(pageDto);
    }

    /**
     * 获取机构详细信息
     *
     * @param employeeId 主键
     */
    @SaCheckPermission("system:employee:query")
    @GetMapping("/{employeeId}")
    public EmployeeVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long employeeId) {
        return employeeService.queryById(employeeId);
    }

    /**
     * 新增机构
     */
    @SaCheckPermission("system:employee:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody EmployeeDto dto) {
        return employeeService.insertByDto(dto);
    }

    /**
     * 修改机构
     */
    @SaCheckPermission("system:employee:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody EmployeeDto dto) {
        return employeeService.updateByDto(dto);
    }

    /**
     * 删除机构
     *
     * @param employeeIds 主键串
     */
    @SaCheckPermission("system:employee:remove")
    @DeleteMapping("/{employeeIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] employeeIds) {
        return employeeService.deleteWithValidByIds(Arrays.asList(employeeIds), true);
    }
}
