package com.galio.generator.service;

import com.galio.generator.config.BaseConfig;
import com.galio.generator.config.GeneratorConfig;
import com.galio.generator.mapper.GeneratorMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneratorService {
    @Resource
    GeneratorConfig generatorConfig;
    @Resource
    Generator generator;

    public void generalCode() {
        String outDir = generatorConfig.getValue("outputDir");
        log.info("generatorCode start ，output dir: {}", outDir);
        try {
            generator.execute();
        } catch (Exception e) {
            log.error("generatorCode error：", e);
        }
        log.info("generatorCode finish ，output dir:{}", outDir);
    }

}
