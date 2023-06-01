package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 群组信息视图对象
 */
@Data
@Schema(description = "群组信息视图对象")
public class GroupVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long groupId;

    /**
     * 组代码
     */
    @Schema(description = "组代码")
    private Long groupCode;

    /**
     * 组名称
     */
    @Schema(description = "组名称")
    private String groupName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 状态1正常 0异常
     */
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
