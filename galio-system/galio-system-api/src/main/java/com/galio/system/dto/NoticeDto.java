package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 通知公告业务对象
 */

@Data
@Schema(description = "通知公告业务对象")
public class NoticeDto extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long noticeId;

    /**
     * 公告标题
     */
    @NotBlank(message = "公告标题不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "公告标题")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @NotBlank(message = "公告类型（1通知 2公告）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "公告内容")
    private String content;

    /**
     * 公告状态（1正常 0关闭）
     */
    @NotBlank(message = "公告状态（1正常 0关闭）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "公告状态（1正常 0关闭）")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注信息")
    private String remark;


}
