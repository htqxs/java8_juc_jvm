package com.atguigu.inteface;

public interface CompareA {
    /**
     * 接口中的静态方法
     */
    static void method1() {
        System.out.println("CompareA...method1");
    }

    /**
     * 接口中的默认方法
     */
    default void method2() {
        System.out.println("CompareA...method2");
    }

    default void method3 (){
        System.out.println("CompareA...method3");
    }
}
