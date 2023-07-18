package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和群组关联对象
 */
@Data
@TableName("sys_member_group")
public class MemberGroup {

    /**
     * 成员id
     */
    @TableId(value = "member_id")
    private Long memberId;
    /**
     * 组id
     */
    @TableId(value = "group_id")
    private Long groupId;

}
