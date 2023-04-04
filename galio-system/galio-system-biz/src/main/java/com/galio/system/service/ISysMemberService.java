package com.galio.system.service;

import com.galio.system.entity.SysMember;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description:
 */
public interface ISysMemberService {

    boolean saveBatch(List<SysMember> entityList);

    boolean saveOrUpdateBatch(List<SysMember> entityList);

    boolean updateBatchById(List<SysMember> entityList);

    boolean saveOrUpdate(SysMember entity);

    SysMember getOne(SysMember entity);

    SysMember getById(Long id);

    List<SysMember> getList(SysMember entity);
}
