package com.galio.system.service;

import com.galio.system.entity.SysEmployee;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 员工业务接口
 */
public interface ISysEmployeeService {

    boolean saveBatch(List<SysEmployee> entityList);

    boolean saveOrUpdateBatch(List<SysEmployee> entityList);

    boolean updateBatchById(List<SysEmployee> entityList);

    boolean saveOrUpdate(SysEmployee entity);

    SysEmployee getOne(SysEmployee entity);

    SysEmployee getById(Long id);

    List<SysEmployee> getList(SysEmployee entity);

}
