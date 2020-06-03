package com.atguigu.stream;

import com.atguigu.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream 关注的是对数据的运算，与CPU打交道
 *    集合关注的是数据的存储，与内存打交道
 *
 * 2. 特点：
 *      1、Stream 自己不会存储元素
 *      2、Stream 不会改变源对象。相反，会返回一个持有结果的新的Stream
 *      3、Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 3. Stream 执行流程
 *      1、Stream 的实例化
 *      2、一系列的中间操作（过滤、映射、...）
 *      3、终止操作
 *
 * 4. 说明：
 *      1、一个中间操作链，对数据源的数据进行处理
 *      2、一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 *  测试Stream的实例化
 */
public class StreamTest {

    // 创建 Steam 方式一：通过集合
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    // 创建Stream方法二：通过数组
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};

        // 调用Arrays类的
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001);
        Employee e2 = new Employee(1002);
        Employee[] arr2 = new Employee[]{e1, e2};
        Stream<Employee> employeeStream = Arrays.stream(arr2);
    }

    // 创建 Stream 方式三：通过Stream的of()
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
    }

    // 创建 Stream 方式四：创建无限流
    @Test
    public void test4() {
        // 迭代
        // 遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
