package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和群组关联对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_member_group")
public class MemberGroup {

    /**
     * 成员id
     */
    private Long memberId;
    /**
     * 组id
     */
    private Long groupId;

}
