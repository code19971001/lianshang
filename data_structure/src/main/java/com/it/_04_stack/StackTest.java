package com.it._04_stack;

/**
 * @author : code1997
 * @date :2021-03-2021/3/9 22:09
 */
public class StackTest {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        System.out.println(stack.peek());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.peek());
    }
}
