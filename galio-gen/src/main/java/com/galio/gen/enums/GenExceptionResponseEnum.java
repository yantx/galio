package com.galio.gen.enums;

import com.galio.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: ocotpus
 * @Date: 2023-02-25 18:11:18
 * @Description: 代码生成器服务异常响应信息枚举
 */
@Getter
public enum GenExceptionResponseEnum implements StatusCode {

    GEN_TABLE_IMPORT_ERROR(65000,  "gen.table.import.error"),
    GEN_TEMPLATE_WRITE_ERROR(65001,  "gen.template.write.error"),
    GEN_TEMPLATE_NOT_FOUND(65008,  "gen.template.not.found"),
    GEN_TABLE_STRUCT_NOT_FOUND(65002,  "gen.table.struct.not.found"),
    GEN_TREE_CODE_NOT_NULL(65003,  "gen.tree.code.not.null"),
    GEN_TREE_PARENT_CODE_NOT_NULL(65004,  "gen.tree.parent.code.not.null"),
    GEN_TREE_NAME_NOT_NULL(65005,  "gen.tree.name.not.null"),
    GEN_SUB_TABLE_NOT_NULL(65006,  "gen.sub.table.not.null"),
    GEN_SUB_TABLE_FK_NAME_NOT_NULL(65007,  "gen.sub.table.fk.name.not.null"),

    UNKNOWN_EXCEPTION(99999,"未知异常");
    private int code;
    private String msg;

    private Object[] args;

    GenExceptionResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public GenExceptionResponseEnum withArgs(Object... args) {
        this.args = args;
        return this;
    }
}
