package com.galio.system.model.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 操作日志记录视图对象
 */
@Data
public class OperLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long operId;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private String businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台雇员 2手机端雇员）
     */
    private String operatorType;

    /**
     * 操作人员
     */
    private Long operBy;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（1正常 0异常）
     */
    private String status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private LocalDateTime operTime;

    /**
     * 应用id
     */
    private Long appId;


}
