package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.DictItemDTO;
import com.galio.system.entity.DictItem;
import com.galio.system.repository.DictItemRepository;
import com.galio.system.service.DictItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典项Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DictItemServiceImpl implements DictItemService {

    private final DictItemRepository dictItemRepository;

    /**
     * 查询字典项
     */
    @Override
    public DictItem getById(Long dictItemId) {
        return dictItemRepository.selectById(dictItemId);
    }

        /**
         * 查询字典项列表
         */
        @Override
        public Page<DictItem> listPage(PageRequestDTO pageRequestDTO) {
            return dictItemRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        }

    /**
     * 查询字典项列表
     */
    @Override
    public List<DictItem> list(DictItemDTO dto) {
        DictItem entity = ObjectUtil.copyObject(dto, DictItem.class);
        
        return dictItemRepository.selectList(entity);
    }

    /**
     * 新增字典项
     */
    @Override
    public Boolean save(DictItemDTO dto) {
        DictItem add = ObjectUtil.copyObject(dto, DictItem.class);
        validEntityBeforeSave(add);
        boolean flag = dictItemRepository.insert(add) > 0;
        if (flag) {
            dto.setDictItemId(add.getDictItemId());
        }
        return flag;
    }

    /**
     * 修改字典项
     */
    @Override
    public Boolean update(DictItemDTO dto) {
        DictItem update = ObjectUtil.copyObject(dto, DictItem.class);
        validEntityBeforeSave(update);
        return dictItemRepository.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return dictItemRepository.deleteBatchIds(ids) > 0;
    }
}
