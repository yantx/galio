package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Dataset;
import com.galio.system.mapper.DatasetMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据集信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class DatasetRepository{

    private final DatasetMapper datasetMapper;

    /**
     * 查询数据集信息
     */
    public Dataset selectById(Long datasetId) {
        return datasetMapper.selectById(datasetId);
    }

        /**
         * 查询数据集信息列表
         */
        public Page<Dataset> selectPage(Page page) {
            LambdaQueryWrapper<Dataset> lqw = Wrappers.lambdaQuery();
            return datasetMapper.selectPage(page, lqw);
        }

    /**
     * 查询数据集信息列表
     */
    public List<Dataset> selectList(Dataset dataset,Map<String, Object> params) {
        LambdaQueryWrapper<Dataset> lqw = buildQueryWrapper(dataset, params);
        return datasetMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Dataset> buildQueryWrapper(Dataset entity,Map<String, Object> params) {
        LambdaQueryWrapper<Dataset> lqw = Wrappers.lambdaQuery();
                    lqw.eq(entity.getDatasourceId() != null, Dataset::getDatasourceId, entity.getDatasourceId());
                    lqw.eq(StringUtil.isNotBlank(entity.getDatasetCode()), Dataset::getDatasetCode, entity.getDatasetCode());
                    lqw.like(StringUtil.isNotBlank(entity.getDatasetName()), Dataset::getDatasetName, entity.getDatasetName());
                    lqw.eq(StringUtil.isNotBlank(entity.getDataContent()), Dataset::getDataContent, entity.getDataContent());
                    lqw.eq(StringUtil.isNotBlank(entity.getFetchType()), Dataset::getFetchType, entity.getFetchType());
                    lqw.eq(StringUtil.isNotBlank(entity.getDataTable()), Dataset::getDataTable, entity.getDataTable());
                    lqw.eq(StringUtil.isNotBlank(entity.getPrimaryKey()), Dataset::getPrimaryKey, entity.getPrimaryKey());
                    lqw.eq(StringUtil.isNotBlank(entity.getLabelCol()), Dataset::getLabelCol, entity.getLabelCol());
                    lqw.eq(StringUtil.isNotBlank(entity.getWhereClause()), Dataset::getWhereClause, entity.getWhereClause());
                    lqw.eq(StringUtil.isNotBlank(entity.getOrderCol()), Dataset::getOrderCol, entity.getOrderCol());
                    lqw.eq(entity.getAppId() != null, Dataset::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增数据集信息
     */
    public int insert(Dataset entity) {
        validEntityBeforeSave(entity);
        int flag = datasetMapper.insert(entity);
        return flag;
    }

    /**
     * 修改数据集信息
     */
    public int updateById(Dataset entity) {
        validEntityBeforeSave(entity);
        return datasetMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Dataset entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除数据集信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return datasetMapper.deleteBatchIds(ids);
    }
}
