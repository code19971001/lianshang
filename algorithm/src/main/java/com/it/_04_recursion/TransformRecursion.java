package com.it._04_recursion;

import java.util.Stack;

/**
 * @author : code1997
 * @date : 2021/4/20 23:40
 */
public class TransformRecursion {

    public static void main(String[] args) {
        log1(4);
        log2(4);
        log3(4);
    }

    private static void log1(int n) {
        if (n < 1) {
            return;
        }
        log1(n - 1);
        int v = n + 10;
        System.out.println(v);
    }

    private static void log2(int n) {
        Stack<StackFrame> stack = new Stack<>();
        while (n > 0) {
            stack.push(new StackFrame(n, n + 10));
            n--;
        }
        while (!stack.isEmpty()) {
            StackFrame frame = stack.pop();
            System.out.println(frame);
        }
    }

    static class StackFrame {
        int n;
        int v;

        public StackFrame(int n, int v) {
            this.n = n;
            this.v = v;
        }

        @Override
        public String toString() {
            return "StackFrame{" +
                    "n=" + n +
                    ", v=" + v +
                    '}';
        }
    }

    private static void log3(int n) {
        int result = 10;
        for (int i = 1; i <= n; i++) {
            System.out.println(i+result);
        }
    }
}
