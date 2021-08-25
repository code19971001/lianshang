package com.it._04_recursion;

/**
 * @author : code1997
 * @date : 2021/4/21 20:31
 */
public class Test {

    public static void main(String[] args) {
        String str11 = new StringBuilder("ja").append("va").toString();
        System.out.println(str11 == str11.intern());  //false

        String str12 = new StringBuilder("alibaba").toString();
        //intern:如果字符串常量池存在该字符串，就直接返回其引用，如果没有则创建该对象并返回其引用。
        System.out.println(str12 == str12.intern()); //false

        String str13 = new StringBuilder("mei").append("tuan").toString();
        //intern:如果字符串常量池存在该字符串，就直接返回其引用，如果没有则创建该对象并返回其引用。
        System.out.println(str13 == str13.intern()); //true
    }
}
