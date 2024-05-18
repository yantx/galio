package com.galio.generator.service;

import com.galio.core.text.CharsetKit;
import com.galio.core.utils.DateTimeUtil;
import com.galio.core.utils.StringUtil;
import com.galio.generator.config.BaseConfig;
import com.galio.generator.config.TableConfig;
import com.galio.generator.config.GeneratorConfig;
import com.galio.generator.config.ThirdPartyToolsConfig;
import com.galio.generator.constants.GenConstants;
import com.galio.generator.init.VelocityInitializer;
import com.galio.generator.mapper.GeneratorMapper;
import com.galio.generator.model.TableColumn;
import com.galio.generator.model.TableInfo;
import com.galio.generator.model.TemplateItem;
import com.galio.generator.utils.ConsoleUtil;
import com.galio.generator.utils.GeneratorUtils;
import com.galio.generator.utils.StringHelper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.io.File;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Erwin Feng
 * @since 2019-04-17 12:04
 */
@Service
@Slf4j
public class Generator {
    @Autowired
    BaseConfig baseConfig;
    @Autowired
    ThirdPartyToolsConfig toolsConfig;
    @Autowired
    GeneratorConfig generatorConfig;
    @Autowired
    TableConfig tableConfig;
    @Autowired
    TemplateService templateService;
    @Resource
    GeneratorMapper generatorMapper;

    @Value("${galio.code.templateGroup}")
    String template;
    @Value("${galio.code.module}")
    private boolean isNewModule;
    @Value("${galio.code.excludedir}")
    private String excludedir;

    private final String VIEW = "view";
    private final String JAVA = "java";
    private final String XML = "xml";
    private final String RESOURCE = "resource";

    private String[] excludeDirArray;

    @PostConstruct
    private void init() {
        if (excludedir == null || "".equalsIgnoreCase(excludedir.trim())) {
            return;
        }
        excludeDirArray = excludedir.split(",");
    }

    private boolean isExclude(String relPath) {
        if (excludeDirArray == null || excludedir.isEmpty()) {
            return false;
        }
        for (String dir : excludeDirArray) {
            if (relPath.startsWith(String.format("/%s", dir.trim()))) {
                return true;
            }
        }
        return false;
    }


