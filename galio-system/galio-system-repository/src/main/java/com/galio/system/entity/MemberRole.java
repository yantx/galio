package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和角色关联对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_member_role")
public class MemberRole{

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 成员id
     */
    private Long memberId;

}
