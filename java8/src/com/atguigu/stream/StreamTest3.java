package com.atguigu.stream;

import com.atguigu.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 */
public class StreamTest3 {

    // 匹配与查找
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        // allMatch(Predicate p)：检查是否匹配所有元素
        // 是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        // anyMatch(Predicate p)：检查是否至少匹配一个元素
        // 是否存在员工的工资大于5000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 5000);
        System.out.println(anyMatch);

        // noneMatch(Predicate p)：检查是否没有匹配的元素
        // 是否不存在员工姓名以 J 开头
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("J"));
        System.out.println(noneMatch);

        // findFirst()：返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        // findAny()：返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);

        // count()：返回流中元素的总个数
        System.out.println(employees.stream().count());

        // max(Comparator c)：返回流中的最大值
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        Optional<Double> max = salaryStream.max(Double::compare);
        System.out.println(max);

        // min(Comparator c)：返回流中最小值
        // 返回最低工资的员工
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);

        // forEach(Consumer c)：内部迭代
        employees.stream().forEach(System.out::println);
    }

    // 归约
    @Test
    public void test2() {
        // reduce(T identity, BinaryOperator bo)：可以将流中的元素反复结合起来，得到一个值。返回T
        // 计算1-10的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8 , 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // reduce(BinaryOperator)：可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        // 计算所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        Optional<Double> optional = salaryStream.reduce(Double::sum);
        System.out.println(optional);
    }

    // 收集
    @Test
    public void test3() {
        // collect(Collector c)：将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
        // 查找工资大于6000的员工，结果返回为一个List或set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 4000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        System.out.println();

        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 4000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
