package com.galio.generator.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "code.generator")
@PropertySource("classpath:settings/generator.properties")
@Configuration
@Data
public class ElementConfig extends AbsConfig {
    /**
     * 配置信息
     */
    private Map<String, String> element;


    @Override
    public Map<String, String> getConfigMap() {
        return getElement();
    }

}
