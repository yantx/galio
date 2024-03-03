package com.galio.gen.util;

import com.galio.core.utils.Assert;
import com.galio.core.utils.DateTimeUtil;
import com.galio.core.utils.JsonUtils;
import com.galio.core.utils.StringUtil;
import com.galio.gen.constant.GenConstants;
import com.galio.gen.enums.GenExceptionResponseEnum;
import com.galio.gen.model.GenTable;
import com.galio.gen.model.GenTableColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.velocity.VelocityContext;

import java.util.*;
import java.util.function.Function;

/**
 * @Author: galio
 * @Date: 2023-01-17
 * @Description: 模板工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityUtils {

    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 默认上级菜单，系统工具
     */
    private static final String DEFAULT_PARENT_MENU_ID = "3";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable) {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtil.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtil.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("BusinessName", StringUtil.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateTimeUtil.getLocalDateStr());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        velocityContext.put("dicts", getDicts(genTable));
        setMenuVelocityContext(velocityContext, genTable);
        if (GenConstants.TPL_TREE.equals(tplCategory)) {
            setTreeVelocityContext(velocityContext, genTable);
        }
        if (GenConstants.TPL_SUB.equals(tplCategory)) {
            setSubVelocityContext(velocityContext, genTable);
        }
        return velocityContext;
    }

    public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        LinkedHashMap<String, Object> paramsObj = JsonUtils.toObject(options, LinkedHashMap.class);
        String parentMenuId = getParentMenuId(paramsObj);
        context.put("parentMenuId", parentMenuId);
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        LinkedHashMap<String, Object> paramsObj = JsonUtils.toObject(options, LinkedHashMap.class);
        String treeCode = getTreeCode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            context.put("tree_parent_code", paramsObj.get(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            context.put("tree_name", paramsObj.get(GenConstants.TREE_NAME));
        }
    }

    public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
        GenTable subTable = genTable.getSubTable();
        String subTableName = genTable.getSubTableName();
        String subTableFkName = genTable.getSubTableFkName();
        String subClassName = genTable.getSubTable().getClassName();
        String subTableFkClassName = StringUtil.lineToHump(subTableFkName);

        context.put("subTable", subTable);
        context.put("subTableName", subTableName);
        context.put("subTableFkName", subTableFkName);
        context.put("subTableFkClassName", subTableFkClassName);
        context.put("subTableFkclassName", StringUtil.uncapitalize(subTableFkClassName));
        context.put("subClassName", subClassName);
        context.put("subclassName", StringUtil.uncapitalize(subClassName));
        context.put("subImportList", getImportList(genTable.getSubTable()));
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory) {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/entity.java.vm");
        templates.add("vm/java/dto.java.vm");
        templates.add("vm/java/queryDTO.java.vm");
        templates.add("vm/java/vo.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/repository.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
//        if (DataBaseHelper.isOracle()) {
//            templates.add("vm/sql/oracle/sql.vm");
//        } else if (DataBaseHelper.isPostgerSql()) {
//            templates.add("vm/sql/postgres/sql.vm");
//        } else {
//            templates.add("vm/sql/sql.vm");
//        }
//        templates.add("vm/js/api.js.vm");
//        if (GenConstants.TPL_CRUD.equals(tplCategory)) {
//            templates.add("vm/vue/index.vue.vm");
//        } else if (GenConstants.TPL_TREE.equals(tplCategory)) {
//            templates.add("vm/vue/index-tree.vue.vm");
//        } else if (GenConstants.TPL_SUB.equals(tplCategory)) {
//            templates.add("vm/vue/index.vue.vm");
//            templates.add("vm/java/sub-entity.java.vm");
//        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable) {
        // 文件名称
        String fileName = "";
        Function<String,String> javaPathFun = (packageName) -> { return PROJECT_PATH + "/" + StringUtil.replace(packageName, ".", "/");};
        Function<String,String> mybatisPathFun = (moduleName) -> {return MYBATIS_PATH + "/" + moduleName;};
        String vuePath = "vue";
        Map<String, Function<GenTable,String>> templateMap = new HashMap<>();
        templateMap.put("vm/java/entity.java.vm", (params) -> StringUtil.format("{}/model/{}.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/vo.java.vm", (params) -> StringUtil.format("{}/model/vo/{}VO.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/dto.java.vm", (params) -> StringUtil.format("{}/model/dto/{}DTO.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/queryDTO.java.vm", (params) -> StringUtil.format("{}/model/dto/{}PageReqDTO.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/sub-entity.java.vm", (params) -> StringUtil.format("{}/model/{}.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/mapper.java.vm", (params) -> StringUtil.format("{}/mapper/{}Mapper.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/repository.java.vm", (params) -> StringUtil.format("{}/repository/{}Repository.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/service.java.vm", (params) -> StringUtil.format("{}/service/{}Service.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/serviceImpl.java.vm", (params) -> StringUtil.format("{}/service/impl/{}ServiceImpl.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/java/controller.java.vm", (params) -> StringUtil.format("{}/controller/{}Controller.java", javaPathFun.apply(params.getPackageName()), params.getClassName()));
        templateMap.put("vm/xml/mapper.xml.vm", (params) -> StringUtil.format("{}/{}Mapper.xml", mybatisPathFun.apply(params.getModuleName()), params.getClassName()));
        templateMap.put("vm/sql/sql.vm", (params) -> StringUtil.format("{}.sql", params.getBusinessName()));
        templateMap.put("vm/js/api.js.vm", (params) -> StringUtil.format("{}/api/{}/{}.js", vuePath, params.getModuleName(), params.getBusinessName()));
        templateMap.put("vm/vue/index.vue.vm", (params) -> StringUtil.format("{}/views/{}/{}/index.vue", vuePath, params.getModuleName(), params.getBusinessName()));
        templateMap.put("vm/vue/index-tree.vue.vm", (params) -> StringUtil.format("{}/views/{}/{}/index.vue", vuePath, params.getModuleName(), params.getBusinessName()));

        Assert.isTrue(templateMap.containsKey(template), GenExceptionResponseEnum.GEN_TEMPLATE_NOT_FOUND.withArgs(template));
        fileName = templateMap.get(template).apply(genTable);

        return fileName;
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        return StringUtil.substring(packageName, 0, lastIndex);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        GenTable subGenTable = genTable.getSubTable();
        HashSet<String> importList = new HashSet<String>();
        if (ObjectUtils.isNotEmpty(subGenTable)) {
            importList.add("java.util.List");
        }
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_LOCALDATETIME.equals(column.getJavaType())) {
                importList.add("java.time.LocalDateTime");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_LOCALDATE.equals(column.getJavaType())) {
                importList.add("java.time.LocalDate");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 根据列类型获取字典组
     *
     * @param genTable 业务表对象
     * @return 返回字典组
     */
    public static String getDicts(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        Set<String> dicts = new HashSet<String>();
        addDicts(dicts, columns);
        if (ObjectUtils.isNotEmpty(genTable.getSubTable())) {
            List<GenTableColumn> subColumns = genTable.getSubTable().getColumns();
            addDicts(dicts, subColumns);
        }
        return StringUtil.join(dicts, ", ");
    }

    /**
     * 添加字典列表
     *
     * @param dicts   字典列表
     * @param columns 列集合
     */
    public static void addDicts(Set<String> dicts, List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn() && StringUtil.isNotEmpty(column.getDictType()) && StringUtil.equalsAny(
                    column.getHtmlType(),
                    new String[] { GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_CHECKBOX })) {
                dicts.add("'" + column.getDictType() + "'");
            }
        }
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName   模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName) {
        return StringUtil.format("{}:{}", moduleName, businessName);
    }

    /**
     * 获取上级菜单ID字段
     *
     * @param paramsObj 生成其他选项
     * @return 上级菜单ID字段
     */
    public static String getParentMenuId(Map<String, Object> paramsObj) {
        if (MapUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.PARENT_MENU_ID)
                && StringUtil.isNotEmpty(paramsObj.get(GenConstants.PARENT_MENU_ID).toString())) {
            return paramsObj.get(GenConstants.PARENT_MENU_ID).toString();
        }
        return DEFAULT_PARENT_MENU_ID;
    }

    /**
     * 获取树编码
     *
     * @param paramsObj 生成其他选项
     * @return 树编码
     */
    public static String getTreeCode(Map<String, Object> paramsObj) {
        if (MapUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.TREE_CODE)) {
            return StringUtil.lineToHump(paramsObj.get(GenConstants.TREE_CODE).toString());
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取树父编码
     *
     * @param paramsObj 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(Map<String, Object> paramsObj) {
        if (MapUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            return StringUtil.lineToHump(paramsObj.get(GenConstants.TREE_PARENT_CODE).toString());
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取树名称
     *
     * @param paramsObj 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(Map<String, Object> paramsObj) {
        if (MapUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.TREE_NAME)) {
            return StringUtil.lineToHump(paramsObj.get(GenConstants.TREE_NAME).toString());
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(GenTable genTable) {
        String options = genTable.getOptions();
        LinkedHashMap paramsObj = JsonUtils.toObject(options, LinkedHashMap.class);
        String treeName = JsonUtils.toString(paramsObj.get(GenConstants.TREE_NAME));
        int num = 0;
        for (GenTableColumn column : genTable.getColumns()) {
            if (column.isList()) {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName)) {
                    break;
                }
            }
        }
        return num;
    }
}
