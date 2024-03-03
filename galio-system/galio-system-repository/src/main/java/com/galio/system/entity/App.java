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
 * @Description: 应用信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_app")
public class App extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 应用id
     */
    @TableId(value = "app_id", type = IdType.ASSIGN_ID)
    private Long appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用代码
     */
    private String appCode;
    /**
     * 应用图标
     */
    private String appIcon;
    /**
     * 是否固定（1是，0否）
     */
    private String isFixed;
    /**
     * 应用状态（1正常，0异常）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
