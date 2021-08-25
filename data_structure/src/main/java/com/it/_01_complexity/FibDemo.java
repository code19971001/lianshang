package com.it._01_complexity;

public class FibDemo {
    /**
     * 求第n个斐波那契数：1 1 2 3 5 8 13
     * 方法1：使用递归解决
     * 优点：但是写起来比较简洁.
     * 缺点：存在性能的问题,但是数大的时候就会计算很慢.时间复杂度是指数级别的.
     */
    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 方式2：使用迭代来解决.
     * 优点：时间复杂度会比较低.
     */
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(fib1(45));
        long fib1EndTime = System.currentTimeMillis();
        System.out.println("fib1花费的时间：" + (fib1EndTime - startTime));
        System.out.println(fib2(45));
        long fib2EndTime = System.currentTimeMillis();
        System.out.println("fib2花费的时间：" + (fib2EndTime - fib1EndTime));
    }
}
