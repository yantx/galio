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
import com.galio.system.model.dto.DatasourceDto;
import com.galio.system.model.vo.DatasourceVo;
import com.galio.system.model.Datasource;
import com.galio.system.mapper.DatasourceMapper;
import com.galio.system.service.DatasourceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据源信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DatasourceServiceImpl implements DatasourceService {

    private final DatasourceMapper datasourceMapper;

    /**
     * 查询数据源信息
     */
    @Override
    public DatasourceVo queryById(Long datasourceId) {
        return datasourceMapper.selectVoById(datasourceId);
    }

        /**
         * 查询数据源信息列表
         */
        @Override
        public PageVo<DatasourceVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Datasource> lqw = Wrappers.lambdaQuery();
            IPage<DatasourceVo> pageData = datasourceMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询数据源信息列表
     */
    @Override
    public List<DatasourceVo> queryList(DatasourceDto dto) {
        LambdaQueryWrapper<Datasource> lqw = buildQueryWrapper(dto);
        return datasourceMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Datasource> buildQueryWrapper(DatasourceDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Datasource> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getDatasourceName()), Datasource::getDatasourceName, dto.getDatasourceName());
                    lqw.eq(StringUtil.isNotBlank(dto.getDatasourceType()), Datasource::getDatasourceType, dto.getDatasourceType());
                    lqw.eq(StringUtil.isNotBlank(dto.getDriverClass()), Datasource::getDriverClass, dto.getDriverClass());
                    lqw.eq(StringUtil.isNotBlank(dto.getUrl()), Datasource::getUrl, dto.getUrl());
                    lqw.like(StringUtil.isNotBlank(dto.getUsername()), Datasource::getUsername, dto.getUsername());
                    lqw.eq(StringUtil.isNotBlank(dto.getPassword()), Datasource::getPassword, dto.getPassword());
                    lqw.eq(StringUtil.isNotBlank(dto.getTestQuery()), Datasource::getTestQuery, dto.getTestQuery());
                    lqw.eq(dto.getAppId() != null, Datasource::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增数据源信息
     */
    @Override
    public Boolean insertByDto(DatasourceDto dto) {
        Datasource add = ObjectUtil.copyObject(dto, Datasource. class);
        validEntityBeforeSave(add);
        boolean flag = datasourceMapper.insert(add) > 0;
        if (flag) {
            dto.setDatasourceId(add.getDatasourceId());
        }
        return flag;
    }

    /**
     * 修改数据源信息
     */
    @Override
    public Boolean updateByDto(DatasourceDto dto) {
        Datasource update = ObjectUtil.copyObject(dto, Datasource. class);
        validEntityBeforeSave(update);
        return datasourceMapper.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return datasourceMapper.deleteBatchIds(ids) > 0;
    }
}
