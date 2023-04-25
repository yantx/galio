package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告视图对象
 */
@Data
public class NoticeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告状态（1正常 0关闭）
     */
    private String status;

    /**
     * $column.columnComment
     */
    private Long appId;

    /**
     * $column.columnComment
     */
    private String remark;


}
