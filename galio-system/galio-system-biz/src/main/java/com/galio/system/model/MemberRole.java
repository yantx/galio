package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和角色关联对象
 */
@Data
@TableName("sys_member_role")
public class MemberRole{

    /**
     * 角色id
     */
    @TableId(value = "role_id")
    private Long roleId;
    /**
     * 成员id
     */
    @TableId(value = "member_id")
    private Long memberId;

}
