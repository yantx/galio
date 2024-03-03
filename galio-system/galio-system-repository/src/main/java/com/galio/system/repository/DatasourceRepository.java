package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.utils.StringUtil;
import com.galio.system.mapper.DatasourceMapper;
import com.galio.system.entity.Datasource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据源信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class DatasourceRepository{

    private final DatasourceMapper datasourceMapper;

    /**
     * 查询数据源信息
     */
    public Datasource selectById(Long datasourceId) {
        return datasourceMapper.selectById(datasourceId);
    }

        /**
         * 查询数据源信息列表
         */
        public Page<Datasource> selectPage(Page page) {
            LambdaQueryWrapper<Datasource> lqw = Wrappers.lambdaQuery();
            return datasourceMapper.selectPage(page, lqw);
        }

    /**
     * 查询数据源信息列表
     */
    public List<Datasource> selectList(Datasource datasource) {
        LambdaQueryWrapper<Datasource> lqw = buildQueryWrapper(datasource);
        return datasourceMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Datasource> buildQueryWrapper(Datasource entity) {
        LambdaQueryWrapper<Datasource> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getDatasourceName()), Datasource::getDatasourceName, entity.getDatasourceName());
                    lqw.eq(StringUtil.isNotBlank(entity.getDatasourceType()), Datasource::getDatasourceType, entity.getDatasourceType());
                    lqw.eq(StringUtil.isNotBlank(entity.getDriverClass()), Datasource::getDriverClass, entity.getDriverClass());
                    lqw.eq(StringUtil.isNotBlank(entity.getUrl()), Datasource::getUrl, entity.getUrl());
                    lqw.like(StringUtil.isNotBlank(entity.getUsername()), Datasource::getUsername, entity.getUsername());
                    lqw.eq(StringUtil.isNotBlank(entity.getPassword()), Datasource::getPassword, entity.getPassword());
                    lqw.eq(StringUtil.isNotBlank(entity.getTestQuery()), Datasource::getTestQuery, entity.getTestQuery());
                    lqw.eq(entity.getAppId() != null, Datasource::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增数据源信息
     */
    public int insert(Datasource entity) {
        validEntityBeforeSave(entity);
        int flag = datasourceMapper.insert(entity);
        return flag;
    }

    /**
     * 修改数据源信息
     */
    public int updateById(Datasource entity) {
        validEntityBeforeSave(entity);
        return datasourceMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Datasource entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除数据源信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return datasourceMapper.deleteBatchIds(ids);
    }
}
