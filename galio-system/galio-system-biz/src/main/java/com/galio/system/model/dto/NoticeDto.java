package com.galio.system.model.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告业务对象
 */

@Data
public class NoticeDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long noticeId;

    /**
     * 公告标题
     */
    @NotBlank(message = "公告标题不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @NotBlank(message = "公告类型（1通知 2公告）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String noticeType;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String content;

    /**
     * 公告状态（1正常 0关闭）
     */
    @NotBlank(message = "公告状态（1正常 0关闭）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * $column.columnComment
     */
    @NotNull(message = "$column.columnComment不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * $column.columnComment
     */
    @NotBlank(message = "$column.columnComment不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
