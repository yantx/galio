package com.galio.mybatis.handler;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.SpringUtils;
import com.galio.core.utils.StreamUtils;
import com.galio.core.utils.StringUtil;
import com.galio.mybatis.annotation.DataColumn;
import com.galio.mybatis.annotation.DataPermission;
import com.galio.mybatis.enums.DataScopeType;
import com.galio.mybatis.enums.MybatisResponseEnum;
import com.galio.mybatis.helper.DataPermissionHelper;
import com.galio.satoken.tools.helper.LoginHelper;
import com.galio.system.dto.LoginMemberDto;
import com.galio.system.dto.RoleDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @Author: galio
 * @Date: 2023-02-02
 * @Description: 数据权限处理
 */
@Slf4j
@Component
public class DataScopePermissionHandler {

    /**
     * 方法或类(名称) 与 注解的映射关系缓存
     */
    private final Map<String, DataPermission> dataPermissionCacheMap = new ConcurrentHashMap<>();

    /**
     * 无效注解方法缓存用于快速返回
     */
    private final Set<String> invalidCacheSet =  ConcurrentHashMap.newKeySet();

    /**
     * spel 解析器
     */
    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParserContext parserContext = new TemplateParserContext();
    /**
     * bean解析器 用于处理 spel 表达式中对 bean 的调用
     */
    private final BeanResolver beanResolver = new BeanFactoryResolver(SpringUtils.getBeanFactory());

    public Expression getSqlSegment(Expression where, String mappedStatementId, boolean isSelect) {
        try {
            DataColumn[] dataColumns = findAnnotation(mappedStatementId);
            if (ObjectUtil.isNull(dataColumns) || ArrayUtils.isEmpty(dataColumns)) {
                invalidCacheSet.add(mappedStatementId);
                return where;
            }
            LoginMemberDto loginMemberDto = DataPermissionHelper.getVariable("user");
            if (ObjectUtil.isNull(loginMemberDto)) {
                loginMemberDto = LoginHelper.getLoginMember();
                DataPermissionHelper.setVariable("user" , loginMemberDto);
            }
            // 如果是超级管理员，则不过滤数据
            if (ObjectUtil.isNull(loginMemberDto) || loginMemberDto.isSuperAdmin()) {
                return where;
            }else if (loginMemberDto.isAdmin()){
                return CCJSqlParserUtil.parseExpression(" app_id = " + loginMemberDto.getAppId());
            }
            String dataFilterSql = buildDataFilter(dataColumns, isSelect);
            if (StringUtil.isBlank(dataFilterSql)) {
                return where;
            }

            Expression expression = CCJSqlParserUtil.parseExpression(dataFilterSql);
            // 数据权限使用单独的括号 防止与其他条件冲突
            Parenthesis parenthesis = new Parenthesis(expression);
            if (ObjectUtil.isNotNull(where)) {
                return new AndExpression(where, parenthesis);
            } else {
                return parenthesis;
            }
        } catch (JSQLParserException e) {
            log.error("数据权限解析异常 => " + e.getMessage());
            throw new CustomException(ResponseEnum.DATA_PERMISSION_PARSER_ERROR);
        } catch (ClassNotFoundException e) {
            throw new CustomException(ResponseEnum.CLASS_NOT_FOUND);
        }
    }

    private String buildDataFilter(DataColumn[] dataColumns, boolean isSelect) {
        // 更新或删除需满足所有条件
        String joinStr = isSelect ? " OR " : " AND ";
        LoginMemberDto memberDto = DataPermissionHelper.getVariable("user");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(beanResolver);
        DataPermissionHelper.getContext().forEach(context::setVariable);
        Set<String> conditions = new HashSet<>();
        for (RoleDto role : memberDto.getRoles()) {
            // 获取角色权限泛型
            DataScopeType type = DataScopeType.findCode(role.getDataScope());
            if (ObjectUtil.isNull(type)) {
                throw new CustomException(ResponseEnum.NO_ROLE_DATA_SCOPE);
            }
            // 全部数据权限直接返回
            if (type == DataScopeType.ALL) {
                return "";
            }
            boolean isSuccess = false;
            for (DataColumn dataColumn : dataColumns) {
                if (dataColumn.key().length != dataColumn.value().length) {
                    throw new CustomException(MybatisResponseEnum.ROLE_DATA_SCOPE_ERROR);
                }
                // 不包含 key 变量 则不处理
                if (!StringUtil.containsAny(type.getSqlTemplate(),
                        Arrays.stream(dataColumn.key()).map(key -> "#" + key).toArray(String[]::new)
                )) {
                    continue;
                }
                // 设置注解变量 key 为表达式变量 value 为变量值
                for (int i = 0; i < dataColumn.key().length; i++) {
                    context.setVariable(dataColumn.key()[i], dataColumn.value()[i]);
                }

                // 解析sql模板并填充
                String sql = parser.parseExpression(type.getSqlTemplate(), parserContext).getValue(context, String.class);
                conditions.add(joinStr + sql);
                isSuccess = true;
            }
            // 未处理成功则填充兜底方案
            if (!isSuccess && StringUtil.isNotBlank(type.getElseSql())) {
                conditions.add(joinStr + type.getElseSql());
            }
        }

        if (CollectionUtils.isNotEmpty(conditions)) {
            String sql = StreamUtils.join(conditions, Function.identity(), "");
            return sql.substring(joinStr.length());
        }
        return "";
    }

    private DataColumn[] findAnnotation(String mappedStatementId) throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder(mappedStatementId);
        int index = sb.lastIndexOf(".");
        String clazzName = sb.substring(0, index);
        String methodName = sb.substring(index + 1, sb.length());
        Class<?> clazz = ClassUtils.getClass(clazzName);
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().equals(methodName)).collect(toList());
        DataPermission dataPermission;
        // 获取方法注解
        for (Method method : methods) {
            dataPermission = dataPermissionCacheMap.get(mappedStatementId);
            if (ObjectUtil.isNotNull(dataPermission)) {
                return dataPermission.value();
            }
            if (ObjectUtil.isNotNull(AnnotationUtils.findAnnotation(method, DataPermission.class))) {
                dataPermission = AnnotationUtils.findAnnotation(method, DataPermission.class);
                dataPermissionCacheMap.put(mappedStatementId, dataPermission);
                return dataPermission.value();
            }
        }
        dataPermission = dataPermissionCacheMap.get(clazz.getName());
        if (ObjectUtil.isNotNull(dataPermission)) {
            return dataPermission.value();
        }
        // 获取类注解
        if (ObjectUtil.isNotNull(AnnotationUtils.findAnnotation(clazz, DataPermission.class))) {
            dataPermission = AnnotationUtils.findAnnotation(clazz, DataPermission.class);
            dataPermissionCacheMap.put(clazz.getName(), dataPermission);
            return dataPermission.value();
        }
        return null;
    }

    /**
     * 是否为无效方法 无数据权限
     */
    public boolean isInvalid(String mappedStatementId) {
        return invalidCacheSet.contains(mappedStatementId);
    }
}
