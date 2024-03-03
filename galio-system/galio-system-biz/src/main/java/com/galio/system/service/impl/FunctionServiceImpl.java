package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.FunctionDTO;
import com.galio.system.entity.Function;
import com.galio.system.repository.FunctionRepository;
import com.galio.system.service.FunctionService;

import java.util.*;

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
    public Function getById(Long functionId) {
        return functionRepository.selectById(functionId);
    }

        /**
         * 查询功能列表
         */
        @Override
        public Page<Function> listPage(PageRequestDTO pageRequestDTO) {
            return functionRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        }

    /**
     * 查询功能列表
     */
    @Override
    public List<Function> list(FunctionDTO dto) {
        Function entity = ObjectUtil.copyObject(dto, Function.class);
        
        return functionRepository.selectList(entity);
    }

    /**
     * 查询功能列表
     */
    @Override
    public List<Function> listByRoles(Collection<Long> roleIds){
        return functionRepository.selectByRoles(roleIds);
    }

    @Override
    public List<Function> getTree(List<Function> functionList) {
        Map<Long, Function> functionMap = new HashMap<>();
        List<Function> rootList = new ArrayList<>();
        // 将function对象放入Map中，以functionId作为key
        for (Function function : functionList) {
            functionMap.put(function.getFunctionId(), function);
        }

        // 遍历functionList，找到根节点和子节点，构建树结构
        for (Function function : functionList) {
            Long parentId = function.getParentId();
            if (parentId == null || parentId.equals(0L) || !functionMap.containsKey(parentId)) {
                // 根节点
                rootList.add(function);
            } else {
                // 子节点
                Function parent = functionMap.get(parentId);
                List<Function> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                }
                children.add(function);
                parent.setChildren(children);
            }
        }
        return rootList;
    }

    /**
     * 新增功能
     */
    @Override
    public Boolean save(FunctionDTO dto) {
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
    public Boolean update(FunctionDTO dto) {
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
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return functionRepository.deleteBatchIds(ids) > 0;
    }
}
