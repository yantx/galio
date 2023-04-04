package com.galio.system.service;

import com.galio.system.entity.SysOrg;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description:
 */
public interface ISysOrgService {

    SysOrg getOne(SysOrg entity);

    SysOrg getById(Long id);

    List<SysOrg> getList(SysOrg entity);
}
