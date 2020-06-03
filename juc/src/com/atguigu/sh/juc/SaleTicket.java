package com.atguigu.sh.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：三个售票员    卖出   30张票
 *
 * 多线程编程的企业级套路 + 模板
 *
 * 在高内聚低耦合的前提下：线程  操作  资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.saleTicket(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.saleTicket(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.saleTicket(); }, "C").start();
    }
}


// 资源类
class Ticket {
    private Integer number = 30;
    // 可重入锁
    private Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

