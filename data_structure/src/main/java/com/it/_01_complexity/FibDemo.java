package com.it._01_complexity;

public class FibDemo {
    /**
     * 求第n个斐波那契数：0 1 1 2 3 5 8 13
     * 问题：存在性能的问题,数小的时候没有问题，但是数大的时候就会计算很慢。
     * @param n：第n个斐波那契数
     * @return ：第n个斐波那契数的值
     */
    public static int fib1(int n){
        if (n<=1) {
            return n;
        }
        return fib1(n-1)+fib1(n-2);
    }
    public static int fib2(int n){
        if (n<=1) return n;
        int first=0;
        int second=1;
        for (int i = 0; i < n - 1; i++) {
            int sum=first+second;
            first=second;
            second=sum;
        }
        return second;
    }
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        System.out.println(fib1(45));
        long fib1EndTime=System.currentTimeMillis();
        System.out.println("fib1花费的时间："+(fib1EndTime-startTime));
        System.out.println(fib2(45));
        long fib2EndTime=System.currentTimeMillis();
        System.out.println("fib2花费的时间："+(fib2EndTime-fib1EndTime));
    }
}
