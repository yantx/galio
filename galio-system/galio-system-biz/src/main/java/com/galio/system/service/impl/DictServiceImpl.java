package com.galio.system.service.impl;

import com.galio.core.constant.CacheConstants;
import com.galio.core.constant.CommonConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.redis.util.CacheUtils;
import com.galio.system.dto.DictItemDto;
import com.galio.system.model.DictItem;
import com.galio.system.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.galio.system.dto.DictDto;
import com.galio.system.model.Dict;
import com.galio.system.repository.DictRepository;
import com.galio.system.service.DictService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典Service业务层处理
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;
    private final DictItemService dictItemService;

    /**
     * 查询字典
     */
    @Override
    public Dict queryById(Long dictId) {
        return dictRepository.selectById(dictId);
    }

        /**
         * 查询字典列表
         */
        @Override
        public Page<Dict> queryPageList(PageRequestDto pageRequestDto) {
            return dictRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询字典列表
     */
    @Override
    public List<Dict> queryList(DictDto dto) {
        Dict entity = ObjectUtil.copyObject(dto, Dict.class);
        Map<String, Object> params = dto.getParams();
        return dictRepository.selectList(entity,params);
    }

    /**
     * 新增字典
     */
    @Override
    public Boolean insertByDto(DictDto dto) {
        Dict add = ObjectUtil.copyObject(dto, Dict.class);
        validEntityBeforeSave(add);
        boolean flag = dictRepository.insert(add) > 0;
        if (flag) {
            dto.setDictId(add.getDictId());
        }
        return flag;
    }

    /**
     * 修改字典
     */
    @Override
    public Boolean updateByDto(DictDto dto) {
        Dict update = ObjectUtil.copyObject(dto, Dict.class);
        validEntityBeforeSave(update);
        return dictRepository.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return dictRepository.deleteBatchIds(ids) > 0;
    }

    @Override
    public void loadingDictCache() {
        List<Dict> dicts = queryList(new DictDto());
        DictItemDto itemDto = new DictItemDto();
        itemDto.setStatus(CommonConstants.NORMAL);
        List<DictItem> items = dictItemService.queryList(itemDto);
        Map<Long, List<DictItem>> dictItemMap =  items.stream().collect(Collectors.groupingBy(DictItem::getDictId));
        dicts.forEach(dict ->
                CacheUtils.put(CacheConstants.SYS_DICT_NAMESPACE, dict.getDictCode(), dictItemMap.get(dict.getDictId())));
    }
}
