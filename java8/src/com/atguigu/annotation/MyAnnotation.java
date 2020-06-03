package com.atguigu.annotation;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
@Inherited // 可继承性的，标准该注解的类的子类也会有该注解的信息
@Retention(RetentionPolicy.RUNTIME) // 注解的声明周期
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.TYPE_USE}) // 注解的作用范围
public @interface MyAnnotation {

    String value() default "hello";
}
