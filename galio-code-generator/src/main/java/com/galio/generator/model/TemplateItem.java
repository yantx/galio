package com.galio.generator.model;

import lombok.Data;

/**
 * 模版定义
 */
@Data
public class TemplateItem {
    /**
     * 模版名称
     */
    private String name;
    private String relativePath;
    private String outPath;
    private String outFileName;
}
