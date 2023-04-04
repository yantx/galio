package com.galio.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.galio.system.entity.SysOrg;
import com.galio.system.mapper.SysOrgMapper;
import com.galio.system.service.ISysOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysOrgServiceImpl implements ISysOrgService {

    private final SysOrgMapper sysOrgMapper;

    @Override
    public SysOrg getOne(SysOrg entity) {
        QueryWrapper queryWrapper = new QueryWrapper<>(entity);
        return sysOrgMapper.selectOne(queryWrapper);
    }

    @Override
    public SysOrg getById(Long id) {
        return sysOrgMapper.selectById(id);
    }

    @Override
    public List<SysOrg> getList(SysOrg entity) {
        QueryWrapper queryWrapper = new QueryWrapper<>(entity);
        return sysOrgMapper.selectList(queryWrapper);
    }
}
