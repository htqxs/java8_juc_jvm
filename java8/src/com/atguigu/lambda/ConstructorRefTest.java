package com.atguigu.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表、返回值与构造器的一致。
 *
 * 二、数组引用
 *      可以把数组看成是一个类，则写方法与构造器引用类似。
 */
public class ConstructorRefTest {

    // 构造器引用
    // Supplier中的T get()
    // Employee的空参构造器：Employee()
    @Test
    public void test1() {
        Supplier<Employee> s1 = () -> new Employee();
        System.out.println(s1.get());
        System.out.println("****************************");

        Supplier<Employee> s2 = Employee::new;
        System.out.println(s2.get());
    }

    // Function中的R apply(T t)
    // Employee中的一个参数的构造器 Employee (Integer id)
    @Test
    public void test2() {
        Function<Integer, Employee> func1 = id -> new Employee(id);
        System.out.println(func1.apply(1001));
        System.out.println("**************************************");

        Function<Integer, Employee> func2 = Employee :: new;
        System.out.println(func2.apply(1002));
    }

    // 数组引用
    // Function中的R apply(T t)
    @Test
    public void test3() {
        Function<Integer, String[]> func1 = len -> new String[len];
        System.out.println(Arrays.toString(func1.apply(5)));
        System.out.println("***********************************");

        Function<Integer, String[]> func2 = String[] :: new;
        System.out.println(Arrays.toString(func2.apply(6)));
    }
}
