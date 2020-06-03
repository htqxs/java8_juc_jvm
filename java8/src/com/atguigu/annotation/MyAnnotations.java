package com.atguigu.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME) // 注解的声明周期
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.TYPE_USE}) // 注解的作用范围
public @interface MyAnnotations {
    MyAnnotation[] value();
}
