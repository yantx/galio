package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.DatasourceDTO;
import com.galio.system.entity.Datasource;
import com.galio.system.repository.DatasourceRepository;
import com.galio.system.service.DatasourceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据源信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DatasourceServiceImpl implements DatasourceService {

    private final DatasourceRepository datasourceRepository;

    /**
     * 查询数据源信息
     */
    @Override
    public Datasource getById(Long datasourceId) {
        return datasourceRepository.selectById(datasourceId);
    }

        /**
         * 查询数据源信息列表
         */
        @Override
        public Page<Datasource> listPage(PageRequestDTO pageRequestDTO) {
            return datasourceRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        }

    /**
     * 查询数据源信息列表
     */
    @Override
    public List<Datasource> list(DatasourceDTO dto) {
        Datasource entity = ObjectUtil.copyObject(dto, Datasource.class);
        
        return datasourceRepository.selectList(entity);
    }

    /**
     * 新增数据源信息
     */
    @Override
    public Boolean save(DatasourceDTO dto) {
        Datasource add = ObjectUtil.copyObject(dto, Datasource.class);
        validEntityBeforeSave(add);
        boolean flag = datasourceRepository.insert(add) > 0;
        if (flag) {
            dto.setDatasourceId(add.getDatasourceId());
        }
        return flag;
    }

    /**
     * 修改数据源信息
     */
    @Override
    public Boolean update(DatasourceDTO dto) {
        Datasource update = ObjectUtil.copyObject(dto, Datasource.class);
        validEntityBeforeSave(update);
        return datasourceRepository.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return datasourceRepository.deleteBatchIds(ids) > 0;
    }
}
