package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 成员与角色关联实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_member_role")
public class SysMemberRole {
    private long roleId;
    private long memberId;
}
