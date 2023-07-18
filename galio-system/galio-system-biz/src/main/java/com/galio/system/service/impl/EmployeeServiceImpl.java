package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.EmployeeDto;
import com.galio.system.model.Employee;
import com.galio.system.repository.EmployeeRepository;
import com.galio.system.service.EmployeeService;

import java.util.List;
import java.util.Map;
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

    /**
     * 查询雇员
     */
    @Override
    public Employee queryById(Long employeeId) {
        return employeeRepository.selectById(employeeId);
    }

        /**
         * 查询雇员列表
         */
        @Override
        public Page<Employee> queryPageList(PageRequestDto pageRequestDto) {
            return employeeRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询雇员列表
     */
    @Override
    public List<Employee> queryList(EmployeeDto dto) {
        Employee entity = ObjectUtil.copyObject(dto, Employee.class);
        Map<String, Object> params = dto.getParams();
        return employeeRepository.selectList(entity,params);
    }

    /**
     * 新增雇员
     */
    @Override
    public Boolean insertByDto(EmployeeDto dto) {
        Employee add = ObjectUtil.copyObject(dto, Employee.class);
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
    public Boolean updateByDto(EmployeeDto dto) {
        Employee update = ObjectUtil.copyObject(dto, Employee.class);
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return employeeRepository.deleteBatchIds(ids) > 0;
    }
}
