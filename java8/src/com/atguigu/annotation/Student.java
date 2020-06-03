package com.atguigu.annotation;

import java.util.ArrayList;

/**
 * 可重复注解：
 *      1、在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
 *      2、MyAnnotation的Target和Retention、Inherited等元注解和MyAnnotation相同
 *
 *
 * 类型注解:
 *      ElementType.TYPE_PARAMETER：表示该注解能写在类型变量的声明语句中（如泛型声明）
 *      ElementType.TYPE_USE：表示该注解能写在使用类型的任何语句中
 */
// jdk1.8 可重复注解
@MyAnnotation(value = "student")
@MyAnnotation(value = "teacher")
// jdk1.8 之前的写法
// @MyAnnotations({@MyAnnotation(value = "student"), @MyAnnotation(value = "teacher")})
public class Student {
    private Integer id;
    private String name;

    public Student() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 类型注解的使用
 * @param <T>
 */
class Generic<@MyAnnotation T> {

    public void show() throws @MyAnnotation RuntimeException {
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num = (@MyAnnotation int)10L;
    }
}
