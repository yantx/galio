package com.galio.gen.enums;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: ocotpus
 * @Date: 2023-02-25 18:11:18
 * @Description: 代码生成器服务异常响应信息枚举
 */
@Getter
public enum GenExceptionResponseEnum implements StatusCode {

    GEN_TABLE_IMPORT_ERROR(6500,  false,"gen.table.import.error"),
    GEN_TEMPLATE_WRITE_ERROR(6501,  false,"gen.template.write.error"),
    GEN_TABLE_STRUCT_NOT_FOUND(6504,  false,"gen.table.struct.not.found"),
    GEN_TREE_CODE_NOT_NULL(6504,  false,"gen.tree.code.not.null"),
    GEN_TREE_PARENT_CODE_NOT_NULL(6504,  false,"gen.tree.parent.code.not.null"),
    GEN_TREE_NAME_NOT_NULL(6504,  false,"gen.tree.name.not.null"),
    GEN_SUB_TABLE_NOT_NULL(6504,  false,"gen.sub.table.not.null"),
    GEN_SUB_TABLE_FK_NAME_NOT_NULL(6504,  false,"gen.sub.table.fk.name.not.null"),

    UNKNOWN_EXCEPTION(9999,false,"未知异常");
    private int code;
    private Boolean state;
    private String msg;

    private Object[] args;

    GenExceptionResponseEnum(int code, boolean state, String msg){
        this.code = code;
        this.state = state;
        this.msg = msg;
    }

    public GenExceptionResponseEnum packageByArgs(Object... args) {
        this.args = args;
        return this;
    }
}
