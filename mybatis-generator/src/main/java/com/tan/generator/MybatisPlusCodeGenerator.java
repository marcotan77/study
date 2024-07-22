package com.tan.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus 代码自动生成器
 *
 * @author tanchusheng
 * @date 2023/6/20
 */
public class MybatisPlusCodeGenerator {
    /**
     * 代码生成器
     *
     * @param args
     */
    public static void main(String[] args) {
        //====================配置变量区域=====================//
        String author = "com.xxx";
        // 生成的entity、controller、service等包所在的公共上一级包路径全限定名
        String rootPackage = "";
        // 模块名
        String moduleName = "sys";

        // 数据库配置
        String mysqlIp = "127.0.0.1";
        int mysqlPort = 3306;
        String mysqlDataBase = "big_market_01";
        String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&characterEncoding=utf8", mysqlIp, mysqlPort, mysqlDataBase);
        String username = "root";
        String password = "root";
        /**表名，多个使用,分隔**/
        //String tableNames = "base_area,base_driver,base_item_details,base_items,base_login_log,base_modify_record,base_module,base_operation_log,base_organize,base_organize_chain,base_organize_chain_user,base_parameter,base_permission,base_permission_scope,base_redis_source,base_role,base_service_license,base_sub_system,base_user,base_user_contact,base_user_log_on,base_user_organize,base_user_privacy,base_user_role,base_wechat_user_oauth,base_worker_node";
        String tableNames = "user_behavior_rebate_order_000";

        // 去掉的前缀
        String[] tablePrefix = new String[]{""};//base_
        // 去掉的后缀
        String[] tableSuffix = new String[]{"_000"};//_000

        // 输出路径 + 定制部分pkg
        String projectPath = System.getProperty("user.dir") + "/mybatis-generator/code/" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + "/";
        String javaPath = projectPath + "/src/main/java";
        String modulePath = javaPath + "/" + rootPackage.replace(".", "/") + "/" + moduleName + "/";
        String xmlPath = javaPath + "/resources/master";
        //  service

        String controllerPath = modulePath + "controller";
        String servicePath = modulePath + "service";
        String serviceImplPath = servicePath + "/impl";
        String entityPath = modulePath + "model";
        String mapperPath = modulePath + "mapper";
//        // 适配层 adapter
//        String adapter = "web";
//        String adapterPath = modulePath + "adapter/" + adapter;
//        String infrastructure = "gatewayimpl";
//        String infrastructurePath = modulePath + "infrastructure/" + infrastructure;
//        // domain
//        String domain = "gateway";
//        String domainPath = modulePath + "domain/" + domain;
//        // 接口层  client dto
//        String dto = "dto";
//        String dtoPath = modulePath + "client/" + dto;
//        // 基础设施层 infrastructure
//        String mapper = "dao";
//        String mapperPath = modulePath + "dbsdk/master/" + mapper;
//        String entity = "model";
//        String entityPath = modulePath + "dbsdk/master/" + entity;

        //====================生成代码=====================//
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .disableOpenDir()
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(javaPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    // 定制路径
                    HashMap<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.controller, controllerPath);
                    pathInfo.put(OutputFile.service, servicePath);
                    pathInfo.put(OutputFile.serviceImpl, serviceImplPath);

//                    pathInfo.put(OutputFile.other, dtoPath);
                    pathInfo.put(OutputFile.entity, entityPath);
                    pathInfo.put(OutputFile.mapper, mapperPath);
                    pathInfo.put(OutputFile.xml, xmlPath);

                    // 定制包名
                    builder.parent(rootPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .controller("controller")
                            .serviceImpl("impl")
                            .service("service")
                            .mapper("mapper")
                            .entity("entity")
//                            .other(dto)
                            .pathInfo(pathInfo); // 设置mapperXml生成路径

                })
                .strategyConfig(builder -> {

                    builder.addInclude(tableNames.split(",")) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix)// 设置过滤表前缀
                            .addTableSuffix(tableSuffix)
                    ;

                    builder.entityBuilder()
//                            .superClass(BaseEntity.class)
                            .disableSerialVersionUID()
                            .enableChainModel()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .enableActiveRecord()
                            // 乐观锁
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addSuperEntityColumns("id", "create_on", "modified_on")
                            // .addIgnoreColumns("age")
                            .idType(IdType.AUTO)
                            .formatFileName("%sDO");

                    builder.controllerBuilder()
                            //.superClass(BaseController.class)
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .build();

                    builder.serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl");

                    builder.mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");

                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entityKt("/templates/dto.java")
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java");
                }).injectionConfig(builder -> {
                    // 自定义模板
                    Map<String, String> diyTemplates = new HashMap<>();
                    diyTemplates.put("DTO.java", "/templates/dto.java.vm");
                    diyTemplates.put("Query.java", "/templates/query.java.vm");
                    diyTemplates.put("Convertor.java", "/templates/convertor.java.vm");


                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                                System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                                // 过滤表前缀
                                String table_name = tableInfo.getName();
                                if (tablePrefix.length > 0) {
                                    for (String s : tablePrefix) {
                                        table_name = StrUtil.removePrefixIgnoreCase(table_name, s);
                                    }
                                }
                                // 自定义变量
                                // 类名 大驼峰
                                String upperClassName = StrUtil.upperFirst(StrUtil.toCamelCase(table_name));
                                objectMap.put("upperClassName", upperClassName);
                                // 对象名 小驼峰
                                objectMap.put("lowerClassName", StrUtil.toCamelCase(table_name));
                            })
                            .customFile(diyTemplates)
                            .build();
                })
                .templateEngine(new TemplateEngineExt())
                .execute();
    }
}
