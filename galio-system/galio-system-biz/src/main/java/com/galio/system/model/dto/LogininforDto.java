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
 * @Description: 系统访问记录业务对象
 */

@Data
public class LogininforDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long infoId;

    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String username;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long memberId;

    /**
     * 登录ip地址
     */
    @NotBlank(message = "登录ip地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String ipaddr;

    /**
     * 登录状态（0成功 1失败）
     */
    @NotBlank(message = "登录状态（0成功 1失败）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * 提示信息
     */
    @NotBlank(message = "提示信息不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String msg;

    /**
     * 访问时间
     */
    @NotNull(message = "访问时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime accessTime;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;


}
