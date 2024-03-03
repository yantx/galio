package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统访问记录对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_access_log")
public class AccessLog{

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "access_id",type = IdType.ASSIGN_ID)
    private Long accessId;
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
