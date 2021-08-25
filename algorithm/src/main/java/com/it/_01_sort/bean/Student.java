package com.it._01_sort.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : code1997
 * @date : 2021/3/28 10:12
 */
@Data
@AllArgsConstructor
public class Student implements Comparable<Student> {

    private int score;
    private int age;

    @Override
    public int compareTo(Student o) {
        return getAge() - o.age;
    }
}
