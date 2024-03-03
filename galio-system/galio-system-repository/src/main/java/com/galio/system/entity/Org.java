package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 机构对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_org")
public class Org extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "org_id",type = IdType.ASSIGN_ID)
    private Long orgId;
    /**
     * 父部门id
     */
    private Long parentId;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 机构名
     */
    private String orgName;
    /**
     * 显示顺序
     */
    private Long orderNum;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 机构类别（1部门 2公司）
     */
    private String category;
    /**
     * 状态1正常 0异常
     */
    private String status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 备注
     */
    private String remark;

}
