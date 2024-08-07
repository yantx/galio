package com.galio.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2023-01-19
 * @Description: 分页查询参数
 */
@Data
@Schema(description = "分页查询参数" )
public class PageRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    @Schema(description = "分页大小")
    private Integer pageSize;

    /**
     * 当前页数
     */
    @Schema(description = "当前页码")
    private Integer pageNumber;

    /**
     * 排序列
     */
    @Schema(description = "排序字段")
    private String sortColumn;

    /**
     * 排序的方式desc或者asc
     */
    @Schema(description = "排序方式desc或者asc")
    private String sortOrder;

}
