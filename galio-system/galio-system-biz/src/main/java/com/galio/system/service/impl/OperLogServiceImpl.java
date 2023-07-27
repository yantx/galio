package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.OperLogDto;
import com.galio.system.model.OperLog;
import com.galio.system.repository.OperLogRepository;
import com.galio.system.service.OperLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 操作日志记录Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class OperLogServiceImpl implements OperLogService {

    private final OperLogRepository operLogRepository;

    /**
     * 查询操作日志记录
     */
    @Override
    public OperLog queryById(Long operId) {
        return operLogRepository.selectById(operId);
    }

        /**
         * 查询操作日志记录列表
         */
        @Override
        public Page<OperLog> queryPageList(PageRequestDto pageRequestDto) {
            return operLogRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询操作日志记录列表
     */
    @Override
    public List<OperLog> queryList(OperLogDto dto) {
        OperLog entity = ObjectUtil.copyObject(dto, OperLog.class);
        
        return operLogRepository.selectList(entity);
    }

    /**
     * 新增操作日志记录
     */
    @Override
    public Boolean insertByDto(OperLogDto dto) {
        OperLog add = ObjectUtil.copyObject(dto, OperLog.class);
        validEntityBeforeSave(add);
        boolean flag = operLogRepository.insert(add) > 0;
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
        OperLog update = ObjectUtil.copyObject(dto, OperLog.class);
        validEntityBeforeSave(update);
        return operLogRepository.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return operLogRepository.deleteBatchIds(ids) > 0;
    }
}
