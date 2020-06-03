package com.atguigu.sh.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 *  A打印五次，B打印10次，CC打印15次
 *  接着
 *  A打印五次，B打印10次，CC打印15次
 *  ...来10轮
 *
 *  1. 高内聚低耦合前提下，线程操作资源类
 *  2. 判断/操作/通知
 *  3. 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 *  4. 注意标志位的修改和定位
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

class ShareResource {
    private Integer flag = 1; // 1:A，2:B，3:C

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                condition1.await();
            }
            // 操作
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 标志位的更新
            flag = 2;
            // 通知
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                condition2.await();
            }
            // 操作
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 标志位的更新
            flag = 3;
            // 通知
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                condition3.await();
            }
            // 操作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 标志位的更新
            flag = 1;
            // 通知
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


