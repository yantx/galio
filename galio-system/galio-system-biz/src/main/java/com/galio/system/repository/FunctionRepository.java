package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.model.RoleFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Function;
import com.galio.system.mapper.FunctionMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 功能Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class FunctionRepository{

    private final FunctionMapper functionMapper;
    private final RoleFunctionRepository roleFunctionRepository;

    /**
     * 查询功能
     */
    public Function selectById(Long functionId) {
        return functionMapper.selectById(functionId);
    }

        /**
         * 查询功能列表
         */
        public Page<Function> selectPage(Page page) {
            LambdaQueryWrapper<Function> lqw = Wrappers.lambdaQuery();
            return functionMapper.selectPage(page, lqw);
        }

    /**
     * 查询功能列表
     */
    public List<Function> selectList(Function function) {
        LambdaQueryWrapper<Function> lqw = buildQueryWrapper(function);
        return functionMapper.selectList(lqw);
    }
    /**
     * 根据角色ID查询功能列表
     */
    public List<Function> selectList(Collection<Long> roleIds) {
        LambdaQueryWrapper<Function> lqw = Wrappers.lambdaQuery();
        List<RoleFunction> roleFunctions = roleFunctionRepository.selectList(roleIds);
        Set<Long> functionIds = roleFunctions.stream().map(RoleFunction::getFunctionId).collect(Collectors.toSet());
        lqw.in(Function::getFunctionId, functionIds);
        return functionMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Function> buildQueryWrapper(Function entity) {
        LambdaQueryWrapper<Function> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getFunctionName()), Function::getFunctionName, entity.getFunctionName());
                    lqw.eq(entity.getParentId() != null, Function::getParentId, entity.getParentId());
                    lqw.eq(entity.getOrderNum() != null, Function::getOrderNum, entity.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(entity.getPath()), Function::getPath, entity.getPath());
                    lqw.eq(StringUtil.isNotBlank(entity.getComponent()), Function::getComponent, entity.getComponent());
                    lqw.eq(StringUtil.isNotBlank(entity.getQueryParam()), Function::getQueryParam, entity.getQueryParam());
                    lqw.eq(StringUtil.isNotBlank(entity.getIsFrame()), Function::getIsFrame, entity.getIsFrame());
                    lqw.eq(StringUtil.isNotBlank(entity.getIsCache()), Function::getIsCache, entity.getIsCache());
                    lqw.eq(StringUtil.isNotBlank(entity.getFunctionType()), Function::getFunctionType, entity.getFunctionType());
                    lqw.eq(StringUtil.isNotBlank(entity.getBelong()), Function::getBelong, entity.getBelong());
                    lqw.eq(StringUtil.isNotBlank(entity.getVisible()), Function::getVisible, entity.getVisible());
                    lqw.eq(StringUtil.isNotBlank(entity.getDeleteFlag()), Function::getDeleteFlag, entity.getDeleteFlag());
                    lqw.eq(StringUtil.isNotBlank(entity.getPerms()), Function::getPerms, entity.getPerms());
                    lqw.eq(StringUtil.isNotBlank(entity.getIcon()), Function::getIcon, entity.getIcon());
                    lqw.eq(entity.getAppId() != null, Function::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增功能
     */
    public int insert(Function entity) {
        validEntityBeforeSave(entity);
        int flag = functionMapper.insert(entity);
        return flag;
    }

    /**
     * 修改功能
     */
    public int updateById(Function entity) {
        validEntityBeforeSave(entity);
        return functionMapper.updateById(entity);
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
    public int deleteBatchIds(Collection<Long> ids) {
        return functionMapper.deleteBatchIds(ids);
    }
}
