package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.system.dto.AppPageReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.AppDTO;
import com.galio.system.entity.App;
import com.galio.system.repository.AppRepository;
import com.galio.system.service.AppService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final AppRepository appRepository;

    /**
     * 查询应用信息
     */
    @Override
    public App getById(Long appId) {
        return appRepository.selectById(appId);
    }

        /**
         * 查询应用信息列表
         */
        @Override
        public Page<App> listPage(AppPageReqDTO pageRequestDTO) {
            return appRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<App> list(AppDTO dto) {
        App entity = ObjectUtil.copyObject(dto, App.class);
        
        return appRepository.selectList(entity);
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean save(AppDTO dto) {
        App add = ObjectUtil.copyObject(dto, App.class);
        validEntityBeforeSave(add);
        boolean flag = appRepository.insert(add) > 0;
        if (flag) {
            dto.setAppId(add.getAppId());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean update(AppDTO dto) {
        App update = ObjectUtil.copyObject(dto, App.class);
        validEntityBeforeSave(update);
        return appRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(App entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return appRepository.deleteBatchIds(ids) > 0;
    }
}
