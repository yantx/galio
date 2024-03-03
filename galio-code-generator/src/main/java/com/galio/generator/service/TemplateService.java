package com.galio.generator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galio.generator.model.TemplateItem;
import com.galio.generator.config.GeneratorConfig;
import com.galio.generator.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TemplateService {
    @Value("${galio.code.templateGroup}")
    String codePath;
    @Autowired
    GeneratorConfig generatorConfig;

    @Value("classpath:${galio.code.templateGroup}/templateDefine.json")
    private Resource templateJsonResource;

    public List<TemplateItem> getTemplates() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(templateJsonResource.getInputStream(), new TypeReference<List<TemplateItem>>() {
            });
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取模版路径
     *
     * @return
     */
    public String getTemplatePath(TemplateItem item) {
        String relPath = item.getRelativePath();
        if (relPath != null && !"".equalsIgnoreCase(relPath.trim())) {
            return String.format("/%s/%s/%s", codePath, relPath, item.getName());
        }
        return String.format("/%s/%s", codePath, item.getName());
    }

}