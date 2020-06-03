package com.atguigu.sh.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试ReadWriteLock的使用（读写锁）
 *
 *      多个线程同时读取一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 *      但是如果有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或写。
 * 小总结：
 *       读-读能共存
 *       读-写不能共存
 *       写-写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入数据" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}