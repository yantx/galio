package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 系统访问记录对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_logininfor")
public class Logininfor extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "info_id")
    private Long infoId;
    /**
     * 登录名
     */
    private String username;
    /**
     * 成员ID
     */
    private Long memberId;
    /**
     * 登录ip地址
     */
    private String ipaddr;
    /**
     * 登录状态（0成功 1失败）
     */
    private String status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 访问时间
     */
    private LocalDateTime accessTime;
    /**
     * 应用id
     */
    private Long appId;

}
