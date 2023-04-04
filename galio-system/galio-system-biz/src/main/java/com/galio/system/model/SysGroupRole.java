package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 账号组与角色关联实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_group_role")
public class SysGroupRole {

    private long groupId;
    private long roleId;
}
