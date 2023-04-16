package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 操作日志记录业务对象
 */

@Data
public class OperLogDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long operId;

    /**
     * 模块标题
     */
    @NotBlank(message = "模块标题不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @NotBlank(message = "业务类型（0其它 1新增 2修改 3删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String businessType;

    /**
     * 方法名称
     */
    @NotBlank(message = "方法名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String method;

    /**
     * 请求方式
     */
    @NotBlank(message = "请求方式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台雇员 2手机端雇员）
     */
    @NotBlank(message = "操作类别（0其它 1后台雇员 2手机端雇员）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String operatorType;

    /**
     * 操作人员
     */
    @NotNull(message = "操作人员不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long operBy;

    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String orgName;

    /**
     * 请求url
     */
    @NotBlank(message = "请求url不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String operUrl;

    /**
     * 主机地址
     */
    @NotBlank(message = "主机地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String operIp;

    /**
     * 操作地点
     */
    @NotBlank(message = "操作地点不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String operLocation;

    /**
     * 请求参数
     */
    @NotBlank(message = "请求参数不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String operParam;

    /**
     * 返回参数
     */
    @NotBlank(message = "返回参数不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String jsonResult;

    /**
     * 操作状态（1正常 0异常）
     */
    @NotBlank(message = "操作状态（1正常 0异常）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * 错误消息
     */
    @NotBlank(message = "错误消息不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String errorMsg;

    /**
     * 操作时间
     */
    @NotNull(message = "操作时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime operTime;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;


}
