package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import com.galio.system.entity.Employee;
import com.galio.system.model.vo.EmployeeVo;
import com.galio.system.dto.EmployeeDTO;
import com.galio.system.service.EmployeeService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 雇员接口
 * 前端访问路由地址为:/system/employee
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "雇员API")
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 查询雇员列表
     */
    @Operation(summary = "查询雇员列表")
    @SaCheckPermission("system:employee:page")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<Employee> pageData = employeeService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取i详细信息
     *
     * @param employeeId 主键
     */
    @Operation(summary = "查询雇员详情")
    @SaCheckPermission("system:employee:query")
    @GetMapping("/{employeeId}")
    public EmployeeVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return ObjectUtil.copyObject(employee, EmployeeVo.class);
    }

    /**
     * 新增雇员
     */
    @Operation(summary = "新增雇员")
    @SaCheckPermission("system:employee:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody EmployeeDTO dto) {
        return employeeService.save(dto);
    }

    /**
     * 修改雇员
     */
    @Operation(summary = "修改雇员")
    @SaCheckPermission("system:employee:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody EmployeeDTO dto) {
        return employeeService.update(dto);
    }

    /**
     * 删除雇员
     *
     * @param employeeIds 主键串
     */
    @Operation(summary = "删除雇员")
    @SaCheckPermission("system:employee:remove")
    @DeleteMapping("/{employeeIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] employeeIds) {
        return employeeService.deleteWithValidByIds(Arrays.asList(employeeIds));
    }
}
