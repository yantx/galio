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
import com.galio.system.model.dto.OrgDto;
import com.galio.system.model.vo.OrgVo;
import com.galio.system.model.Org;
import com.galio.system.mapper.OrgMapper;
import com.galio.system.service.OrgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class OrgServiceImpl implements OrgService {

    private final OrgMapper orgMapper;

    /**
     * 查询机构
     */
    @Override
    public OrgVo queryById(Long orgId) {
        return orgMapper.selectVoById(orgId);
    }

        /**
         * 查询机构列表
         */
        @Override
        public PageVo<OrgVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Org> lqw = Wrappers.lambdaQuery();
            IPage<OrgVo> pageData = orgMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询机构列表
     */
    @Override
    public List<OrgVo> queryList(OrgDto dto) {
        LambdaQueryWrapper<Org> lqw = buildQueryWrapper(dto);
        return orgMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Org> buildQueryWrapper(OrgDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Org> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getParentId() != null, Org::getParentId, dto.getParentId());
                    lqw.eq(StringUtil.isNotBlank(dto.getAncestors()), Org::getAncestors, dto.getAncestors());
                    lqw.like(StringUtil.isNotBlank(dto.getOrgName()), Org::getOrgName, dto.getOrgName());
                    lqw.eq(dto.getOrderNum() != null, Org::getOrderNum, dto.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(dto.getLeader()), Org::getLeader, dto.getLeader());
                    lqw.eq(StringUtil.isNotBlank(dto.getPhone()), Org::getPhone, dto.getPhone());
                    lqw.eq(StringUtil.isNotBlank(dto.getEmail()), Org::getEmail, dto.getEmail());
                    lqw.eq(StringUtil.isNotBlank(dto.getCategory()), Org::getCategory, dto.getCategory());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Org::getStatus, dto.getStatus());
                    lqw.eq(StringUtil.isNotBlank(dto.getDeleteFlag()), Org::getDeleteFlag, dto.getDeleteFlag());
                    lqw.eq(dto.getAppId() != null, Org::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增机构
     */
    @Override
    public Boolean insertByDto(OrgDto dto) {
        Org add = ObjectUtil.copyObject(dto, Org. class);
        validEntityBeforeSave(add);
        boolean flag = orgMapper.insert(add) > 0;
        if (flag) {
            dto.setOrgId(add.getOrgId());
        }
        return flag;
    }

    /**
     * 修改机构
     */
    @Override
    public Boolean updateByDto(OrgDto dto) {
        Org update = ObjectUtil.copyObject(dto, Org. class);
        validEntityBeforeSave(update);
        return orgMapper.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return orgMapper.deleteBatchIds(ids) > 0;
    }
}
