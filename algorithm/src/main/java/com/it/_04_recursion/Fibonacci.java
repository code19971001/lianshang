package com.it._04_recursion;

import com.it.tools.Times;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/4/20 21:18
 */
public class Fibonacci {

    public static void main(String[] args) {
        //求第n个斐波那契数
        Times.test("fib1", () ->
                System.out.println(fibonacci1(30))
        );
        Times.test("fib2", () ->
                System.out.println(fibonacci2(30))
        );
        Times.test("fib3", () ->
                System.out.println(fibonacci3(30))
        );
        Times.test("fib4", () ->
                System.out.println(fibonacci4(30))
        );
        Times.test("fib5", () ->
                System.out.println(fibonacci5(30))
        );
    }


    private static int fibonacci1(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    private static int fibonacci6(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci6(n, 1, 1);
    }

    private static int fibonacci6(int n, int first, int second) {
        if (n <= 1) {
            return first;
        }
        return fibonacci6(n - 1, second, first + second);
    }

    private static int fibonacci2(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fibonacci2(n, array);
    }

    private static int fibonacci2(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fibonacci2(n - 1, array) + fibonacci2(n - 2, array);
        }
        return array[n];
    }

    private static int fibonacci3(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    private static int fibonacci4(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] ints = new int[3];
        Arrays.fill(ints, 1);
        for (int i = 3; i <= n; i++) {
            ints[2] = ints[0] + ints[1];
            ints[0] = ints[1];
            ints[1] = ints[2];
        }
        return ints[2];
    }

    private static int fibonacci5(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }


}
