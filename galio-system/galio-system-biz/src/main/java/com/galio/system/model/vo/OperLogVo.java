package com.galio.system.model.vo;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 操作日志记录视图对象
 */
@Data
@Schema(description = "操作日志记录视图对象")
public class OperLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long operId;

    /**
     * 模块标题
     */
    @Schema(description = "模块标题")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private String operType;

    /**
     * 方法名称
     */
    @Schema(description = "方法名称")
    private String method;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台雇员 2手机端雇员）
     */
    @Schema(description = "操作类别（0其它 1后台雇员 2手机端雇员）")
    private String operSide;

    /**
     * 操作人员
     */
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
    @Schema(description = "请求url")
    private String operUrl;

    /**
     * 主机地址
     */
    @Schema(description = "主机地址")
    private String operIp;

    /**
     * 操作地点
     */
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
    @Schema(description = "操作时间")
    private LocalDateTime operTime;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;


}
