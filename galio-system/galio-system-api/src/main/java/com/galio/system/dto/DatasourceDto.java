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
    @Schema(description = "数据源id")
    private Long datasourceId;

    /**
     * 数据源名称
     */
    @Schema(description = "数据源名称")
    private String datasourceName;

    /**
     * 数据源类型
     */
    @Schema(description = "数据源类型")
    private String datasourceType;

    /**
     * 链接驱动
     */
    @Schema(description = "链接驱动")
    private String driverClass;

    /**
     * 数据库链接
     */
    @Schema(description = "数据库链接")
    private String url;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 测试查询语句
     */
    @Schema(description = "测试查询语句")
    private String testQuery;

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
