package com.galio.mybatis.enums;

import com.galio.mybatis.helper.DataPermissionHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ocotpus
 * @Date: 2023-01-19
 * @Description: 数据权限类型
 * <p>
 * 语法支持 spel 模板表达式
 * <p>
 * 如需扩展数据 可使用 {@link DataPermissionHelper} 操作
 */
@Getter
@AllArgsConstructor
public enum DataScopeType {

    /**
     * 全部数据权限
     */
    ALL("1", "", ""),

    /**
     * 自定数据权限
     */
    CUSTOM("2", " #{#orgName} IN ( #{@sdss.getRoleCustom( #member.roleId )} ) ", ""),

    /**
     * 机构数据权限
     */
    ORG("3", " #{#orgName} = #{#user.orgId} ", ""),

    /**
     * 机构及以下数据权限
     */
    ORG_AND_CHILD("4", " #{#orgName} IN ( #{@sdss.getorgAndChild( #employee.orgId )} )", ""),

    /**
     * 仅本人数据权限
     */
    SELF("5", " #{#username} = #{#member.memberId} ", " 1 = 0 ");

    private final String code;

    /**
     * 语法 采用 spel 模板表达式
     */
    private final String sqlTemplate;

    /**
     * 不满足 sqlTemplate 则填充
     */
    private final String elseSql;

    public static DataScopeType findCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (DataScopeType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
