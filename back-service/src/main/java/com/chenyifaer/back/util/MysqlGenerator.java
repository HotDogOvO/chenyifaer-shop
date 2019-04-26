package com.chenyifaer.back.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatisplus自動生成工具
 * @Author:wudh
 * @Date: 2019/4/6 16:55
 */
public class MysqlGenerator {
    public static void main(String[] args) {
        int result = scanner();

        //需要生成的表
        String[] tableList = {"t_shop_return_orders"};
        final String projectPath = "E:\\JAVA\\Idea\\chenyifaer-shop\\back-service";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        //输出目录
                        .setOutputDir(projectPath + "/src/main/java")
                        // 是否覆盖文件
                        .setFileOverride(true)
                        // 开启 activeRecord 模式
                        .setActiveRecord(true)
                        // XML 二级缓存
                        .setEnableCache(false)
                        // XML ResultMap
                        .setBaseResultMap(true)
                        // XML columList
                        .setBaseColumnList(true)
                        //作者
                        .setAuthor("wudh")
                        //是否打开输出目录
                        .setOpen(false)
                        //swagger支持
                        .setSwagger2(true)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%sPO")
                .setServiceName("%sService")
                .setMapperName("%sDao")
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        // 数据库类型
                        .setDbType(DbType.MYSQL)
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                System.out.println("转换类型：" + fieldType);
//                                if ( fieldType.toLowerCase().contains( "date" ) ) {
//                                    System.out.println(fieldType+"转为:"+ DbColumnType.DATE.getType());
//                                    return DbColumnType.DATE;
//                                }
                                return super.processTypeConvert(globalConfig, fieldType);
                            }
                        })
                        .setDriverName("com.mysql.jdbc.Driver")
                        .setUsername("chenyifaer")
                        .setPassword("cyfe67373")
                        .setUrl("jdbc:mysql://47.101.58.198:3306/chenyifaer_shop")
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // 此处可以修改为您的表前缀
                        .setTablePrefix(new String[]{"t_"})
                        // 表名生成策略
                        .setNaming(NamingStrategy.underline_to_camel)
                        // 需要生成的表
                        .setInclude(tableList)
                        .setEntityBuilderModel(true)
                        // 是否为lombok模型（默认 false）
                        .setEntityLombokModel(true)
                        .setRestControllerStyle(true)
                        .setControllerMappingHyphenStyle(false)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        // 自定义包路径
                        .setParent("com.chenyifaer.back")
                        .setEntity("entity.po")
                        .setMapper("dao")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setController("controller")
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml" + ((1 == result) ? ".ftl" : ".vm")) {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/mapper/" +
                            tableInfo.getEntityName().substring(0,tableInfo.getEntityName().length()-2) + "Mapper" + StringPool.DOT_XML;
                }
            }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
        );
        // 执行生成
        if (1 == result) {
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        }
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static int scanner() {
        return 1;
    }
}
