package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_group")
public class Group extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "group_id")
    private Long groupId;
    /**
     * 组代码
     */
    private Long groupCode;
    /**
     * 组名称
     */
    private String groupName;
    /**
     * 显示顺序
     */
    private Long orderNum;
    /**
     * 状态1正常 0异常
     */
    private String status;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 备注
     */
    private String remark;

}
