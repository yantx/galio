package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.FunctionDto;
import com.galio.system.model.Function;
import com.galio.system.repository.FunctionRepository;
import com.galio.system.service.FunctionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 功能Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class FunctionServiceImpl implements FunctionService {

    private final FunctionRepository functionRepository;

    /**
     * 查询功能
     */
    @Override
    public Function queryById(Long functionId) {
        return functionRepository.selectById(functionId);
    }

        /**
         * 查询功能列表
         */
        @Override
        public Page<Function> queryPageList(PageRequestDto pageRequestDto) {
            return functionRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询功能列表
     */
    @Override
    public List<Function> queryList(FunctionDto dto) {
        Function entity = ObjectUtil.copyObject(dto, Function.class);
        Map<String, Object> params = dto.getParams();
        return functionRepository.selectList(entity,params);
    }

    /**
     * 新增功能
     */
    @Override
    public Boolean insertByDto(FunctionDto dto) {
        Function add = ObjectUtil.copyObject(dto, Function.class);
        validEntityBeforeSave(add);
        boolean flag = functionRepository.insert(add) > 0;
        if (flag) {
            dto.setFunctionId(add.getFunctionId());
        }
        return flag;
    }

    /**
     * 修改功能
     */
    @Override
    public Boolean updateByDto(FunctionDto dto) {
        Function update = ObjectUtil.copyObject(dto, Function.class);
        validEntityBeforeSave(update);
        return functionRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Function entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除功能
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return functionRepository.deleteBatchIds(ids) > 0;
    }
}
