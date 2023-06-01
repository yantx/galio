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
import com.galio.system.model.Employee;
import com.galio.system.model.vo.EmployeeVo;
import com.galio.system.dto.EmployeeDto;
import com.galio.system.service.EmployeeService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 机构接口
 * 前端访问路由地址为:/system/employee
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "机构API")
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 查询机构列表
     */
    @Operation(summary = "查询机构列表")
    @SaCheckPermission("system:employee:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Employee> pageData = employeeService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取机构详细信息
     *
     * @param employeeId 主键
     */
    @Operation(summary = "查询机构详情")
    @SaCheckPermission("system:employee:query")
    @GetMapping("/{employeeId}")
    public EmployeeVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long employeeId) {
        Employee employee = employeeService.queryById(employeeId);
        return ObjectUtil.copyObject(employee, EmployeeVo.class);
    }

    /**
     * 新增机构
     */
    @Operation(summary = "新增机构")
    @SaCheckPermission("system:employee:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody EmployeeDto dto) {
        return employeeService.insertByDto(dto);
    }

    /**
     * 修改机构
     */
    @Operation(summary = "修改机构")
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
    @Operation(summary = "删除机构")
    @SaCheckPermission("system:employee:remove")
    @DeleteMapping("/{employeeIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] employeeIds) {
        return employeeService.deleteWithValidByIds(Arrays.asList(employeeIds), true);
    }
}
