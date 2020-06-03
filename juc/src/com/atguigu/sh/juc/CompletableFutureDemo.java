package com.atguigu.sh.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试CompletableFuture
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t没有返回值 update mysql ok");
        });
        System.out.println(voidCompletableFuture.get()); // null*/

       // 异步回调
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "有返回值 insert mysql");
            int i = 10 / 0;
            return 1;
        });
        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("t = " + t);
            System.out.println("u = " + u);
        }).exceptionally(f -> {
            // 如果出现异常会执行，也会有返回值
            System.out.println("exception：" + f.getMessage());
            return -1;
        }).get());
    }
}
