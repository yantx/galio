package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 通知公告视图对象
 */
@Data
@Schema(description = "通知公告视图对象")
public class NoticeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    private String content;

    /**
     * 公告状态（1正常 0关闭）
     */
    @Schema(description = "公告状态（1正常 0关闭）")
    private String status;

    /**
     * $column.columnComment
     */
    @Schema(description = "$column.columnComment")
    private Long appId;

    /**
     * $column.columnComment
     */
    @Schema(description = "$column.columnComment")
    private String remark;


}
