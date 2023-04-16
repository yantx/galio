package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据源信息业务对象
 */

@Data
public class DatasourceDto extends BaseEntity {

    /**
     * 数据源id
     */
    @NotNull(message = "数据源id不能为空", groups = { UpdateGroup.class })
    private Long datasourceId;

    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String datasourceName;

    /**
     * 数据源类型
     */
    @NotBlank(message = "数据源类型不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String datasourceType;

    /**
     * 链接驱动
     */
    @NotBlank(message = "链接驱动不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String driverClass;

    /**
     * 数据库链接
     */
    @NotBlank(message = "数据库链接不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String url;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String password;

    /**
     * 测试查询语句
     */
    @NotBlank(message = "测试查询语句不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String testQuery;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
