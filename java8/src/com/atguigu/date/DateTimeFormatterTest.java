package com.atguigu.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * 格式化或解析日期、时间
 * 类似于SimpleDateFormatter
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        // 方式一：预定义的标准格式；DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化：日期 ---> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime); // 2020-05-22T10:13:36.145
        System.out.println(str1); // 2020-05-22T10:13:36.145
        // 解析：字符串 ---> 日期
        TemporalAccessor parse = formatter.parse("2020-05-22T10:13:36.145");
        System.out.println(parse); // {},ISO resolved to 2020-05-22T10:13:36.145

        // 方式二：本地化相关的格式。如：
        // DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG【MEDIUM、SHORT】);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        // 格式化
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2); // 2020年5月22日 上午10时23分26秒

        // DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(formatter2.format(localDateTime)); // 2020年5月22日 星期五
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println(formatter3.format(localDateTime)); // 2020-5-22

        // 方式三：自定义的格式。如：
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss");
        // 格式化
        System.out.println(dateTimeFormatter.format(localDateTime)); // 2020-05-22 25:19
        // 解析
        // {SecondOfMinute=19, MicroOfSecond=0, NanoOfSecond=0, MinuteOfHour=25, MilliOfSecond=0},ISO resolved to 2020-05-22
        System.out.println(dateTimeFormatter.parse("2020-05-22 25:19"));
    }
}
