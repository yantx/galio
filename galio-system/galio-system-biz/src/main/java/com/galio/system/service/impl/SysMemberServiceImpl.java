package com.galio.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.galio.system.entity.SysMember;
import com.galio.system.mapper.SysMemberMapper;
import com.galio.system.service.ISysMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class SysMemberServiceImpl implements ISysMemberService {

    private final SysMemberMapper sysMemberMapper;

    @Override
    public boolean saveBatch(List<SysMember> entityList) {
        return sysMemberMapper.insertBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(List<SysMember> entityList) {
        return sysMemberMapper.insertOrUpdateBatch(entityList);
    }

    @Override
    public boolean updateBatchById(List<SysMember> entityList) {
        return sysMemberMapper.updateBatchById(entityList);
    }

    @Override
    public boolean saveOrUpdate(SysMember entity) {
        return sysMemberMapper.insertOrUpdate(entity);
    }

    @Override
    public SysMember getOne(SysMember entity) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        return sysMemberMapper.selectOne(queryWrapper);
    }

    @Override
    public SysMember getById(Long id) {
        return sysMemberMapper.selectById(id);
    }

    @Override
    public List<SysMember> getList(SysMember entity) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        return sysMemberMapper.selectList(queryWrapper);
    }
}
