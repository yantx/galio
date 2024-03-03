package com.galio.generator.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "code")
@PropertySource("classpath:settings/generator.properties")
@Configuration
@Data
public class GeneratorConfig extends AbsConfig {
    /**
     * 配置信息
     */
    private Map<String, String> generator;

    // 表前缀，生成的实体类

    @Value("#{'${code.generator.table.tablePrefixes}'.split(',')}")
    public String[] tablePrefixes;
    @Value("${code.generator.table.autoRemovePrefix}")
    public Boolean autoRemovePrefix;
    // 表名，为空，生成所有的表
    @Value("#{'${code.generator.table.tableNames}'.split(',')}")
    public String[] tableNames;
    // 表名，为空，生成所有的表
    @Value("#{'${code.generator.table.exclude}'.split(',')}")
    public String[] exclude;
    // 字段前缀
    @Value("#{'${code.generator.table.fieldPrefixes}'.split(',')}")
    public String[] fieldPrefixes;


    @Override
    public Map<String, String> getConfigMap() {
        return getGenerator();
    }

}
