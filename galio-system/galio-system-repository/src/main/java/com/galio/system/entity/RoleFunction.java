package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色和功能关联对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_function")
public class RoleFunction {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 功能id
     */
    private Long functionId;

}
