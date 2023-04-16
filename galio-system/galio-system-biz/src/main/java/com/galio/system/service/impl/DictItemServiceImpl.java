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
import com.galio.system.model.dto.DictItemDto;
import com.galio.system.model.vo.DictItemVo;
import com.galio.system.model.DictItem;
import com.galio.system.mapper.DictItemMapper;
import com.galio.system.service.DictItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典项Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DictItemServiceImpl implements DictItemService {

    private final DictItemMapper dictItemMapper;

    /**
     * 查询字典项
     */
    @Override
    public DictItemVo queryById(Long dictItemId) {
        return dictItemMapper.selectVoById(dictItemId);
    }

        /**
         * 查询字典项列表
         */
        @Override
        public PageVo<DictItemVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<DictItem> lqw = Wrappers.lambdaQuery();
            IPage<DictItemVo> pageData = dictItemMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询字典项列表
     */
    @Override
    public List<DictItemVo> queryList(DictItemDto dto) {
        LambdaQueryWrapper<DictItem> lqw = buildQueryWrapper(dto);
        return dictItemMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DictItem> buildQueryWrapper(DictItemDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<DictItem> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getOrderNum() != null, DictItem::getOrderNum, dto.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(dto.getLabel()), DictItem::getLabel, dto.getLabel());
                    lqw.eq(StringUtil.isNotBlank(dto.getValue()), DictItem::getValue, dto.getValue());
                    lqw.eq(StringUtil.isNotBlank(dto.getDictId()), DictItem::getDictId, dto.getDictId());
                    lqw.eq(StringUtil.isNotBlank(dto.getCssClass()), DictItem::getCssClass, dto.getCssClass());
                    lqw.eq(StringUtil.isNotBlank(dto.getListClass()), DictItem::getListClass, dto.getListClass());
                    lqw.eq(StringUtil.isNotBlank(dto.getIsDefault()), DictItem::getIsDefault, dto.getIsDefault());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), DictItem::getStatus, dto.getStatus());
                    lqw.eq(dto.getAppId() != null, DictItem::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增字典项
     */
    @Override
    public Boolean insertByDto(DictItemDto dto) {
        DictItem add = ObjectUtil.copyObject(dto, DictItem. class);
        validEntityBeforeSave(add);
        boolean flag = dictItemMapper.insert(add) > 0;
        if (flag) {
            dto.setDictItemId(add.getDictItemId());
        }
        return flag;
    }

    /**
     * 修改字典项
     */
    @Override
    public Boolean updateByDto(DictItemDto dto) {
        DictItem update = ObjectUtil.copyObject(dto, DictItem. class);
        validEntityBeforeSave(update);
        return dictItemMapper.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return dictItemMapper.deleteBatchIds(ids) > 0;
    }
}
