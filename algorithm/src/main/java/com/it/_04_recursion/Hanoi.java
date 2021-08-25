package com.it._04_recursion;

/**
 * @author : code1997
 * @date : 2021/4/20 22:47
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, "A", "B", "C");
    }

    private static void hanoi(int n, String a, String b, String c) {
        if (n == 1) {
            move(n, a, c);
            return;
        }
        hanoi(n - 1, a, c, b);
        move(n, a, c);
        hanoi(n - 1, b, a, c);
    }

    private static void move(int n, String from, String to) {
        System.out.println("将盘子【" + n + "】从【" + from + "】挪动到【" + to + "】");
    }
}
