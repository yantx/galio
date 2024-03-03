package com.galio.generator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galio.core.utils.StringUtil;
import com.galio.generator.constants.GenConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2024-01-28 20:29:11
 * @Description: 代码生成表属性类
 */
@Data
public class TableInfo implements Serializable {
    /**
     * 代码生成表
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 实体类名称
     */
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成业务名
     */
    private String businessName;

    /**
     * 生成功能名
     */
    private String functionName;

    /**
     * 生成功能作者
     */
    private String author;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 是否中间表 1-是 0-否
     */
    private String isMid;
    /**
     * 树编码字段
     */
    private String treeCode;

    /**
     * 树父编码字段
     */
    private String treeParentCode;


    /**
     * 表列信息
     */
    private List<TableColumn> columns;

    /**
     * 关联功能
     */
    private List<Long> functionIds;

    /**
     * 主键信息
     */
    private TableColumn primaryKeyField;
    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;


    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtil.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtil.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }
    public boolean isPagination() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtil.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String fieldName) {
        return isSuperColumn(this.tplCategory, fieldName);
    }

    public static boolean isSuperColumn(String tplCategory, String fieldName) {
        if (isTree(tplCategory)) {
            return StringUtil.equalsAnyIgnoreCase(fieldName,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtil.equalsAnyIgnoreCase(fieldName, GenConstants.BASE_ENTITY);
    }
}
