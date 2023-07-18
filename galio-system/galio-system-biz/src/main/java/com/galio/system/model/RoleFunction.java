package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色和功能关联对象
 */
@Data
@TableName("sys_role_function")
public class RoleFunction {

    /**
     * 角色id
     */
    @TableId(value = "role_id")
    private Long roleId;
    /**
     * 功能id
     */
    @TableId(value = "function_id")
    private Long functionId;

}
