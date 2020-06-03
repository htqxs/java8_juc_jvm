package com.atguigu.sh.juc;


import java.util.concurrent.CountDownLatch;

/**
 * 测试CountDownLatch的使用
 *
 * 原理：
 *      CountDownLatch主要有两个方法，当一个或者多个线程调用await()方法时，这些线程会阻塞。
 *      其他线程调用countDown()方法会将计数器减1（调用countDown()方法的线程不会阻塞）。
 *      当计数器为0时，因await()方法阻塞的线程会被唤醒，继续执行。
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长离开教室");
    }
}
