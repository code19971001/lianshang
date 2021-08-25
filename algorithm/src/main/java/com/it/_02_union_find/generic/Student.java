package com.it._02_union_find.generic;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : code1997
 * @date : 2021/4/7 21:40
 */
public class Student implements Serializable {

    private int age;
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
