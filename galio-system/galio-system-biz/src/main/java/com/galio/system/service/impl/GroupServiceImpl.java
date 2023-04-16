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
import com.galio.system.model.dto.GroupDto;
import com.galio.system.model.vo.GroupVo;
import com.galio.system.model.Group;
import com.galio.system.mapper.GroupMapper;
import com.galio.system.service.GroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 群组信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;

    /**
     * 查询群组信息
     */
    @Override
    public GroupVo queryById(Long groupId) {
        return groupMapper.selectVoById(groupId);
    }

        /**
         * 查询群组信息列表
         */
        @Override
        public PageVo<GroupVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Group> lqw = Wrappers.lambdaQuery();
            IPage<GroupVo> pageData = groupMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询群组信息列表
     */
    @Override
    public List<GroupVo> queryList(GroupDto dto) {
        LambdaQueryWrapper<Group> lqw = buildQueryWrapper(dto);
        return groupMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Group> buildQueryWrapper(GroupDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Group> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getGroupCode() != null, Group::getGroupCode, dto.getGroupCode());
                    lqw.like(StringUtil.isNotBlank(dto.getGroupName()), Group::getGroupName, dto.getGroupName());
                    lqw.eq(dto.getOrderNum() != null, Group::getOrderNum, dto.getOrderNum());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Group::getStatus, dto.getStatus());
                    lqw.eq(dto.getAppId() != null, Group::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增群组信息
     */
    @Override
    public Boolean insertByDto(GroupDto dto) {
        Group add = ObjectUtil.copyObject(dto, Group. class);
        validEntityBeforeSave(add);
        boolean flag = groupMapper.insert(add) > 0;
        if (flag) {
            dto.setGroupId(add.getGroupId());
        }
        return flag;
    }

    /**
     * 修改群组信息
     */
    @Override
    public Boolean updateByDto(GroupDto dto) {
        Group update = ObjectUtil.copyObject(dto, Group. class);
        validEntityBeforeSave(update);
        return groupMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Group entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除群组信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return groupMapper.deleteBatchIds(ids) > 0;
    }
}