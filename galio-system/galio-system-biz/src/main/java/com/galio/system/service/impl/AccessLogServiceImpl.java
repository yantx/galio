package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.AccessLogDto;
import com.galio.system.model.AccessLog;
import com.galio.system.repository.AccessLogRepository;
import com.galio.system.service.AccessLogService;

import java.util.List;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;

    /**
     * 查询系统访问记录
     */
    @Override
    public AccessLog queryById(Long accessId) {
        return accessLogRepository.selectById(accessId);
    }

        /**
         * 查询系统访问记录列表
         */
        @Override
        public Page<AccessLog> queryPageList(PageRequestDto pageRequestDto) {
            return accessLogRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询系统访问记录列表
     */
    @Override
    public List<AccessLog> queryList(AccessLogDto dto) {
        AccessLog entity = ObjectUtil.copyObject(dto, AccessLog.class);
        
        return accessLogRepository.selectList(entity);
    }

    /**
     * 新增系统访问记录
     */
    @Override
    public Boolean insertByDto(AccessLogDto dto) {
        AccessLog add = ObjectUtil.copyObject(dto, AccessLog.class);
        validEntityBeforeSave(add);
        boolean flag = accessLogRepository.insert(add) > 0;
        if (flag) {
            dto.setAccessId(add.getAccessId());
        }
        return flag;
    }

    /**
     * 修改系统访问记录
     */
    @Override
    public Boolean updateByDto(AccessLogDto dto) {
        AccessLog update = ObjectUtil.copyObject(dto, AccessLog.class);
        validEntityBeforeSave(update);
        return accessLogRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AccessLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统访问记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return accessLogRepository.deleteBatchIds(ids) > 0;
    }
}
