package com.galio.generator.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.Properties;

/**
 * @Author: galio
 * @Date: 2024-01-28 20:16:43
 * @Description: 初始化Velocity模板引擎
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityInitializer {

    public static void initVelocity() {
        Properties props = new Properties();
        try {
            // 初始化默认加载路径 指定目录下
            // props.setProperty(RuntimeConstants.RESOURCE_LOADERS, "file");
            // props.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            // props.setProperty("resource.loader.file.path", "/path/to/templates");
            // 初始化默认加载路径 classpath目录下
            props.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
            props.setProperty("resource.loader.classpath.class", ClasspathResourceLoader.class.getName());
            // props.setProperty("resource.loader.classpath.path", "/");
            // 定义字符集
            props.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            Velocity.init(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
