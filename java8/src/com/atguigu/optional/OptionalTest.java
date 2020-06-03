package com.atguigu.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * 测试Optional类的使用
 * Optional类是为了避免出现空指针异常
 */
public class OptionalTest {
    /*
        Optional.of(T t)：创建一个Optional实例，t必须非空
        Optional.empty()：创建一个空的Optional实例
        Optional.ofNullable(T t)：t可以为null
     */

    @Test
    public void test1() {
        Boy boy = new Boy();
        boy = null;

        // 会出现空指针异常
        // Optional.of(boy);

        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        System.out.println(optionalBoy);
    }

    @Test
    public void test2() {
        Boy boy = new Boy("张智");
        // boy = null;
        System.out.println(getBoyName3(boy));
    }


    public String getBoyName1(Boy boy) {
        // 可能会出现空指针异常
        return boy.getName();
    }

    public String getBoyName2(Boy boy) {
        // 可能会出现空指针异常
        if (boy != null) {
            return boy.getName();
        }
        return null;
    }

    // 使用Optional类
    public String getBoyName3(Boy boy) {
        // 可能会出现空指针异常
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        // orElse(T other)：如果optional对象中的值为空，就使用other对象代替，否则仍使用原对象
        Boy newBoy = optionalBoy.orElse(new Boy("zz"));
        return newBoy.getName();
    }
}
