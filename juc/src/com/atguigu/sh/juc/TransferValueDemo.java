package com.atguigu.sh.juc;

public class TransferValueDemo {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("XXX");
    }

    public void changeValue3(String str) {
        str = "XXX";
    }

    public static void main(String[] args) {
        TransferValueDemo demo = new TransferValueDemo();
        int age = 20;
        demo.changeValue1(age);
        System.out.println("age = " + age);

        Person person = new Person();
        person.setName("abc");
        demo.changeValue2(person);
        System.out.println("personName = " + person.getName());

        String str = "abc";
        demo.changeValue3(str);
        System.out.println("String str = " + str);
    }
}

class Person {
    private Integer id;
    private String name;

    public Person() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

