package com.galio.generator.config;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsConfig {

    /**
     * 获取与给定键关联的值。
     *
     * @param  key  要查找值的键
     * @return      与键关联的值，如果键为null、空或未找到，则返回null
     */
    public String getValue(String key) {
        if (key == null || "".equalsIgnoreCase(key.trim())) {
            return null;
        }
        key = key.trim();
        if (formatMap().containsKey(key)) {
            String value = formatMap().get(key);
            if (value == null || value.trim().isEmpty())
                return null;
            return value;
        }
        return null;
    }

    /**
     * 生成一个包含格式化键值对的映射。
     *
     * @return         	一个包含格式化键值对的映射。
     */
    private Map<String, String> formatMap() {
        Map<String, String> configMap = getConfigMap();
        if (configMap == null) return new HashMap<>();
        return configMap;
    }

    public abstract Map<String, String> getConfigMap();
}
