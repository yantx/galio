package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-12
 * @Description: 字典实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("sys_dict")
public class SysDict extends BaseEntity {

    /**
     * 字典主键
     */
    @TableId(value = "dict_id")
    private Long dictId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    private String dictName;

    /**
     * 字典编号
     */
    @NotBlank(message = "字典编号")
    private String dictCode;

    /**
     * 状态（1正常 0停用）
     */
    private String status;
    private Integer orderNum;

    /**
     * 备注
     */
    private String remark;

}
