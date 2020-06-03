package com.atguigu.sh.juc;


/*
   线程池的优势：
        线程池做的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，
    如果线程数量超过了最大数量，超出数量的线程排队等候，等待其他线程执行完毕，再从队列中取出任务来执行。

    主要特点：线程复用、控制最大并发数、管理线程

    1、降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
    2、提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行。
    3、提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，
       使用线程池可以进行统一的分配，调优和监控。

   线程池的七大参数：
        1、corePoolSize: 线程池中的常驻核心线程数
        2、maximumPoolSize: 线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
        3、keepAliveTime: 多余的空闲线程的存活时间。当线程池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，
                          多余线程会被销毁直到剩下corePoolSize个线程为止
        4、unit: keepAliveTime的单位
        5、workQueue: 任务队列，被提交但尚未被执行的任务
        6、threadFactory: 表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
        7、handler: 拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）时，如何拒绝请求执行的runnable策略

    线程池底层工作原理：
        1、在创建了线程池后，开始等待请求；
        2、当调用execute()方法添加一个请求任务时，线程池会做出如下判断；
           2.1、如果正在运行的线程数小于corePoolSize，那么马上创建线程运行这任务；
           2.2、如果正在运行的线程数大于或等于corePoolSize，那么将这个任务放入任务队列；
           2.3、如果这个时候队列满了，且正在运行的线程数量小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
           2.4、如果队列满了，且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行；
        3、当一个线程完成任务时，它会从队列中取下一个任务来执行；
        4、当一个线程无事可做超过一定时间（keepAliveTime）时，线程会判断：
                如果当前运行的线程数大于corePoolSize，那么这个线程就会被停掉；
                所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小；

    四种拒绝策略：
        1、AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行；
        2、CallerRunPolicy："调用者运行"一种调节机制，该策略既不会抛弃任务，也不会抛出异常，
                            而是将某些任务回退给调用者，从而降低新任务的流量；
        3、DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中，尝试再次提交当前任务；
        4、DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常；如果允许任务丢失，这是最好的一种策略。

*/

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 获取cpu内核数，（内核数加1或2）用来作为maximumPoolSize
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 1; i <= 9; i++) {
                // TimeUnit.MILLISECONDS.sleep(200);
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }

    public static void initThreadPool() {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池5个工作线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池一个工作线程
        // 一池N个工作线程，线程池根据需要创建新线程，但在之前创建的线程可用时将重用它们。可扩容
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 10; i++) {
                TimeUnit.MILLISECONDS.sleep(200);
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

