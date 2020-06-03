package com.atguigu.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用：
 *  1. 使用情境：当要传递给lambda体的操作，已经有实现的方法，可以使用方法引用。
 *
 *  2. 方法引用，本质上是lambda表达式，所以也是函数式接口的实例。
 *
 *  3. 使用要求：
 *      情况一：对象 :: 实例方法
 *      情况二：类 :: 静态方法
 *      情况三：类 :: 实例方法
 *
 *      要求接口中的抽象方法的形参列表和返回值与方法引用中的方法保持一致(对于情况一和二)
 *      对于情况下三，形参列表可以不一致，但抽象方法的第一个形参为实例方法的调用者。
 */
public class MethodRefTest {

    // 情况一：对象 :: 实例方法
    // Consumer中的void accept(T t) 方法
    // PrintStream中的void println(T t)方法
    @Test
    public void test1() {
        // lambda表达式写法
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("hello");
        System.out.println("**********************");

        // 方法引用写法
        PrintStream printStream = System.out;
        Consumer<String> con2 = printStream :: println;
        con2.accept("world");
    }

    // Supplier中的 T get()方法
    // Employee中的 getName()方法
    @Test
    public void test2() {
        Employee employee = new Employee(1001, "Tom", 23, 6000d);
        Supplier<String> s1 = () -> employee.getName();
        System.out.println(s1.get());
        System.out.println("**********************");

        Supplier<String> s2 = employee :: getName;
        System.out.println(s2.get());
    }

    // 情况二：类 :: 静态方法
    // Comparator中的int compare(T t1, T t2)
    // Integer中的int compare(T t1, T t2)
    @Test
    public void test3() {
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(32, 13));
        System.out.println("**********************");

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12, 32));
    }

    // Function中的R apply(T t)
    // Math中的Long round(Double d)
    @Test
    public void test4() {
        Function<Double, Long> f1 = aDouble -> Math.round(aDouble);
        System.out.println(f1.apply(3.6));
        System.out.println("**********************");

        Function<Double, Long> f2 = Math :: round;
        System.out.println(f2.apply(3.4));
    }

    // 情况三：类 :: 实例方法
    // Comparator中的int compare(T t1, T t2)
    // String中的int t1.compareTo(T t2)
    @Test
    public void test5() {
        Comparator<String> com1 = (t1, t2) -> t1.compareTo(t2);
        System.out.println(com1.compare("abc", "abd"));
        System.out.println("**********************");

        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("abc", "abb"));
    }

    // Function中的R apply(T t);
    // Employee中的String getName();
    @Test
    public void test6() {
        Function<Employee, String> f1 = e -> e.getName();
        Employee employee = new Employee(1002, "Jack", 34, 6700.0);
        System.out.println(f1.apply(employee));
        System.out.println("**********************");

        Function<Employee, String> f2 = Employee::getName;
        System.out.println(f2.apply(employee));
    }
}
