package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.LogininforDto;
import com.galio.system.model.Logininfor;
import com.galio.system.repository.LogininforRepository;
import com.galio.system.service.LogininforService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class LogininforServiceImpl implements LogininforService {

    private final LogininforRepository logininforRepository;

    /**
     * 查询系统访问记录
     */
    @Override
    public Logininfor queryById(Long infoId) {
        return logininforRepository.selectById(infoId);
    }

        /**
         * 查询系统访问记录列表
         */
        @Override
        public Page<Logininfor> queryPageList(PageRequestDto pageRequestDto) {
            return logininforRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询系统访问记录列表
     */
    @Override
    public List<Logininfor> queryList(LogininforDto dto) {
        Logininfor entity = ObjectUtil.copyObject(dto, Logininfor.class);
        
        return logininforRepository.selectList(entity);
    }

    /**
     * 新增系统访问记录
     */
    @Override
    public Boolean insertByDto(LogininforDto dto) {
        Logininfor add = ObjectUtil.copyObject(dto, Logininfor.class);
        validEntityBeforeSave(add);
        boolean flag = logininforRepository.insert(add) > 0;
        if (flag) {
            dto.setInfoId(add.getInfoId());
        }
        return flag;
    }

    /**
     * 修改系统访问记录
     */
    @Override
    public Boolean updateByDto(LogininforDto dto) {
        Logininfor update = ObjectUtil.copyObject(dto, Logininfor.class);
        validEntityBeforeSave(update);
        return logininforRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Logininfor entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统访问记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return logininforRepository.deleteBatchIds(ids) > 0;
    }
}
