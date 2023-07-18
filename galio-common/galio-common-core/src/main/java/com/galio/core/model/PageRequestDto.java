package com.galio.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2023-01-19
 * @Description: 分页查询参数
 */
@Data
public class PageRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc;

}
