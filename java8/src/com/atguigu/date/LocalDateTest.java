package com.atguigu.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 测试 LocalDate、LocalTime、LocalDateTime的使用
 * LocalDateTime的使用频率较高
 */
public class LocalDateTest {
    public static void main(String[] args) {
        // now(); 获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        // 输出的只有日期：2020-05-22
        System.out.println(localDate);
        // 输出只有时间：09:24:02.380
        System.out.println(localTime);
        // 输出带有日期和时间：2020-05-22T09:24:02.380
        System.out.println(localDateTime);

        // of(); 设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2017, 6, 12, 16, 23, 48);
        // 2017-06-12T16:23:48
        System.out.println(localDateTime1);

        // getXXX();
        System.out.println(localDateTime.getDayOfMonth()); // 22
        System.out.println(localDateTime.getDayOfYear()); // 143
        System.out.println(localDateTime.getMonth()); // MAY
        System.out.println(localDateTime.getMonthValue()); // 5
        System.out.println(localDateTime.getHour()); // 9

        // withXXX()：设置相关的属性，体现了不可变性，类似于String
        // 将年份设置为2013年
        // 原localDateTime并没有改变，而是返回了一个新的localDateTime对象
        LocalDateTime localDateTime2 = localDateTime.withYear(2013);
        System.out.println(localDateTime2);
        System.out.println(localDateTime);

        // plusXXX(); 加法
        LocalDateTime localDateTime3 = localDateTime.plusYears(3);
        System.out.println(localDateTime3);
        System.out.println(localDateTime);

        // minusXXX(); 减法
        LocalDateTime localDateTime4 = localDateTime.minusYears(3);
        System.out.println(localDateTime4);
        System.out.println(localDateTime);
    }
}
