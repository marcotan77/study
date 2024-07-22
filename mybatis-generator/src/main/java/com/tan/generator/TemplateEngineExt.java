package com.tan.generator;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 模板引擎抽象类 - 扩展,支持自定义模板名称
 *
 * @author ssm
 */
public class TemplateEngineExt extends VelocityTemplateEngine {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 批量输出 java xml 文件
     */
    @Override
    public TemplateEngineExt batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = config.getTableInfoList();
            tableInfoList.forEach(tableInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
                Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
                    t.beforeOutputFile(tableInfo, objectMap);
                    // 输出自定义文件
//                    this.outputCustomFile(t.getCustomFile(), tableInfo, objectMap);
                });
                // entity
                outputEntity(tableInfo, objectMap);
                // mappers and xml
                outputMapper(tableInfo, objectMap);
                // service
                outputService(tableInfo, objectMap);
                // controller
                outputController(tableInfo, objectMap);
            });
        } catch (Exception e) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }


    /**
     * 输出自定义模板文件
     *
     * @param customFile 自定义配置模板文件信息
     * @param tableInfo  表信息
     * @param objectMap  渲染数据
     * @since 3.5.1
     */
//    @Override
//    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
//        String otherPath = getPathInfo(OutputFile.other);
//        customFile.forEach((key, value) -> {
//            String upperClassName = (String) objectMap.get("upperClassName");
//            String fileName = String.format((otherPath + File.separator + upperClassName + "%s"), key);
//            outputFile(new File(fileName), objectMap, value, getConfigBuilder().getInjectionConfig().isFileOverride());
//        });
//    }


}
