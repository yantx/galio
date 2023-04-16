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
import com.galio.system.model.dto.OperLogDto;
import com.galio.system.model.vo.OperLogVo;
import com.galio.system.model.OperLog;
import com.galio.system.mapper.OperLogMapper;
import com.galio.system.service.OperLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 操作日志记录Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class OperLogServiceImpl implements OperLogService {

    private final OperLogMapper operLogMapper;

    /**
     * 查询操作日志记录
     */
    @Override
    public OperLogVo queryById(Long operId) {
        return operLogMapper.selectVoById(operId);
    }

        /**
         * 查询操作日志记录列表
         */
        @Override
        public PageVo<OperLogVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<OperLog> lqw = Wrappers.lambdaQuery();
            IPage<OperLogVo> pageData = operLogMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询操作日志记录列表
     */
    @Override
    public List<OperLogVo> queryList(OperLogDto dto) {
        LambdaQueryWrapper<OperLog> lqw = buildQueryWrapper(dto);
        return operLogMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OperLog> buildQueryWrapper(OperLogDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<OperLog> lqw = Wrappers.lambdaQuery();
                    lqw.eq(StringUtil.isNotBlank(dto.getTitle()), OperLog::getTitle, dto.getTitle());
                    lqw.eq(StringUtil.isNotBlank(dto.getBusinessType()), OperLog::getBusinessType, dto.getBusinessType());
                    lqw.eq(StringUtil.isNotBlank(dto.getMethod()), OperLog::getMethod, dto.getMethod());
                    lqw.eq(StringUtil.isNotBlank(dto.getRequestMethod()), OperLog::getRequestMethod, dto.getRequestMethod());
                    lqw.eq(StringUtil.isNotBlank(dto.getOperatorType()), OperLog::getOperatorType, dto.getOperatorType());
                    lqw.eq(dto.getOperBy() != null, OperLog::getOperBy, dto.getOperBy());
                    lqw.like(StringUtil.isNotBlank(dto.getOrgName()), OperLog::getOrgName, dto.getOrgName());
                    lqw.eq(StringUtil.isNotBlank(dto.getOperUrl()), OperLog::getOperUrl, dto.getOperUrl());
                    lqw.eq(StringUtil.isNotBlank(dto.getOperIp()), OperLog::getOperIp, dto.getOperIp());
                    lqw.eq(StringUtil.isNotBlank(dto.getOperLocation()), OperLog::getOperLocation, dto.getOperLocation());
                    lqw.eq(StringUtil.isNotBlank(dto.getOperParam()), OperLog::getOperParam, dto.getOperParam());
                    lqw.eq(StringUtil.isNotBlank(dto.getJsonResult()), OperLog::getJsonResult, dto.getJsonResult());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), OperLog::getStatus, dto.getStatus());
                    lqw.eq(StringUtil.isNotBlank(dto.getErrorMsg()), OperLog::getErrorMsg, dto.getErrorMsg());
                    lqw.eq(dto.getOperTime() != null, OperLog::getOperTime, dto.getOperTime());
                    lqw.eq(dto.getAppId() != null, OperLog::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增操作日志记录
     */
    @Override
    public Boolean insertByDto(OperLogDto dto) {
        OperLog add = ObjectUtil.copyObject(dto, OperLog. class);
        validEntityBeforeSave(add);
        boolean flag = operLogMapper.insert(add) > 0;
        if (flag) {
            dto.setOperId(add.getOperId());
        }
        return flag;
    }

    /**
     * 修改操作日志记录
     */
    @Override
    public Boolean updateByDto(OperLogDto dto) {
        OperLog update = ObjectUtil.copyObject(dto, OperLog. class);
        validEntityBeforeSave(update);
        return operLogMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OperLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除操作日志记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return operLogMapper.deleteBatchIds(ids) > 0;
    }
}
