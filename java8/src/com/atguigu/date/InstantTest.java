package com.atguigu.date;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * 测试 Instant 类的使用
 */
public class InstantTest {
    public static void main(String[] args) {
        // now(); 获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        // 2020-05-22T01:43:46.034Z，少了8个小时
        // 因为我们在东八区，与本初子午线差快了八小时
        System.out.println(instant);

        // 添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        // 2020-05-22T09:45:37.722+08:00，我们这的正常时间
        System.out.println(offsetDateTime);

        // toEpochMilli(); 获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数
        long milli = instant.toEpochMilli();
        // 1590112034475
        System.out.println(milli);

        // ofEpochMilli(); 通过给定的毫秒数，获取Instant实例
        Instant instant1 = Instant.ofEpochMilli(1590112034475L);
        System.out.println(instant1);
    }
}
