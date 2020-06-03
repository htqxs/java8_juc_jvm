package com.atguigu.stream;

import com.atguigu.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试 Stream 的中间操作
 */
public class StreamTest2 {

    // 1、筛选与切片
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();
        // filter(Predicate p)：接收 lambda，从流中排除某些元素
        Stream<Employee> stream = list.stream();
        // 查询员工中工资大于4000的员工信息
      /*  stream.filter(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 4000;
            }
        }).forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println(employee;
            }
        });*/
        stream.filter(e -> e.getSalary() > 4000).forEach(System.out::println);
        System.out.println();

        // limit(n)：截断流，使其元素不超过给定数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // skip(n)：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。
        list.stream().skip(2).forEach(System.out::println);
        System.out.println();

        // distinct()：筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        Employee e = new Employee(10011, "Hello", 55, 8000.0);
        list.add(e);
        list.add(e);
        list.add(e);
        list.add(e);
        list.add(e);

        list.stream().distinct().forEach(System.out::println);
    }

    // 映射
    @Test
    public void test2() {
        // map(Function f)：接收一个函数作为参数，将元素转换为其他形式或提取信息，
        // 该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc");
        // list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        // 获取员工姓名长度大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name -> name.length() > 4).forEach(System.out::println);

        // flatMap(Function f)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        // 类似于 list.addAll()，而map()类型于list.add();

        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest2::fromStringToStream);
        streamStream.forEach(s -> s.forEach(System.out::println));

        // flatMap 把所有的流连接成了一个流
        list.stream().flatMap(StreamTest2::fromStringToStream).forEach(System.out::println);
    }

    // 将字符串中的多个字符构成的集合转换为对应的Stream
    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


    @Test
    public void test3() {
        List<Object> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Object> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        list1.add(list2);
        System.out.println(list1);

        // list1.addAll(list2);
        // System.out.println(list1);
    }

    // 排序
    @Test
    public void test4() {
        // sorted()：自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0);
        list.stream().sorted().forEach(System.out::println);

        // 报错，因为Employee没有实现Comparable接口
        // List<Employee> employees = EmployeeData.getEmployees();
        // employees.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com)：定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) -> {
            if (!e1.getAge().equals(e2.getAge())) {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
            return Double.compare(e1.getSalary(), e2.getSalary());
        }).forEach(System.out::println);
    }
}
