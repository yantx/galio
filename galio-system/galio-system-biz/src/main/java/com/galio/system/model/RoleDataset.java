package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色和数据集关联对象
 */
@Data
@TableName("sys_role_dataset")
public class RoleDataset{

    /**
     * 角色id
     */
    @TableId(value = "role_id")
    private Long roleId;
    /**
     * 数据集id
     */
    @TableId(value = "dataset_id")
    private Long datasetId;

}
