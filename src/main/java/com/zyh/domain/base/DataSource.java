package com.zyh.domain.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，处理切换数据源
 *
 * @author 1101399
 * @CreateDate 2018-6-19 下午4:06:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    /**
     * 注入映射注解：使用枚举类型应对配置文件数据库key键值
     */
    DataSourceType value();
    /**
     * 注入映射注解：直接键入配置文件中的key键值
     */
    String description() default "";
}
