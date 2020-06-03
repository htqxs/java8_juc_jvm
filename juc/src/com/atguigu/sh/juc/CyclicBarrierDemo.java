package com.atguigu.sh.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier的使用
 *
 *      从字面上的意思可以知道，这个类的中文意思是“循环栅栏”。大概的意思就是一个可循环利用的屏障。
 *      它的作用就是会让所有线程都等待完成后才会继续下一步行动。
 *
 * 示例：凑齐了七颗龙珠才会召唤神龙。即七个线程等待完成后，才会执行召唤神龙（执行召唤神龙的线程是最后一个等待完成的线程）
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println(Thread.currentThread().getName() + "\t召唤神龙...."));
        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第：" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
