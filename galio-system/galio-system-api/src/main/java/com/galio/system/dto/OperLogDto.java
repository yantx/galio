package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 操作日志记录业务对象
 */

@Data
@Schema(description = "操作日志记录业务对象")
public class OperLogDTO extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long operId;

    /**
     * 模块标题
     */
    @NotBlank(message = "模块标题不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "模块标题")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @NotBlank(message = "业务类型（0其它 1新增 2修改 3删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private String operType;

    /**
     * 方法名称
     */
    @NotBlank(message = "方法名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "方法名称")
    private String method;

    /**
     * 请求方式
     */
    @NotBlank(message = "请求方式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台雇员 2手机端雇员）
     */
    @NotBlank(message = "操作类别（0其它 1后台雇员 2手机端雇员）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "操作类别（0其它 1后台雇员 2手机端雇员）")
    private String operSide;

    /**
     * 操作人员
     */
    @NotNull(message = "操作人员不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "操作人员")
    private Long operBy;

    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String orgName;

    /**
     * 请求url
     */
    @NotBlank(message = "请求url不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "请求url")
    private String operUrl;

    /**
     * 主机地址
     */
    @NotBlank(message = "主机地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "主机地址")
    private String operIp;

    /**
     * 操作地点
     */
    @NotBlank(message = "操作地点不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String operParam;

    /**
     * 返回参数
     */
    @NotBlank(message = "返回参数不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（1正常 0异常）
     */
    @Schema(description = "操作状态（1正常 0异常）")
    private String status;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @NotNull(message = "操作时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "操作时间")
    private LocalDateTime operTime;


}
