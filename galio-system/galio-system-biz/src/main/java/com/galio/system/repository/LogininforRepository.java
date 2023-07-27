package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Logininfor;
import com.galio.system.mapper.LogininforMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class LogininforRepository{

    private final LogininforMapper logininforMapper;

    /**
     * 查询系统访问记录
     */
    public Logininfor selectById(Long infoId) {
        return logininforMapper.selectById(infoId);
    }

        /**
         * 查询系统访问记录列表
         */
        public Page<Logininfor> selectPage(Page page) {
            LambdaQueryWrapper<Logininfor> lqw = Wrappers.lambdaQuery();
            return logininforMapper.selectPage(page, lqw);
        }

    /**
     * 查询系统访问记录列表
     */
    public List<Logininfor> selectList(Logininfor logininfor) {
        LambdaQueryWrapper<Logininfor> lqw = buildQueryWrapper(logininfor);
        return logininforMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Logininfor> buildQueryWrapper(Logininfor entity) {
        LambdaQueryWrapper<Logininfor> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getUsername()), Logininfor::getUsername, entity.getUsername());
                    lqw.eq(entity.getMemberId() != null, Logininfor::getMemberId, entity.getMemberId());
                    lqw.eq(StringUtil.isNotBlank(entity.getIpaddr()), Logininfor::getIpaddr, entity.getIpaddr());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Logininfor::getStatus, entity.getStatus());
                    lqw.eq(StringUtil.isNotBlank(entity.getMsg()), Logininfor::getMsg, entity.getMsg());
                    lqw.eq(entity.getAccessTime() != null, Logininfor::getAccessTime, entity.getAccessTime());
                    lqw.eq(entity.getAppId() != null, Logininfor::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增系统访问记录
     */
    public int insert(Logininfor entity) {
        validEntityBeforeSave(entity);
        int flag = logininforMapper.insert(entity);
        return flag;
    }

    /**
     * 修改系统访问记录
     */
    public int updateById(Logininfor entity) {
        validEntityBeforeSave(entity);
        return logininforMapper.updateById(entity);
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
    public int deleteBatchIds(Collection<Long> ids) {
        return logininforMapper.deleteBatchIds(ids);
    }
}
