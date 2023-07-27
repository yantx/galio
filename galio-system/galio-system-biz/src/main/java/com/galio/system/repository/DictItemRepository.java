package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.DictItem;
import com.galio.system.mapper.DictItemMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典项Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class DictItemRepository{

    private final DictItemMapper dictItemMapper;

    /**
     * 查询字典项
     */
    public DictItem selectById(Long dictItemId) {
        return dictItemMapper.selectById(dictItemId);
    }

        /**
         * 查询字典项列表
         */
        public Page<DictItem> selectPage(Page page) {
            LambdaQueryWrapper<DictItem> lqw = Wrappers.lambdaQuery();
            return dictItemMapper.selectPage(page, lqw);
        }

    /**
     * 查询字典项列表
     */
    public List<DictItem> selectList(DictItem dictItem) {
        LambdaQueryWrapper<DictItem> lqw = buildQueryWrapper(dictItem);
        return dictItemMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<DictItem> buildQueryWrapper(DictItem entity) {
        LambdaQueryWrapper<DictItem> lqw = Wrappers.lambdaQuery();
                    lqw.eq(entity.getOrderNum() != null, DictItem::getOrderNum, entity.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(entity.getLabel()), DictItem::getLabel, entity.getLabel());
                    lqw.eq(StringUtil.isNotBlank(entity.getValue()), DictItem::getValue, entity.getValue());
                    lqw.eq(entity.getDictId() != null, DictItem::getDictId, entity.getDictId());
                    lqw.eq(StringUtil.isNotBlank(entity.getCssClass()), DictItem::getCssClass, entity.getCssClass());
                    lqw.eq(StringUtil.isNotBlank(entity.getListClass()), DictItem::getListClass, entity.getListClass());
                    lqw.eq(StringUtil.isNotBlank(entity.getIsDefault()), DictItem::getIsDefault, entity.getIsDefault());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), DictItem::getStatus, entity.getStatus());
                    lqw.eq(entity.getAppId() != null, DictItem::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增字典项
     */
    public int insert(DictItem entity) {
        validEntityBeforeSave(entity);
        int flag = dictItemMapper.insert(entity);
        return flag;
    }

    /**
     * 修改字典项
     */
    public int updateById(DictItem entity) {
        validEntityBeforeSave(entity);
        return dictItemMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DictItem entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除字典项
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return dictItemMapper.deleteBatchIds(ids);
    }
}
