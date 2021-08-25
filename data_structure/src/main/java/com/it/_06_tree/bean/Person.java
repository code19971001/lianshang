package com.it._06_tree.bean;

/**
 * @author : code1997
 * @date :2021-03-2021/3/16 15:54
 */
public class Person implements Comparable {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        return this.age.compareTo(((Person)o).age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
