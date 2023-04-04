package com.galio.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-01-11
 * @Description: 树结构基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeEntity<T> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 子节点集
     */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();

}
