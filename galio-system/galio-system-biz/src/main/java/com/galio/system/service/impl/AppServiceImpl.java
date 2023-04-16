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
import com.galio.system.model.dto.AppDto;
import com.galio.system.model.vo.AppVo;
import com.galio.system.model.App;
import com.galio.system.mapper.AppMapper;
import com.galio.system.service.AppService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息Service业务层处理
 */
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AppMapper appMapper;

    /**
     * 查询应用信息
     */
    @Override
    public AppVo queryById(Long appId) {
        return appMapper.selectVoById(appId);
    }

        /**
         * 查询应用信息列表
         */
        @Override
        public PageVo<AppVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<App> lqw = Wrappers.lambdaQuery();
            IPage<AppVo> pageData = appMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<AppVo> queryList(AppDto dto) {
        LambdaQueryWrapper<App> lqw = buildQueryWrapper(dto);
        return appMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<App> buildQueryWrapper(AppDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<App> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getAppName()), App::getAppName, dto.getAppName());
                    lqw.eq(StringUtil.isNotBlank(dto.getAppCode()), App::getAppCode, dto.getAppCode());
                    lqw.eq(StringUtil.isNotBlank(dto.getAppIcon()), App::getAppIcon, dto.getAppIcon());
                    lqw.eq(StringUtil.isNotBlank(dto.getIsFixed()), App::getIsFixed, dto.getIsFixed());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), App::getStatus, dto.getStatus());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByDto(AppDto dto) {
        App add = ObjectUtil.copyObject(dto, App. class);
        validEntityBeforeSave(add);
        boolean flag = appMapper.insert(add) > 0;
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
        App update = ObjectUtil.copyObject(dto, App. class);
        validEntityBeforeSave(update);
        return appMapper.updateById(update) > 0;
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
        return appMapper.deleteBatchIds(ids) > 0;
    }
}
