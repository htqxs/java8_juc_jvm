package com.atguigu.sh.juc;

import java.util.concurrent.TimeUnit;

/**
 * 多线程8锁
 *
 * 1. 标准访问，请问先打印邮件还是短信？  邮件
 * 2. 邮件方法暂停4秒钟，请问先打印邮件还是短信？  邮件
 * 3. 新增一个普通方法hello()，先打印邮件还是hello   hello
 * 4. 两部手机，请问先打印邮件还是短信？  短信
 * 5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信？  邮件
 * 6. 两个静态同步方法，两部部手机，请问先打印邮件还是短信？  邮件
 * 7. 一个普通同步方法，一个静态同步方法，一部手机，请问先打印邮件还是短信？  短信
 * 8. 一个普通同步方法，一个静态同步方法，两部手机，请问先打印邮件还是短信？  短信
 *
 * 笔记：
 *      一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *      其他的线程只能等待。换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法，
 *      锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
 *
 *      几个普通方法，发现和同步锁无关，普通方法并没有对同步方法进行资源的抢夺。
 *
 *      换成两个对象后，就不是同一把锁了。
 *
 *      都换成静态同步方法后，情况发生变化
 *      所有的普通同步方法用的都是同一把锁---锁的是实例对象本身
 *
 *      synchronized实现同步的基础：java中的每一个对象都可以作为锁
 *      具体表现为以下三种形式：
 *          1. 对于普通同步方法，锁是当前实例对象。
 *          2. 对于静态同步方法，锁是当前类的Class对象。
 *          3. 对于同步方法块，锁是synchronized括号里配置的对象。
 *
 *      当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 *
 *      也就是说如果一个实例对象的普通方法获取锁后，该实例对象的其他普通静态方法必须等待获取锁的方法释放锁后才能获取锁，
 *      可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，
 *      所以无须等待该实例对象已获取锁的普通同步方法释放锁就可以获取他们自己的锁。
 *
 *      所有的静态同步方法用的也是同一把锁---类对象本身，
 *      静态同步方法与普通同步方法之间是不会有竞争条件的，因为这两把锁是两个不同的对象。
 *      但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 *      不管是同一个实例对象的静态同步方法之间，还是不同的实例的对象的静态同步方法之间，
 *      因为它们是同一个类的实例对象。
 *
 */
public class Lock8 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "A").start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            // phone.sendEmail();
            // phone.hello();
            phone2.sendSMS();
        }, "B").start();
    }
}

class Phone {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail...");
    }

    public synchronized void sendSMS() {
        System.out.println("sendSMS...");
    }

    public void hello() {
        System.out.println("hello...");
    }
}
