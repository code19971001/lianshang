package com.it._04_recursion;

/**
 * @author : code1997
 * @date : 2021/4/20 22:38
 */
public class GetSum {
    public static void main(String[] args) {
        System.out.println(sum1(3));
        System.out.println(sum2(3));
        System.out.println(sum3(3));
    }

    private static int sum1(int n) {
        if (n <= 1) {
            return n;
        }
        return n + sum1(n - 1);
    }

    private static int sum2(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static int sum3(int n) {
        if (n <= 1) {
            return n;
        }
        return (1 + n) * n >> 1;
    }

}
