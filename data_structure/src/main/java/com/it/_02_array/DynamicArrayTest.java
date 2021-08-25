package com.it._02_array;

import day01.bean.Person;

public class DynamicArrayTest {
    public static void main(String[] args) throws InterruptedException {
        DynamicArray<Person> dynamicArray = new DynamicArray<>();
        dynamicArray.add(new Person(12,"张三"));
        dynamicArray.add(null);
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.add(new Person(13,"张三"));
        dynamicArray.remove(10);
        dynamicArray.remove(9);
        dynamicArray.remove(8);
        System.gc();
        Thread.sleep(10000);
        System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.indexOf(null));


    }
}
