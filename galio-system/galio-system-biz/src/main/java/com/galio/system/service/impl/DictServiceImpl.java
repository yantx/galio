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
import com.galio.system.model.dto.DictDto;
import com.galio.system.model.vo.DictVo;
import com.galio.system.model.Dict;
import com.galio.system.mapper.DictMapper;
import com.galio.system.service.DictService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class DictServiceImpl implements DictService {

    private final DictMapper dictMapper;

    /**
     * 查询字典
     */
    @Override
    public DictVo queryById(Long dictId) {
        return dictMapper.selectVoById(dictId);
    }

        /**
         * 查询字典列表
         */
        @Override
        public PageVo<DictVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Dict> lqw = Wrappers.lambdaQuery();
            IPage<DictVo> pageData = dictMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询字典列表
     */
    @Override
    public List<DictVo> queryList(DictDto dto) {
        LambdaQueryWrapper<Dict> lqw = buildQueryWrapper(dto);
        return dictMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Dict> buildQueryWrapper(DictDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Dict> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getDictName()), Dict::getDictName, dto.getDictName());
                    lqw.eq(StringUtil.isNotBlank(dto.getDictCode()), Dict::getDictCode, dto.getDictCode());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Dict::getStatus, dto.getStatus());
                    lqw.eq(dto.getAppId() != null, Dict::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增字典
     */
    @Override
    public Boolean insertByDto(DictDto dto) {
        Dict add = ObjectUtil.copyObject(dto, Dict. class);
        validEntityBeforeSave(add);
        boolean flag = dictMapper.insert(add) > 0;
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
        Dict update = ObjectUtil.copyObject(dto, Dict. class);
        validEntityBeforeSave(update);
        return dictMapper.updateById(update) > 0;
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
        return dictMapper.deleteBatchIds(ids) > 0;
    }
}
