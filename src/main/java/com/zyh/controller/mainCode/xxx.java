package com.zyh.controller.mainCode;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zyh.controller.mainCode.RolesController.RequestMethod;

/**
 * 学习注解的使用
 *
 * 添加元注解
 *
 *
 * @author      1101399
 * @CreateDate  2018-3-13 下午3:03:32
 */
/*注解用于什么地方*/
/*给方法注解*/
/*给注解注解*/
/*给包注解*/
//@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.PACKAGE})
@Target(value = {ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.PACKAGE})
@Documented /*生成说明文档，添加类的解释*/
@Inherited /*允许子类继承父类中的注解*/
@Retention(RetentionPolicy.RUNTIME)/*注解运行状态*/
public @interface xxx {

    /**
     * 自定义注解的属性
     * @return
     */
    String gvaluc() default "默认值：狗蛋";;

    /**
     * 自定义注解的属性
     * @return
     */
    RequestMethod[] method() default {};;

}
