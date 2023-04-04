package com.galio.gen.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.galio.core.model.BaseEntity;
import com.galio.core.utils.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 代码生成业务表字段
 * @TableName gen_table_column
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table_column")
public class GenTableColumn extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "column_id")
    private Long columnId;

    /**
     * 归属表id
     */
    private Long tableId;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * java类型
     */
    private String javaType;

    /**
     * java字段名
     */
    private String javaField;

    /**
     * 是否主键（1是）
     */
    private String isPk;

    /**
     * 是否自增（1是）
     */
    private String isIncrement;

    /**
     * 是否必填（1是）
     */
    private String isRequired;

    /**
     * 是否为插入字段（1是）
     */
    private String isInsert;

    /**
     * 是否编辑字段（1是）
     */
    private String isEdit;

    /**
     * 是否列表字段（1是）
     */
    private String isList;

    /**
     * 是否查询字段（1是）
     */
    private String isQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    private String queryType;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序
     */
    private Object orderNum;

    /**
     * 应用id
     */
    private Long appId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtil.equals("1", isPk);
    }

    public boolean isIncrement() {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement) {
        return isIncrement != null && StringUtil.equals("1", isIncrement);
    }

    public boolean isRequired() {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired) {
        return isRequired != null && StringUtil.equals("1", isRequired);
    }

    public boolean isInsert() {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert) {
        return isInsert != null && StringUtil.equals("1", isInsert);
    }

    public boolean isEdit() {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit) {
        return isEdit != null && StringUtil.equals("1", isEdit);
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtil.equals("1", isList);
    }

    public boolean isQuery() {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery) {
        return isQuery != null && StringUtil.equals("1", isQuery);
    }

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtil.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime",
                // TreeEntity
                "parentName", "parentId");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtil.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }
}