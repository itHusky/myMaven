package com.zyh.controller.test;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 第一种方法的代码自动生成
 *
 * 代码自动生成器 了解下
 *
 * POM.XML 文件添加依赖
  <!-- Mybatis-Plus  自动生成实体类-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
            <version>2.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity</artifactId>
            <version>1.7</version>
        </dependency>
 *
 * Created by ehomeud on 2017/4/26.
 */

public class AutoGenerated{
    public static void main(String[] args) throws InterruptedException {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://");// 项目文件输出地址
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(true);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("张易鸿");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
         gc.setMapperName("%sDao");
         gc.setXmlName("%sMapper");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImap");
         gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });*/

        // 数据库登录相关配置
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://192.168.12.244:3310/test?useUnicode=true&amp;characterEncoding=UTF-8&amp;generateSimpleParameterMetadata=true");
        dsc.setUsername("root");
        dsc.setPassword("1101399");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略|生成方法
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表|指定生成
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表|忽略

        // 增加
        // 自定义实体父类
//        strategy.setSuperEntityClass("com.zyh.TestEntity");
        // 自定义实体，公共字段
//        strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
//        strategy.setSuperMapperClass("com.zyh.TestMapper");
        // 自定义 service 父类
//        strategy.setSuperServiceClass("com.zyh.TestService");
        // 自定义 service 实现类父类
//        strategy.setSuperServiceImplClass("com.zyh.TestServiceImpl");
        // 自定义 controller 父类
//        strategy.setSuperControllerClass("com.zyh.TestController");
        // 【实体】是否生成字段常量（默认 false）
//        public static final String ID = "test_id";
//        strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
//        public User setName(String name) {this.name = name; return this;}
//        strategy.setEntityBuilderModel(true);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zyh");
        pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

}