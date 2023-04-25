package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.TreeEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-12
 * @Description: 机构实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("sys_org")
public class SysOrg extends TreeEntity<SysOrg> {
    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableId(value = "org_id")
    private Long orgId;

    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @Size(min = 0, max = 30, message = "机构名称长度不能超过30个字符")
    private String orgName;

    /**
     * 父级机构ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;

    /**
     * 邮箱
     */
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /**
     * 机构状态:1正常,0停用
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String deleteFlag;

    /**
     * 祖级列表
     */
    private String ancestors;

}
