package com.it._06_tree.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : code1997
 * @date :2021-03-2021/3/16 15:54
 */
@Data
@AllArgsConstructor
public class Person implements Comparable {
    private Integer age;

    @Override
    public int compareTo(Object o) {
        return this.age.compareTo(((Person) o).age);
    }

}
