package com.it.stack;

import java.util.Stack;

/**
 * 设计一个栈,支持push，pop,top操作,并可以在常熟时间内检索到最小元素的栈.
 * 方式1：空间换时间的思想，创建2个栈，一个栈用来存储正常的元素，另外一个栈作为最小栈.
 * 方式1：空间换时间的思想，
 *
 * @author : code1997
 * @date : 2021/9/7 21:05
 */
public class _155_MinStack {

    Stack<Integer> dataStack;
    Stack<Integer> mStack;

    public _155_MinStack() {
        dataStack = new Stack<>();
        mStack = new Stack<>();
    }

    public void push(int val) {
        dataStack.push(val);
        mStack.push(mStack.isEmpty() ? val : Math.min(val, mStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        mStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return mStack.peek();
    }
}
