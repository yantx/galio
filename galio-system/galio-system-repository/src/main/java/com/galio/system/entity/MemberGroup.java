package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
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
