package com.atguigu.stream;

import com.atguigu.lambda.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1001, "Tom", 23, 2314.0));
        employees.add(new Employee(1002, "Jack", 34, 4245.0));
        employees.add(new Employee(1003, "Mary", 35, 5880.0));
        employees.add(new Employee(1004, "Frank", 36, 6000.0));
        return employees;
    }
}
