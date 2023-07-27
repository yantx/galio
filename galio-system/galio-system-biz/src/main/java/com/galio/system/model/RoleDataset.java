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
 * @Description: 角色和数据集关联对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_dataset")
public class RoleDataset{

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 数据集id
     */
    private Long datasetId;

}
