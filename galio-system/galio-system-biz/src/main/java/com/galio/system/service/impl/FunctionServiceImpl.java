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
import com.galio.system.model.dto.FunctionDto;
import com.galio.system.model.vo.FunctionVo;
import com.galio.system.model.Function;
import com.galio.system.mapper.FunctionMapper;
import com.galio.system.service.FunctionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 功能Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class FunctionServiceImpl implements FunctionService {

    private final FunctionMapper functionMapper;

    /**
     * 查询功能
     */
    @Override
    public FunctionVo queryById(Long functionId) {
        return functionMapper.selectVoById(functionId);
    }

        /**
         * 查询功能列表
         */
        @Override
        public PageVo<FunctionVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Function> lqw = Wrappers.lambdaQuery();
            IPage<FunctionVo> pageData = functionMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询功能列表
     */
    @Override
    public List<FunctionVo> queryList(FunctionDto dto) {
        LambdaQueryWrapper<Function> lqw = buildQueryWrapper(dto);
        return functionMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Function> buildQueryWrapper(FunctionDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Function> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getFunctionName()), Function::getFunctionName, dto.getFunctionName());
                    lqw.eq(dto.getParentId() != null, Function::getParentId, dto.getParentId());
                    lqw.eq(dto.getOrderNum() != null, Function::getOrderNum, dto.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(dto.getPath()), Function::getPath, dto.getPath());
                    lqw.eq(StringUtil.isNotBlank(dto.getComponent()), Function::getComponent, dto.getComponent());
                    lqw.eq(StringUtil.isNotBlank(dto.getQueryParam()), Function::getQueryParam, dto.getQueryParam());
                    lqw.eq(StringUtil.isNotBlank(dto.getIsFrame()), Function::getIsFrame, dto.getIsFrame());
                    lqw.eq(StringUtil.isNotBlank(dto.getIsCache()), Function::getIsCache, dto.getIsCache());
                    lqw.eq(StringUtil.isNotBlank(dto.getFunctionType()), Function::getFunctionType, dto.getFunctionType());
                    lqw.eq(StringUtil.isNotBlank(dto.getBelong()), Function::getBelong, dto.getBelong());
                    lqw.eq(StringUtil.isNotBlank(dto.getVisible()), Function::getVisible, dto.getVisible());
                    lqw.eq(StringUtil.isNotBlank(dto.getDeleteFlag()), Function::getDeleteFlag, dto.getDeleteFlag());
                    lqw.eq(StringUtil.isNotBlank(dto.getPerms()), Function::getPerms, dto.getPerms());
                    lqw.eq(StringUtil.isNotBlank(dto.getIcon()), Function::getIcon, dto.getIcon());
                    lqw.eq(dto.getAppId() != null, Function::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增功能
     */
    @Override
    public Boolean insertByDto(FunctionDto dto) {
        Function add = ObjectUtil.copyObject(dto, Function. class);
        validEntityBeforeSave(add);
        boolean flag = functionMapper.insert(add) > 0;
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
        Function update = ObjectUtil.copyObject(dto, Function. class);
        validEntityBeforeSave(update);
        return functionMapper.updateById(update) > 0;
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
        return functionMapper.deleteBatchIds(ids) > 0;
    }
}
