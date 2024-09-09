package com.galio.common.log.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志事件
 *
 * @author Lion Li
 */

@Data
public class OperationLogEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long operationId;

    /**
     * 操作模块
     */
    private String model;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer operationType;

    /**
     * 业务类型数组
     */
    private Integer[] operationTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作人员
     */
    private String operator;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 请求url
     */
    private String operationUrl;

    /**
     * 操作地址
     */
    private String operationIp;

    /**
     * 操作地点
     */
    private String operationLocation;

    /**
     * 请求参数
     */
    private String operationParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

}
