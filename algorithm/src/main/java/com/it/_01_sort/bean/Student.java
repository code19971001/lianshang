package com.it._01_sort.bean;

/**
 * @author : code1997
 * @date : 2021/3/28 10:12
 */
public class Student implements Comparable<Student> {

    private int score;
    private int age;

    public int getScore() {
        return score;
    }

    public int getAge() {
        return age;
    }

    public Student(int score, int age) {
        this.score = score;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return getAge() - o.age;
    }
}
