package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 通知公告分页查询对象
 */

@Data
@Schema(description = "通知公告分页查询对象")
public class NoticePageReqDTO extends PageRequestDTO {

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

}
