package com.it._04_recursion;

/**
 * 尾调用：一个函数的最后一个动作是调用函数。
 *
 * @author : code1997
 * @date : 2021/4/20 23:56
 */
public class TailCall {

    public static void main(String[] args) {

    }

    private static int factorial1(int n) {
        if (n <= 1) {
            return n;
        }
        return n * factorial1(n - 1);
    }

    private static int factorial2(int n) {
        if (n <= 1) {
            return n;
        }
        return factorial2(n, 1);
    }

    private static int factorial2(int n, int result) {
        return factorial2(n - 1, result * n);
    }

}
