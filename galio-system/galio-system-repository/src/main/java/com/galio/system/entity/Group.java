package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-05-30
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
    @TableId(value = "group_id",type = IdType.ASSIGN_ID)
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

    /**
     * 关联关联角色ID集合
     */
    @TableField(exist = false)
    private List<Long> roleIds;

}
