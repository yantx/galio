package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.DatasetDto;
import com.galio.system.model.vo.DatasetVo;
import com.galio.system.model.Dataset;
import com.galio.system.mapper.DatasetMapper;
import com.galio.system.service.DatasetService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据集信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetMapper datasetMapper;

    /**
     * 查询数据集信息
     */
    @Override
    public DatasetVo queryById(Long datasetId) {
        return datasetMapper.selectVoById(datasetId);
    }

        /**
         * 查询数据集信息列表
         */
        @Override
        public PageVo<DatasetVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Dataset> lqw = Wrappers.lambdaQuery();
            IPage<DatasetVo> pageData = datasetMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询数据集信息列表
     */
    @Override
    public List<DatasetVo> queryList(DatasetDto dto) {
        LambdaQueryWrapper<Dataset> lqw = buildQueryWrapper(dto);
        return datasetMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Dataset> buildQueryWrapper(DatasetDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Dataset> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getDatasourceId() != null, Dataset::getDatasourceId, dto.getDatasourceId());
                    lqw.eq(StringUtil.isNotBlank(dto.getDatasetCode()), Dataset::getDatasetCode, dto.getDatasetCode());
                    lqw.like(StringUtil.isNotBlank(dto.getDatasetName()), Dataset::getDatasetName, dto.getDatasetName());
                    lqw.eq(StringUtil.isNotBlank(dto.getDataContent()), Dataset::getDataContent, dto.getDataContent());
                    lqw.eq(StringUtil.isNotBlank(dto.getFetchType()), Dataset::getFetchType, dto.getFetchType());
                    lqw.eq(StringUtil.isNotBlank(dto.getDataTable()), Dataset::getDataTable, dto.getDataTable());
                    lqw.eq(StringUtil.isNotBlank(dto.getPrimaryKey()), Dataset::getPrimaryKey, dto.getPrimaryKey());
                    lqw.eq(StringUtil.isNotBlank(dto.getLabelCol()), Dataset::getLabelCol, dto.getLabelCol());
                    lqw.eq(StringUtil.isNotBlank(dto.getWhereClause()), Dataset::getWhereClause, dto.getWhereClause());
                    lqw.eq(StringUtil.isNotBlank(dto.getOrderCol()), Dataset::getOrderCol, dto.getOrderCol());
                    lqw.eq(dto.getAppId() != null, Dataset::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增数据集信息
     */
    @Override
    public Boolean insertByDto(DatasetDto dto) {
        Dataset add = ObjectUtil.copyObject(dto, Dataset. class);
        validEntityBeforeSave(add);
        boolean flag = datasetMapper.insert(add) > 0;
        if (flag) {
            dto.setDatasetId(add.getDatasetId());
        }
        return flag;
    }

    /**
     * 修改数据集信息
     */
    @Override
    public Boolean updateByDto(DatasetDto dto) {
        Dataset update = ObjectUtil.copyObject(dto, Dataset. class);
        validEntityBeforeSave(update);
        return datasetMapper.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return datasetMapper.deleteBatchIds(ids) > 0;
    }
}
