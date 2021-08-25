package com.it._02_union_find.generic;

import com.it.tools.Asserts;
import org.junit.Test;

/**
 * 支持自定义对象并查集实现。
 *
 * @author : code1997
 * @date : 2021/4/7 21:32
 */
public class GenericUnionFindTest {

    @Test
    public void testGenericUnionFind() {
        GenericUnionFind<Student> unionFind = new GenericUnionFind<>();
        Student stu1 = new Student(1, "jack");
        Student stu2 = new Student(2, "rose");
        Student stu3 = new Student(3, "jack");
        Student stu4 = new Student(4, "rose");
        unionFind.makeSet(stu1);
        unionFind.makeSet(stu2);
        unionFind.makeSet(stu3);
        unionFind.makeSet(stu4);

        unionFind.union(stu1, stu2);
        unionFind.union(stu3, stu4);

        Asserts.test(unionFind.isSame(stu1, stu2));
        Asserts.test(unionFind.isSame(stu3, stu4));
        Asserts.test(!unionFind.isSame(stu1, stu3));

        unionFind.union(stu1, stu3);
        Asserts.test(unionFind.isSame(stu1, stu3));

    }

}
