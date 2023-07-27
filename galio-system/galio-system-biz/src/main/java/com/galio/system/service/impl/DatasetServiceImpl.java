package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.DatasetDto;
import com.galio.system.model.Dataset;
import com.galio.system.repository.DatasetRepository;
import com.galio.system.service.DatasetService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据集信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;

    /**
     * 查询数据集信息
     */
    @Override
    public Dataset queryById(Long datasetId) {
        return datasetRepository.selectById(datasetId);
    }

        /**
         * 查询数据集信息列表
         */
        @Override
        public Page<Dataset> queryPageList(PageRequestDto pageRequestDto) {
            return datasetRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询数据集信息列表
     */
    @Override
    public List<Dataset> queryList(DatasetDto dto) {
        Dataset entity = ObjectUtil.copyObject(dto, Dataset.class);
        
        return datasetRepository.selectList(entity);
    }

    /**
     * 新增数据集信息
     */
    @Override
    public Boolean insertByDto(DatasetDto dto) {
        Dataset add = ObjectUtil.copyObject(dto, Dataset.class);
        validEntityBeforeSave(add);
        boolean flag = datasetRepository.insert(add) > 0;
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
        Dataset update = ObjectUtil.copyObject(dto, Dataset.class);
        validEntityBeforeSave(update);
        return datasetRepository.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return datasetRepository.deleteBatchIds(ids) > 0;
    }
}
