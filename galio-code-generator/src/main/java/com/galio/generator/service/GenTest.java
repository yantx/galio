//package com.galio.generator.service;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
//import com.baomidou.mybatisplus.generator.config.po.TableField;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.galio.core.model.BaseEntity;
//import com.galio.core.utils.ObjectUtil;
//import com.galio.generator.config.BaseConfig;
//import com.galio.generator.config.GeneratorConfig;
//import com.galio.generator.config.ThirdPartyToolsConfig;
//import com.galio.generator.model.TemplateItem;
//import com.galio.generator.utils.ConsoleUtil;
//import com.galio.generator.utils.StringHelper;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.NumberUtils;
//
//import java.sql.Types;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author Erwin Feng
// * @since 2019-04-17 12:04
// */
//@Service
//@Slf4j
//public class GenTest {
//    @Autowired
//    BaseConfig baseConfig;
//    @Autowired
//    ThirdPartyToolsConfig toolsConfig;
//    @Value("${galio.code.templateGroup}")
//    String template;
//    @Autowired
//    GeneratorConfig generatorConfig;
//    @Autowired
//    TemplateService templateService;
//
//
//    @Value("${galio.code.module}")
//    private boolean isNewModule;
//    @Value("${galio.code.excludedir}")
//    private String excludedir;
//
//    private final String VIEW = "view";
//    private final String JAVA = "java";
//    private final String XML = "xml";
//    private final String RESOURCE = "resource";
//
//    private String[] excludeDirArray;
//
//    @PostConstruct
//    private void init() {
//        if (excludedir == null || "".equalsIgnoreCase(excludedir.trim())) {
//            return;
//        }
//        excludeDirArray = excludedir.split(",");
//    }
//
//    private boolean isExclude(String relPath) {
//        if (excludeDirArray == null || excludedir.isEmpty()) {
//            return false;
//        }
//        for (String dir : excludeDirArray) {
//            if (relPath.startsWith(String.format("/%s", dir.trim()))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 数据连接信息
//     *
//     * @return DataSourceConfig
//     */
//    private DataSourceConfig dataSourceConfig() {
//        String datasource = generatorConfig.getValue("datasource");
//        datasource = datasource == null ? "dev" : datasource.trim();
//        String url = toolsConfig.getValue(datasource + "_datasource_url");
//        String username = toolsConfig.getValue(datasource + "_datasource_username");
//        String password = toolsConfig.getValue(datasource + "_datasource_password");
//        return new DataSourceConfig.Builder(url, username, password).typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//            if (typeCode == Types.SMALLINT) {
//                // 自定义类型转换
//                return DbColumnType.INTEGER;
//            }
//            return typeRegistry.getColumnType(metaInfo);
//        }).build();
//    }
//
//    // 配置
//    private GlobalConfig globalConfig() {
//        GlobalConfig.Builder config = new GlobalConfig.Builder()
//                .author(baseConfig.getValue("AUTHOR"))
//                .disableOpenDir() // 禁止打开输出目录
//                .outputDir(String.format("%s/%s/src/main/java", generatorConfig.getValue("outputDir"), generatorConfig.getValue("moduleName")))
//                .dateType(DateType.TIME_PACK); // 时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
//        if ("true".equalsIgnoreCase(baseConfig.getValue("swagger3"))) {
//            config.enableSwagger();
//        }
//        return config.build();
//    }
//
//
//    private StrategyConfig strategyConfig() {
//        StrategyConfig.Builder config = new StrategyConfig.Builder()
//                .addTablePrefix(generatorConfig.getTablePrefixes())// 此处可以修改为您的表前缀(数组)
//                .addFieldPrefix(generatorConfig.getFieldPrefixes()); // 字段前缀
//
//        if (!ObjectUtil.isEmpty(generatorConfig.getTableNames())) {
//            config.addInclude(generatorConfig.getTableNames());//修改替换成你需要的表名，多个表名传数组
//        }else if (!ObjectUtil.isEmpty(generatorConfig.getExclude())) {
//            config.addExclude(generatorConfig.getExclude());// 排除生成的表
//        }
//
//        if ("true".equalsIgnoreCase(baseConfig.getValue("enableSchema"))) {
//            config.enableSchema();
//        }
//        if ("true".equalsIgnoreCase(baseConfig.getValue("enableCapitalMode"))) {
//            config.enableCapitalMode();
//        }
//        if ("true".equalsIgnoreCase(baseConfig.getValue("enableSkipView"))) {
//            config.enableSkipView();
//        }
//        if ("true".equalsIgnoreCase(baseConfig.getValue("disableSqlFilter"))) {
//            config.disableSqlFilter();
//        }
//        config.entityBuilder()
//                .superClass(BaseEntity.class);
//        return config.build();
//    }
//
//    // 包信息配置
//    private PackageConfig packageConfig() {
//        return new PackageConfig.Builder()
//                .parent(generatorConfig.getValue("packageName"))
//                .controller(baseConfig.getValue("PACKAGE_NAME_CONTROLLER"))
//                .entity(baseConfig.getValue("PACKAGE_NAME_MODEL"))
//                .mapper(baseConfig.getValue("PACKAGE_NAME_DAO"))
//                .xml(baseConfig.getValue("PACKAGE_NAME_XML"))
//                .service(baseConfig.getValue("PACKAGE_NAME_SERVICE"))
//                .serviceImpl(baseConfig.getValue("PACKAGE_NAME_SERVICE_IMPL"))
//                .moduleName(generatorConfig.getValue("moduleName")).build();
//    }
//
//    /**
//     * 注入配置 自定义模版注入
//     * @param templateConfig
//     * @return
//     */
//    private InjectionConfig injectionConfig(final TemplateConfig templateConfig) {
//        Map<String, Object> map = new HashMap<>();
//        map.putAll(generatorConfig.getConfigMap());
//        map.putAll(baseConfig.getConfigMap());
//        map.putAll(toolsConfig.getConfigMap());
//        map.put("appName", generatorConfig.getValue("moduleName").toLowerCase());
//        map.put("varDescriptor", "$");
//        map.put("s", "$");
//        map.put("a", "@");
//        map.put("appModule", generatorConfig.getValue("moduleName").toLowerCase());
//        map.put("appVersion", generatorConfig.getValue("moduleVersion"));
//        String appClassName = StringHelper.toCamelCase(generatorConfig.getValue("moduleName"), true) + "App";
//        map.put("appClassName", appClassName);
//        String packagePath = generatorConfig.getValue("packageName").replace('.', '/');
//        map.put("packagePath", packagePath);
//        map.put("dbType", toolsConfig.getValue("datasource_dbType").trim().toLowerCase());
//        InjectionConfig.Builder injectionConfig = new InjectionConfig.Builder()
//                .beforeOutputFile((tableInfo, objectMap) -> {
//
//                    log.info("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
//                })
//                .customMap(map);
//        List<TemplateItem> templateItems = templateService.getTemplates();
//        if (ObjectUtil.isNull(templateItems) || templateItems.isEmpty()) {
//            return injectionConfig.build();
//        }
//
//        CustomFile.Builder customFile;
//        for (TemplateItem templateItem : templateItems) {
//            if (isExclude(templateItem.getRelativePath())) {
//                continue;
//            }
//            customFile = new CustomFile.Builder();
//            customFile.formatNameFunction(tableInfo -> StringHelper.repalceParams(templateItem.getOutFileName(), map, null))
//                    .fileName(StringHelper.repalceParams(templateItem.getOutFileName(), map, null))
//                    .filePath(templateService.getOutputFile(templateItem, map))
//                    .enableFileOverride();
//            injectionConfig.customFile(customFile.build());
//        }
//
//        return injectionConfig.build();
//    }
//
//    private void checkPrimary(TableInfo tableInfo) {
//        if (tableInfo == null) {
//            return;
//        }
//        List<TableField> fields = tableInfo.getFields();
//        if (fields == null || fields.size() < 1) {
//            return;
//        }
//        for (TableField field : fields) {
//            if (field.isKeyFlag()) {
//                return;
//            }
//        }
//
//        int keyFieldNum = getKeyFieldNum(fields, tableInfo.getName());
//        if (keyFieldNum >= 0 && keyFieldNum < fields.size()) {
//            log.info(" set  field [ {} ] as primary", fields.get(keyFieldNum).getName());
//            fields.get(keyFieldNum).primaryKey(true);
//        } else {
//            log.info("not right set primary key，random set field [ {} ] as primary", fields.get(0).getName());
//            fields.get(0).primaryKey(true);
//        }
//
//
//    }
//
//    private int getKeyFieldNum(List<TableField> fields, String tableName) {
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(String.format("please select one field as Primary for the table %s\n", tableName));
//        int i = 0;
//        for (TableField field : fields) {
//            buffer.append(String.format("%d : %s\n", i++, field.getName()));
//        }
//        buffer.append(String.format("please select the field num between 0 and %s : ", fields.size() - 1));
//        for (int loopNum = 0; loopNum < 3; loopNum++) {
//            String ret = ConsoleUtil.readConsole(buffer.toString());
////            log.info("read from console value: {}", ret);
//            int fildNum = NumberUtils.parseNumber(ret,Integer.class);
//            if (fildNum >= 0 && fildNum < fields.size()) {
//                return fildNum;
//            }
//        }
//        log.error("read from console not expect");
//        return -1;
//    }
//
//    private String getTemplate(String templateType, String templateName) {
//        return String.format("/%s/%s/%s", template, templateType, templateName);
//    }
//
//    private TemplateConfig templateConfigConfig() {
//        TemplateConfig.Builder templateConfig = new TemplateConfig.Builder()
//                .entity(getTemplate(JAVA, "entity.java"))
//                .controller(getTemplate(JAVA, "controller.java"))
//                .mapper(getTemplate(JAVA, "mapper.java"))
//                .xml(getTemplate(XML, "mapper.xml"))
//                .service(getTemplate(JAVA, "service.java"))
//                .serviceImpl(getTemplate(JAVA, "serviceImpl.java"));
//        return templateConfig.build();
//    }
//
//    /**
//     * 执行器,生成代码
//     */
//    public void execute() {
//        generatorConfig.getGenerator().put("moduleName", generatorConfig.getValue("moduleName").toLowerCase());
//        generatorConfig.getGenerator().put("viewModuleName", generatorConfig.getValue("viewModuleName").toLowerCase());
//
//        GlobalConfig globalConfig = globalConfig();
//        DataSourceConfig dataSourceConfig = dataSourceConfig();
//        StrategyConfig strategyConfig = strategyConfig();
//        PackageConfig packageConfig = packageConfig();
//        TemplateConfig templateConfig = templateConfigConfig();
//        InjectionConfig injectionConfig = injectionConfig(templateConfig);
//        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
//                .global(globalConfig)
//                .strategy(strategyConfig)
//                .packageInfo(packageConfig)
//                .template(templateConfig)
//                .injection(injectionConfig);
//        autoGenerator.execute();
//    }
//
//}
