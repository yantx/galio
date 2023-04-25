package com.galio.system.model.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录视图对象
 */
@Data
public class LogininforVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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
