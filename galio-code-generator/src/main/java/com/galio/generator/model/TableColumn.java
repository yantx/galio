package com.galio.generator.model;

import com.galio.core.utils.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2024-01-28 20:31:44
 * @Description: 表字段信息
 */
@Data
public class TableColumn implements Serializable {

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
     * 对象属性类型
     */
    private String fieldType;

    /**
     * 对象属性名称
     */
    private String fieldName;

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
    private Integer orderNum;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;

    @Serial
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
        return isSuperColumn(fieldName);
    }

    public static boolean isSuperColumn(String fieldName) {
        return StringUtil.equalsAnyIgnoreCase(fieldName,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime",
                // TreeEntity
                "parentName", "parentId");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(fieldName);
    }

    public static boolean isUsableColumn(String fieldName) {
        // isSuperField()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtil.equalsAnyIgnoreCase(fieldName, "parentId", "orderNum", "remark");
    }

}
