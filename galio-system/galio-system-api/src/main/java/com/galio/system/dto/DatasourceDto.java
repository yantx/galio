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
 * @Description: 数据源信息业务对象
 */

@Data
@Schema(description = "数据源信息业务对象")
public class DatasourceDto extends BaseEntity {

    /**
     * 数据源id
     */
    @NotNull(message = "数据源id不能为空", groups = { UpdateGroup.class })
    @Schema(description = "数据源id")
    private Long datasourceId;

    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据源名称")
    private String datasourceName;

    /**
     * 数据源类型
     */
    @NotBlank(message = "数据源类型不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据源类型")
    private String datasourceType;

    /**
     * 链接驱动
     */
    @NotBlank(message = "链接驱动不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "链接驱动")
    private String driverClass;

    /**
     * 数据库链接
     */
    @NotBlank(message = "数据库链接不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据库链接")
    private String url;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "密码")
    private String password;

    /**
     * 测试查询语句
     */
    @NotBlank(message = "测试查询语句不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "测试查询语句")
    private String testQuery;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
