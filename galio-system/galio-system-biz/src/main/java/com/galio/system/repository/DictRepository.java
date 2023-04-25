package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Dict;
import com.galio.system.mapper.DictMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class DictRepository{

    private final DictMapper dictMapper;

    /**
     * 查询字典
     */
    public Dict selectById(Long dictId) {
        return dictMapper.selectById(dictId);
    }

        /**
         * 查询字典列表
         */
        public Page<Dict> selectPage(Page page) {
            LambdaQueryWrapper<Dict> lqw = Wrappers.lambdaQuery();
            return dictMapper.selectPage(page, lqw);
        }

    /**
     * 查询字典列表
     */
    public List<Dict> selectList(Dict dict,Map<String, Object> params) {
        LambdaQueryWrapper<Dict> lqw = buildQueryWrapper(dict, params);
        return dictMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Dict> buildQueryWrapper(Dict entity,Map<String, Object> params) {
        LambdaQueryWrapper<Dict> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getDictName()), Dict::getDictName, entity.getDictName());
                    lqw.eq(StringUtil.isNotBlank(entity.getDictCode()), Dict::getDictCode, entity.getDictCode());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Dict::getStatus, entity.getStatus());
                    lqw.eq(entity.getAppId() != null, Dict::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增字典
     */
    public int insert(Dict entity) {
        validEntityBeforeSave(entity);
        int flag = dictMapper.insert(entity);
        return flag;
    }

    /**
     * 修改字典
     */
    public int updateById(Dict entity) {
        validEntityBeforeSave(entity);
        return dictMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Dict entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除字典
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return dictMapper.deleteBatchIds(ids);
    }
}
