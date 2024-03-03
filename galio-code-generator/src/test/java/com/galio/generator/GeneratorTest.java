package com.galio.generator;

import com.galio.generator.init.VelocityInitializer;
import com.galio.generator.service.GeneratorService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;

/**
 * @Author: galio
 * @Date: 2024-01-21 18:25:16
 * @Description: 生成任务测试
 */
@Log4j2
@SpringBootTest
public class GeneratorTest {

    @Resource
    private GeneratorService generatorService;

    @Test
    public void test() {
        generatorService.generalCode();
        System.exit(0);
    }

    @Test
    public void execute1() {
        //设置资源路径
//        Properties p = new Properties();
//        p.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
//        p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //初始化
        // 初始化默认加载路径 classpath目录下的vm文件
//            p.setProperty("resource.loader.default.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 定义字符集
//            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
//        Velocity.init(p);
        VelocityInitializer.initVelocity();
        //载入模板
        Template t = Velocity.getTemplate("vm/java/App.java.vm");

        //定义替换规则
        VelocityContext context = new VelocityContext();
        context.put("package","org.developer.velocity.api");
        context.put("className","velocityDemo");
        context.put("Object","Value");

        //存储合并后的结果
        StringWriter sw = new StringWriter();
        t.merge(context,sw);
        String r = sw.toString();

        log.info("##r:   \n" +r);
    }
}
