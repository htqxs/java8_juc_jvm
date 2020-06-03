package com.atguigu.lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * Lambda表达式的使用举例
 */
public class LambdaTest {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r1.run();
        System.out.println("***********************************");

        Runnable r2 = () -> System.out.println("Hello Java!");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator1.compare(12, 31));
        System.out.println("*************************************");

        // Lambda表达式
        Comparator<Integer> comparator2 = (o1, o2) ->  Integer.compare(o1, o2);
        System.out.println(comparator2.compare(35, 31));
        System.out.println("*************************************");

        // 方法引用
        Comparator<Integer> comparator3 = Integer::compare;
        System.out.println(comparator3.compare(12, 31));
        System.out.println("*************************************");
    }
}
