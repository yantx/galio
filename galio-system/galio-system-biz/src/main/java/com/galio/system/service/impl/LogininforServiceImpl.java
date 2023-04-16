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
import com.galio.system.model.dto.LogininforDto;
import com.galio.system.model.vo.LogininforVo;
import com.galio.system.model.Logininfor;
import com.galio.system.mapper.LogininforMapper;
import com.galio.system.service.LogininforService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 系统访问记录Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class LogininforServiceImpl implements LogininforService {

    private final LogininforMapper logininforMapper;

    /**
     * 查询系统访问记录
     */
    @Override
    public LogininforVo queryById(Long infoId) {
        return logininforMapper.selectVoById(infoId);
    }

        /**
         * 查询系统访问记录列表
         */
        @Override
        public PageVo<LogininforVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Logininfor> lqw = Wrappers.lambdaQuery();
            IPage<LogininforVo> pageData = logininforMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询系统访问记录列表
     */
    @Override
    public List<LogininforVo> queryList(LogininforDto dto) {
        LambdaQueryWrapper<Logininfor> lqw = buildQueryWrapper(dto);
        return logininforMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Logininfor> buildQueryWrapper(LogininforDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Logininfor> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getUsername()), Logininfor::getUsername, dto.getUsername());
                    lqw.eq(dto.getMemberId() != null, Logininfor::getMemberId, dto.getMemberId());
                    lqw.eq(StringUtil.isNotBlank(dto.getIpaddr()), Logininfor::getIpaddr, dto.getIpaddr());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Logininfor::getStatus, dto.getStatus());
                    lqw.eq(StringUtil.isNotBlank(dto.getMsg()), Logininfor::getMsg, dto.getMsg());
                    lqw.eq(dto.getAccessTime() != null, Logininfor::getAccessTime, dto.getAccessTime());
                    lqw.eq(dto.getAppId() != null, Logininfor::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增系统访问记录
     */
    @Override
    public Boolean insertByDto(LogininforDto dto) {
        Logininfor add = ObjectUtil.copyObject(dto, Logininfor. class);
        validEntityBeforeSave(add);
        boolean flag = logininforMapper.insert(add) > 0;
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
        Logininfor update = ObjectUtil.copyObject(dto, Logininfor. class);
        validEntityBeforeSave(update);
        return logininforMapper.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return logininforMapper.deleteBatchIds(ids) > 0;
    }
}
