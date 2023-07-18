package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.system.dto.AppPageReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.AppDto;
import com.galio.system.model.App;
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
    public App queryById(Long appId) {
        return appRepository.selectById(appId);
    }

        /**
         * 查询应用信息列表
         */
        @Override
        public Page<App> queryPageList(AppPageReqDto pageRequestDto) {
            return appRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<App> queryList(AppDto dto) {
        App entity = ObjectUtil.copyObject(dto, App.class);
        Map<String, Object> params = dto.getParams();
        return appRepository.selectList(entity,params);
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByDto(AppDto dto) {
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
    public Boolean updateByDto(AppDto dto) {
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return appRepository.deleteBatchIds(ids) > 0;
    }
}
