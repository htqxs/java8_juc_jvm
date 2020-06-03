package com.atguigu.inteface;

public class TestSubClass {
    public static void main(String[] args) {
        SubClass s = new SubClass();

        // s.method1();
        // 知识点1：接口中定义的静态方法，只能通过接口调用
        CompareA.method1();

        // 知识点2：通过实现类的对象，可以调用接口的默认方法
        // 如果实现类中重写了接口的默认方法，调用时调用的为重写的方法
        s.method2();
        // 知识点3：如果子类（或实现类）继承的父类和实现的接口中声明了同名同参数的方法，
        // 那么子类在没有重写此方法的情况下，默认调用的是【直接父类】中的同名同参数方法。

        // 知识点4：如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
        // 而实现类没有重写此方法的情况下，会报错
        // 这就需要我们必须在实现类中重写此方法
        s.method3();
    }
}


class SubClass extends SuperClass implements CompareA, CompareB{

    @Override
    public void method2() {
        System.out.println("SubClass...method2");
    }

    // @Override
    // public void method3() {
    //     System.out.println("SubClass...method3");
    // }

    // 知识点5：如何在子类（或实现类中调用父类、接口中被重写的方法）
    public void myMethod() {
        method3(); // 调用自己定义的方法
        super.method3(); // 调用父类的方法
        // 调用接口中的默认方法
        CompareA.super.method2();
        CompareB.super.method3();
    }
}