package com.atguigu.sh.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue：阻塞队列
 *      阻塞：必须要阻塞/不得不阻塞
 *
 *      当队列是空的，从队列中获取元素的操作将会被阻塞
 *      当队列是满的，从队列中添加元素的操作将会被阻塞
 *
 *      试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入元素
 *
 *      试图向已满的队列中添加新元素的线程将会被阻塞，直到其他线程从队列中移除一个或多个或者完全清空，使队列变得空闲起来，并后续新增
 *
 *  阻塞队列的用处：
 *         在多线程领域，所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的x线程又会自动被唤醒。
 *
 *  为什么需要BlockingQueue？
 *         我们不需要关系什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切都交给了BlockingQueue处理。
 *         在concurrent包发布以前，在多线程环境下，需要每个程序员控制这些细节，尤其还要兼顾效率和线程安全，这会给程序带来不小的复杂度。
 *
 *
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
       /* System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // System.out.println(blockingQueue.add("d")); // 抛出异常IllegalStateException:Queue full

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // System.out.println(blockingQueue.remove());
        // System.out.println(blockingQueue.remove()); // 抛出异常NoSuchElementException

        System.out.println(blockingQueue.element()); // 抛出异常NoSuchElementException
        */

        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // System.out.println(blockingQueue.offer("d")); // false
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // System.out.println(blockingQueue.poll()); // null
        // System.out.println(blockingQueue.peek()); // null*/

     /*   blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // blockingQueue.put("c"); // 队列满时，会阻塞
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        // blockingQueue.take(); // 队列空时，会阻塞*/

        blockingQueue.offer("a");
        blockingQueue.offer("a");
        blockingQueue.offer("a");
        // blockingQueue.offer("a", 3L, TimeUnit.SECONDS); // 阻塞3秒后，自动退出
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // blockingQueue.poll(3L, TimeUnit.SECONDS); // 阻塞3秒后，自动退出
    }
}


/*
    种类分析：

    ArrayBlockingQueue: 由数组结构组成的有界阻塞队列。
    LinkedBlockingQueue: 由链表结构组成的有界（大小默认为Integer.MAX_VALUE）阻塞队列。
    PriorityBlockingQueue: 支持优先级排序的无界阻塞队列。
    DelayQueue: 使用优先级队列实现的延迟无界阻塞队列。
    SynchronousQueue: 不存储元素的阻塞队列，也即单个元素的阻塞队列。
    LinkedTransferQueue: 由链表组成的无界阻塞队列。
    LinkedBlockingDeque: 由链表组成的双向阻塞队列。


    BlockingQueue的核心方法：

        方法类型    抛出异常    特殊值     阻塞      超时
          插入      add(e)    offer(e)   put(e)    offer(e,time,unit)
          移除     remove()   poll()     take()    poll(time,unit)
          检查    element()   peek()     不可用     不可用

     抛出异常：
                当阻塞队列满时，再往队列里add(e)插入元素会抛出IllegalStateException:Queue full
                当阻塞队列空时，再往队列里remove()移除元素会抛出NoSuchElementException
      特殊值：
                插入方法，成功返回true，失败false
                移除方法，成功返回出队列的元素，队列没有就返回null

      超时退出：
                当阻塞队列满时，队列会阻塞生产者一段时间，超过限时后生产者线程会退出
 */


