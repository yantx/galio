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
 * @Description: 字典对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 字典主键
     */
    @TableId(value = "dict_id",type = IdType.ASSIGN_ID)
    private Long dictId;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典编号
     */
    private String dictCode;
    /**
     * 状态（1正常 0停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 应用id
     */
    private Long appId;

}
