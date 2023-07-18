package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 分组和角色关联对象
 */
@Data
@TableName("sys_group_role")
public class GroupRole {

    private static final long serialVersionUID=1L;

    /**
     * 组id
     */
    @TableId(value = "group_id")
    private Long groupId;
    /**
     * 角色id
     */
    @TableId(value = "role_id")
    private Long roleId;

}
