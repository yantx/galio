package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.GroupDto;
import com.galio.system.model.Group;
import com.galio.system.repository.GroupRepository;
import com.galio.system.service.GroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    /**
     * 查询群组信息
     */
    @Override
    public Group queryById(Long groupId) {
        return groupRepository.selectById(groupId);
    }

        /**
         * 查询群组信息列表
         */
        @Override
        public Page<Group> queryPageList(PageRequestDto pageRequestDto) {
            return groupRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDto));
        }

    /**
     * 查询群组信息列表
     */
    @Override
    public List<Group> queryList(GroupDto dto) {
        Group entity = ObjectUtil.copyObject(dto, Group.class);
        Map<String, Object> params = dto.getParams();
        return groupRepository.selectList(entity,params);
    }

    /**
     * 新增群组信息
     */
    @Override
    public Boolean insertByDto(GroupDto dto) {
        Group add = ObjectUtil.copyObject(dto, Group.class);
        validEntityBeforeSave(add);
        boolean flag = groupRepository.insert(add) > 0;
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
        Group update = ObjectUtil.copyObject(dto, Group.class);
        validEntityBeforeSave(update);
        return groupRepository.updateById(update) > 0;
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
        return groupRepository.deleteBatchIds(ids) > 0;
    }
}