    /**
     * 注入配置 自定义模版注入
     * @param tableInfo
     * @return
     */
    private VelocityContext prepareContext(final TableInfo tableInfo) {

        String moduleName = generatorConfig.getValue("moduleName");
        String businessName = tableInfo.getBusinessName();
        String functionName = tableInfo.getFunctionName();
        String packageName = generatorConfig.getValue("packageName");
        String packagePath = packageName.replace('.', '/');
        String appClassName = StringHelper.toCamelCase(generatorConfig.getValue("moduleName"), true) + "App";

        VelocityContext velocityContext = new VelocityContext();
        // 项目整体相关配置
        velocityContext.put("s", "$");
        velocityContext.put("a", "@");
        velocityContext.put("varDescriptor", "$");
        velocityContext.put("author", baseConfig.getValue("AUTHOR"));
        velocityContext.put("datetime", DateTimeUtil.getLocalDateStr());
        velocityContext.put("moduleName", tableInfo.getModuleName());
        velocityContext.put("viewModuleName", tableInfo.getModuleName());
        velocityContext.put("appClassName", appClassName);
        velocityContext.put("appModule", generatorConfig.getValue("moduleName"));
        velocityContext.put("appVersion", generatorConfig.getValue("moduleVersion"));
        velocityContext.put("dbType", toolsConfig.getValue("datasource_dbType").trim().toLowerCase());

        // 单独功能相关配置
        velocityContext.put("tplCategory", tableInfo.getTplCategory());
        velocityContext.put("tableName", tableInfo.getTableName());
        velocityContext.put("functionName", StringUtil.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", tableInfo.getClassName());
        velocityContext.put("className", StringUtil.uncapitalize(tableInfo.getClassName()));
        velocityContext.put("entity", tableInfo.getClassName());
        velocityContext.put("entityLower", StringUtil.uncapitalize(tableInfo.getClassName()));
        velocityContext.put("BusinessName", StringUtil.capitalize(tableInfo.getBusinessName()));
        velocityContext.put("businessName", tableInfo.getBusinessName());
        velocityContext.put("packageName", packageName);
        velocityContext.put("packagePath", packagePath);
        velocityContext.put("pkColumn", tableInfo.getPrimaryKeyField());
        velocityContext.put("importList", getImportList(tableInfo));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", tableInfo.getColumns());
        velocityContext.put("table", tableInfo);
        velocityContext.put("dicts", getDicts(tableInfo));

        for (String key : generatorConfig.getGenerator().keySet()) {
            velocityContext.put(key, generatorConfig.getValue(key));
        }
        for (String key : toolsConfig.getConfigMap().keySet()) {
            velocityContext.put(key, toolsConfig.getValue(key));
        }
        for (String key : baseConfig.getConfigMap().keySet()) {
            velocityContext.put(key, baseConfig.getValue(key));
        }

        return velocityContext;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param tableInfo 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(TableInfo tableInfo) {
        List<TableColumn> columns = tableInfo.getColumns();
        HashSet<String> importList = new HashSet<String>();
        for (TableColumn column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getFieldType())) {
                importList.add("java.util.Date");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_LOCALDATETIME.equals(column.getFieldType())) {
                importList.add("java.time.LocalDateTime");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_LOCALDATE.equals(column.getFieldType())) {
                importList.add("java.time.LocalDate");
            }else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getFieldType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
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
     * 根据列类型获取字典组
     *
     * @param genTable 业务表对象
     * @return 返回字典组
     */
    public static String getDicts(TableInfo genTable) {
        List<TableColumn> columns = genTable.getColumns();
        Set<String> dicts = new HashSet<String>();
        addDicts(dicts, columns);
        return StringUtil.join(dicts, ", ");
    }
    /**
     * 添加字典列表
     *
     * @param dicts   字典列表
     * @param columns 列集合
     */
    public static void addDicts(Set<String> dicts, List<TableColumn> columns) {
        for (TableColumn column : columns) {
            if (!column.isSuperColumn() && StringUtil.isNotEmpty(column.getDictType()) && StringUtil.equalsAny(
                    column.getHtmlType(),
                    new String[] { GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_CHECKBOX })) {
                dicts.add("'" + column.getDictType() + "'");
            }
        }
    }
    private void checkPrimary(TableInfo tableInfo) {
        if (tableInfo == null) {
            return;
        }
        List<TableColumn> fields = tableInfo.getColumns();
        if (fields == null || fields.isEmpty()) {
            return;
        }
        for (TableColumn field : fields) {
            if (field.isPk()) {
                return;
            }
        }

        int keyFieldNum = getKeyFieldNum(fields, tableInfo.getTableName());
        if (keyFieldNum >= 0 && keyFieldNum < fields.size()) {
            log.info(" set  field [ {} ] as primary", fields.get(keyFieldNum).getFieldName());
            fields.get(keyFieldNum).isPk("1");
        } else {
            log.info("not right set primary key，random set field [ {} ] as primary", fields.get(0).getFieldName());
            fields.get(0).isPk("1");
        }


    }

    private int getKeyFieldNum(List<TableColumn> fields, String tableName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("please select one field as Primary for the table %s\n", tableName));
        int i = 0;
        for (TableColumn field : fields) {
            buffer.append(String.format("%d : %s\n", i++, field.getColumnName()));
        }
        buffer.append(String.format("please select the field num between 0 and %s : ", fields.size() - 1));
        for (int loopNum = 0; loopNum < 3; loopNum++) {
            String ret = ConsoleUtil.readConsole(buffer.toString());
//            log.info("read from console value: {}", ret);
            int fildNum = NumberUtils.parseNumber(ret,Integer.class);
            if (fildNum >= 0 && fildNum < fields.size()) {
                return fildNum;
            }
        }
        log.error("read from console not expect");
        return -1;
    }

    private String getTemplateFilePath(String templateType, String templateName) {
//        return String.format("/%s/%s", templateType, templateName);
        return String.format("%s/%s/%s", template, templateType, templateName);
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(TableInfo table) {
        for (TableColumn field : table.getColumns()) {
            if (field.isPk()) {
                table.setPrimaryKeyField(field);
                break;
            }
        }
        if (ObjectUtils.isEmpty(table.getPrimaryKeyField())) {
            table.setPrimaryKeyField(table.getColumns().get(0));
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param context    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public String getGenPath(VelocityContext context, TemplateItem template) {
        String outPath = StringHelper.repalceParams(template.getOutPath()+File.separator+template.getOutFileName(), context, null);
        String outputDir = generatorConfig.getValue("outputDir");
        return String.format("%s/%s", outputDir, outPath);
    }

    public List<TableInfo> initTable(String[] tableNames) {
        List<TableInfo> result = new ArrayList<>();
        try {
            List<TableInfo> tableList = generatorMapper.selectDbTableListByNames(List.of(tableNames));
            Map<String, String> temp = tableConfig.getConfigMap();
            for (TableInfo table : tableList) {
                String tableName = table.getTableName();
                table.setTplCategory(temp.getOrDefault(tableName+".tplCategory", "crud"));
                table.setClassName(GeneratorUtils.convertClassName(table.getTableName(),generatorConfig.getTablePrefixes(),generatorConfig.getAutoRemovePrefix()));
                table.setPackageName(generatorConfig.getValue("packageName"));
                table.setModuleName(generatorConfig.getValue("moduleName"));
                table.setBusinessName(GeneratorUtils.getBusinessName(table.getTableName()));
                table.setFunctionName(GeneratorUtils.replaceText(table.getTableComment()));
                table.setAuthor(baseConfig.getValue("AUTHOR"));
                // 保存列信息
                List<TableColumn> tableColumns = generatorMapper.selectDbTableColumnsByName(tableName);
                List<TableColumn> saveColumns = new ArrayList<>();
                for (TableColumn column : tableColumns) {
                    initColumnField(column);
                    saveColumns.add(column);
                }
                table.setColumns(saveColumns);
                result.add(table);
            }
        } catch (Exception e) {
            log.error("initTable error: ", e);
//            throw new CustomException(GenExceptionResponseEnum.GEN_TABLE_IMPORT_ERROR);
        }
        return result;
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(TableColumn column) {
        String dataType = GeneratorUtils.getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        // 设置java字段名
        column.setFieldName(StringUtil.lineToHump(columnName));
        // 设置默认类型
        column.setFieldType(GenConstants.TYPE_STRING);
        column.setQueryType(GenConstants.QUERY_EQ);

        if (GeneratorUtils.arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || GeneratorUtils.arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
            // 字符串长度超过500设置为文本域
            Integer columnLength = GeneratorUtils.getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || GeneratorUtils.arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        } else if (GeneratorUtils.arraysContains(GenConstants.COLUMNTYPE_DATETIME, dataType)) {
            column.setFieldType(GenConstants.TYPE_LOCALDATETIME);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        }  else if (dataType.equals(GenConstants.COLUMNTYPE_DATE)) {
            column.setFieldType(GenConstants.TYPE_LOCALDATE);
            column.setHtmlType(GenConstants.HTML_DATE);
        }  else if (GeneratorUtils.arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtil.split(StringUtil.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setFieldType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                column.setFieldType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setFieldType(GenConstants.TYPE_LONG);
            }
        }

        // BO对象 默认插入勾选
        if (!GeneratorUtils.arraysContains(GenConstants.COLUMNNAME_NOT_ADD, columnName) && !column.isPk()) {
            column.setIsInsert(GenConstants.REQUIRE);
        }
        // BO对象 默认编辑勾选
        if (!GeneratorUtils.arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // BO对象 默认是否必填勾选
        if (!GeneratorUtils.arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsRequired(GenConstants.REQUIRE);
        }
        // VO对象 默认返回勾选
        if (!GeneratorUtils.arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName)) {
            column.setIsList(GenConstants.REQUIRE);
        }
        // BO对象 默认查询勾选
        if (!GeneratorUtils.arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk()) {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtil.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtil.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtil.endsWithIgnoreCase(columnName, "type")
                || StringUtil.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtil.endsWithIgnoreCase(columnName, "image")) {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
        }
        // 文件字段设置文件上传控件
        else if (StringUtil.endsWithIgnoreCase(columnName, "file")) {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (StringUtil.endsWithIgnoreCase(columnName, "content")) {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    /**
     * 执行器,生成代码
     */
    public void execute() {
        // 获取表列表
        List<TableInfo> tableList = initTable(generatorConfig.getTableNames());
        // 获取模板列表
        List<TemplateItem> templates = templateService.getTemplates();
        // 初始化模板引擎
        VelocityInitializer.initVelocity();
        for (TableInfo tableInfo : tableList) {
            setPkColumn(tableInfo);
            VelocityContext context = prepareContext(tableInfo);
            for (TemplateItem template : templates) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(getTemplateFilePath(template.getRelativePath(),template.getName()), CharsetKit.UTF_8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(context, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), CharsetKit.UTF_8);
                } catch (Exception e) {
                    log.error("Table:[{}]Generator, {} write error: {}", tableInfo.getTableName(), template.getName(), e.getMessage());
                }
            }
        }
    }

}
