package com.atguigu.sh.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量
 *  实现一个线程对该变量加1，一个线程对该变量减1，
 *  实现交替，来10轮，变量初始值为零
 *
 *  1. 高内聚低耦合前提下，线程操作资源类
 *  2. 判断/操作/通知
 *  3. 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <  10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <  10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i <  10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


class AirConditioner {
    private Integer number = 0;

    private Lock lock = new ReentrantLock();
    // 用condition中的await()方法和signal()方法来代替Object中的wait()方法和notify()方法
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知
            condition.signalAll();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知
            condition.signalAll();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }


   /* public synchronized void increment() throws InterruptedException {
        // 判断需要使用while，不能使用if，否则就会出现虚假唤醒的情况，导致错误
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }*/
}