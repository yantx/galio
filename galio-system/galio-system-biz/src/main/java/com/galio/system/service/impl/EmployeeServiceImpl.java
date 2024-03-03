package com.galio.system.service.impl;

import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.system.model.converter.EmployeeConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.EmployeeDTO;
import com.galio.system.entity.Employee;
import com.galio.system.repository.EmployeeRepository;
import com.galio.system.service.EmployeeService;

import java.util.List;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 雇员Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeConvert convert;

    /**
     * 查询雇员
     */
    @Override
    public Employee getById(Long employeeId) {
        return employeeRepository.selectById(employeeId);
    }

        /**
         * 查询雇员列表
         */
        @Override
        public Page<Employee> listPage(PageRequestDTO pageRequestDTO) {
            return employeeRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        }

    /**
     * 查询雇员列表
     */
    @Override
    public List<Employee> list(EmployeeDTO dto) {
        Employee entity = convert.dtoToEntity(dto);
        
        return employeeRepository.selectList(entity);
    }

    /**
     * 新增雇员
     */
    @Override
    public Boolean save(EmployeeDTO dto) {
        Employee add = convert.dtoToEntity(dto);
        validEntityBeforeSave(add);
        boolean flag = employeeRepository.insert(add) > 0;
        if (flag) {
            dto.setEmployeeId(add.getEmployeeId());
        }
        return flag;
    }

    /**
     * 修改雇员
     */
    @Override
    public Boolean update(EmployeeDTO dto) {
        Employee update = convert.dtoToEntity(dto);
        validEntityBeforeSave(update);
        return employeeRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Employee entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除雇员
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return employeeRepository.deleteBatchIds(ids) > 0;
    }
}
