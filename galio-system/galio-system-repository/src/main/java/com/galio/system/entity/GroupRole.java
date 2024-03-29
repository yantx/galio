package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 分组和角色关联对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_group_role")
public class GroupRole {

    private static final long serialVersionUID=1L;

    /**
     * 组id
     */
    private Long groupId;
    /**
     * 角色id
     */
    private Long roleId;

}
