package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 应用信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
public class Config extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 参数表主键
     */
    @TableId(value = "config_id")
    private Long configId;
    /**
     * 参数名称
     */
    private String configName;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String configValue;
    /**
     * 系统内置（1是 0否）
     */
    private String configType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 应用id
     */
    private Long appId;

}
