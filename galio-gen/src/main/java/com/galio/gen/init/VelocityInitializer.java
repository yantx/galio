package com.galio.gen.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * @Author: galio
 * @Date: 2024-01-28 20:16:43
 * @Description: 初始化Velocity模板引擎
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityInitializer {

    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 设置资源加载器为ClasspathResourceLoader，并指定加载路径为default
            p.setProperty("resource.loader.default.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // p.setProperty("resource.loader.default.path", "default");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
