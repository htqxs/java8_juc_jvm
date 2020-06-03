package com.atguigu.sh.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程中，第三中获得多线程的方式：实现 Callable接口
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        // 同一个futureTask只会调用一次call()方法
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        System.out.println(Thread.currentThread().getName() + "主线程计算完毕");

        // 一般放在最后，防止主线程堵塞
        System.out.println(futureTask.get());

    }
}


class MyThread implements Callable<Integer> {

    /**
     * 与继承Thread和实现Runnable接口的不同点
     *
     * 1. 方法名不同，前者为run()方法
     * 2. 前者run()方法没有抛出异常，此方法抛出Exception
     * 3. 此方法有返回值，返回值的类型和泛型保持一致
     *
     */
    @Override
    public Integer call() throws Exception {
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + "...come in here...");
        return 1024;
    }
}