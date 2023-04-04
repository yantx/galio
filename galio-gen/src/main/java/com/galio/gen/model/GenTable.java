package com.galio.gen.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

import com.galio.core.model.BaseEntity;
import com.galio.core.utils.StringUtil;
import com.galio.gen.constant.GenConstants;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 代码生成表
 * @TableName gen_table
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value ="gen_table")
public class GenTable extends BaseEntity {
    /**
     * 主键
     */
    @TableId("table_id")
    private Long tableId;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 代码生成表
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 关联子表的表名
     */
    private String subTableName;

    /**
     * 子表关联的外键名
     */
    private String subTableFkName;

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
    private String functionAuthor;

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
     * 树编码字段
     */
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @TableField(exist = false)
    private String treeName;

    /**
     * 表列信息
     */
    @Valid
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /**
     * 关联功能
     */
    @TableField(exist = false)
    private List<Long> functionIds;

    /**
     * 关联子表的表名
     */
    /**
     * 子表信息
     */
    @TableField(exist = false)
    private GenTable subTable;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private GenTableColumn pkColumn;


    @TableField(exist = false)
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

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtil.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtil.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtil.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
    
}