package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Org;
import com.galio.system.mapper.OrgMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class OrgRepository{

    private final OrgMapper orgMapper;

    /**
     * 查询机构
     */
    public Org selectById(Long orgId) {
        return orgMapper.selectById(orgId);
    }

        /**
         * 查询机构列表
         */
        public Page<Org> selectPage(Page page) {
            LambdaQueryWrapper<Org> lqw = Wrappers.lambdaQuery();
            return orgMapper.selectPage(page, lqw);
        }

    /**
     * 查询机构列表
     */
    public List<Org> selectList(Org org,Map<String, Object> params) {
        LambdaQueryWrapper<Org> lqw = buildQueryWrapper(org, params);
        return orgMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Org> buildQueryWrapper(Org entity,Map<String, Object> params) {
        LambdaQueryWrapper<Org> lqw = Wrappers.lambdaQuery();
                    lqw.eq(entity.getParentId() != null, Org::getParentId, entity.getParentId());
                    lqw.eq(StringUtil.isNotBlank(entity.getAncestors()), Org::getAncestors, entity.getAncestors());
                    lqw.like(StringUtil.isNotBlank(entity.getOrgName()), Org::getOrgName, entity.getOrgName());
                    lqw.eq(entity.getOrderNum() != null, Org::getOrderNum, entity.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(entity.getLeader()), Org::getLeader, entity.getLeader());
                    lqw.eq(StringUtil.isNotBlank(entity.getPhone()), Org::getPhone, entity.getPhone());
                    lqw.eq(StringUtil.isNotBlank(entity.getEmail()), Org::getEmail, entity.getEmail());
                    lqw.eq(StringUtil.isNotBlank(entity.getCategory()), Org::getCategory, entity.getCategory());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Org::getStatus, entity.getStatus());
                    lqw.eq(StringUtil.isNotBlank(entity.getDeleteFlag()), Org::getDeleteFlag, entity.getDeleteFlag());
                    lqw.eq(entity.getAppId() != null, Org::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增机构
     */
    public int insert(Org entity) {
        validEntityBeforeSave(entity);
        int flag = orgMapper.insert(entity);
        return flag;
    }

    /**
     * 修改机构
     */
    public int updateById(Org entity) {
        validEntityBeforeSave(entity);
        return orgMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Org entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除机构
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return orgMapper.deleteBatchIds(ids);
    }
}
