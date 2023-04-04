package com.galio.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.galio.system.entity.SysEmployee;
import com.galio.system.mapper.SysEmployeeMapper;
import com.galio.system.service.ISysEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 员工业务
 */
@Service
@RequiredArgsConstructor
public class SysEmployeeServiceImpl implements ISysEmployeeService {

    private final SysEmployeeMapper sysEmployeeMapper;

    @Override
    public boolean saveBatch(List<SysEmployee> entityList) {
        return sysEmployeeMapper.insertBatch(entityList,entityList.size());
    }

    @Override
    public boolean saveOrUpdateBatch(List<SysEmployee> entityList) {
        return sysEmployeeMapper.insertOrUpdateBatch(entityList);
    }

    @Override
    public boolean updateBatchById(List<SysEmployee> entityList) {
        return sysEmployeeMapper.updateBatchById(entityList);
    }

    @Override
    public boolean saveOrUpdate(SysEmployee entity) {
        return sysEmployeeMapper.insertOrUpdate(entity);
    }

    @Override
    public SysEmployee getOne(SysEmployee entity) {
        QueryWrapper<SysEmployee> employeeQueryWrapper = new QueryWrapper<>();
        return sysEmployeeMapper.selectOne(employeeQueryWrapper);
    }

    @Override
    public SysEmployee getById(Long id) {
        return sysEmployeeMapper.selectById(id);
    }

    @Override
    public List<SysEmployee> getList(SysEmployee entity) {
        QueryWrapper<SysEmployee> employeeQueryWrapper = new QueryWrapper<>();
        return sysEmployeeMapper.selectList(employeeQueryWrapper);
    }
}
