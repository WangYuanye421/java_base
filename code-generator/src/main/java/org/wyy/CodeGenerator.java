package org.wyy;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Wyy
 **/
@Component
public class CodeGenerator {
    /**
     * 当前项目名称
     */
    public static   String moduleName = "code-generator";
    public static  String dbUrl = "jdbc:mysql://localhost:3306/tech_interview?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
    public static  String dbDriver = "com.mysql.cj.jdbc.Driver";
    public static  String dbUsername = "root";
    public static  String dbPassword = "root123";

    // 多个用,  拼接
    public static  final String tableNames= "sys_user,sys_role,sys_user_role,sys_auth,sys_role_auth";
    //public static  final String tableNames= "biz_payment";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");  //获取当前项目的 文件夹地址

        if(!projectPath.endsWith(moduleName)){  //解决IDEA中 项目目录问题
            projectPath += File.separator + moduleName;
        }

        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("wyy");
        gc.setOpen(false);

        gc.setBaseResultMap(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setServiceImplName("%sServiceImpl");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName(dbDriver);
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);

        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                //tinyint转换成Boolean
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.BYTE;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }

        });

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.wyy.tech");
        //实体目录
        pc.setEntity("entity");
        //Mapper接口目录
        pc.setMapper("mapper");
        //xml目录
        pc.setXml("mapper.xml");
        //service目录
        pc.setService("service");
        //serviceImpl 目录
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //不生成controller
        //templateConfig.setController(null);
        mpg.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);    //no_change原样输出
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //no_change原样输出
        strategy.setEntityLombokModel(true);

        strategy.setInclude(tableNames.split(","));
        strategy.setTablePrefix("t_");

//        strategy.setEntityTableFieldAnnotationEnable(true); //自动添加 field注解

        mpg.setStrategy(strategy);
        mpg.execute();

    }
}
