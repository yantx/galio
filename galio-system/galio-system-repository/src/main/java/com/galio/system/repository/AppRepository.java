package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.utils.StringUtil;
import com.galio.system.mapper.AppMapper;
import com.galio.system.entity.App;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class AppRepository{

    private final AppMapper appMapper;

    /**
     * 查询应用信息
     */
    public App selectById(Long appId) {
        return appMapper.selectById(appId);
    }

        /**
         * 查询应用信息列表
         */
        public Page<App> selectPage(Page page) {
            LambdaQueryWrapper<App> lqw = Wrappers.lambdaQuery();
            return appMapper.selectPage(page, lqw);
        }

    /**
     * 查询应用信息列表
     */
    public List<App> selectList(App app) {
        LambdaQueryWrapper<App> lqw = buildQueryWrapper(app);
        return appMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<App> buildQueryWrapper(App entity) {
        LambdaQueryWrapper<App> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getAppName()), App::getAppName, entity.getAppName());
                    lqw.eq(StringUtil.isNotBlank(entity.getAppCode()), App::getAppCode, entity.getAppCode());
                    lqw.eq(StringUtil.isNotBlank(entity.getIsFixed()), App::getIsFixed, entity.getIsFixed());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), App::getStatus, entity.getStatus());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    public int insert(App entity) {
        validEntityBeforeSave(entity);
        int flag = appMapper.insert(entity);
        return flag;
    }

    /**
     * 修改应用信息
     */
    public int updateById(App entity) {
        validEntityBeforeSave(entity);
        return appMapper.updateById(entity);
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
    public int deleteBatchIds(Collection<Long> ids) {
        return appMapper.deleteBatchIds(ids);
    }
}
