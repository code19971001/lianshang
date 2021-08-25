package com.it.work.queue;

import java.util.Stack;

/**
 * 栈来实现队列
 * 1）准备两个栈：inStack,outStack.
 * 2)入队时：push到inStack中
 *   出队时：如果outStack为空，将inStack中元素逐一弹出，push到outStack
 *          如果outStack不为空，outStack弹出栈顶元素。
 * @author : code1997
 * @date :2021-03-2021/3/10 13:07
 */
public class _232_栈实现队列 {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public _232_栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return outStack.isEmpty()&&inStack.isEmpty();
    }
    private void checkOutStack(){
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}
