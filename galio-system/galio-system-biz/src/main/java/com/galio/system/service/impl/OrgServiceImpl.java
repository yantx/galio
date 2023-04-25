package com.galio.system.service.impl;

import com.galio.core.utils.StringUtil;
import com.galio.core.utils.ObjectUtil;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.OrgDto;
import com.galio.system.model.Org;
import com.galio.system.repository.OrgRepository;
import com.galio.system.service.OrgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class OrgServiceImpl implements OrgService {

    private final OrgRepository orgRepository;

    /**
     * 查询机构
     */
    @Override
    public Org queryById(Long orgId) {
        return orgRepository.selectById(orgId);
    }

        /**
         * 查询机构列表
         */
        @Override
        public Page<Org> queryPageList(PageDto pageDto) {
            return orgRepository.selectPage(pageDto.build());
        }

    /**
     * 查询机构列表
     */
    @Override
    public List<Org> queryList(OrgDto dto) {
        Org entity = ObjectUtil.copyObject(dto, Org.class);
        Map<String, Object> params = dto.getParams();
        return orgRepository.selectList(entity,params);
    }

    /**
     * 新增机构
     */
    @Override
    public Boolean insertByDto(OrgDto dto) {
        Org add = ObjectUtil.copyObject(dto, Org.class);
        validEntityBeforeSave(add);
        boolean flag = orgRepository.insert(add) > 0;
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
        Org update = ObjectUtil.copyObject(dto, Org.class);
        validEntityBeforeSave(update);
        return orgRepository.updateById(update) > 0;
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
        return orgRepository.deleteBatchIds(ids) > 0;
    }
}
