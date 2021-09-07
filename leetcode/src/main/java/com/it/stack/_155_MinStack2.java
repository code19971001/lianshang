package com.it.stack;

import java.util.Stack;

/**
 * 设计一个栈,支持push，pop,top操作,并可以在常熟时间内检索到最小元素的栈.
 * 方式1：空间换时间的思想，创建2个栈，一个栈用来存储正常的元素，另外一个栈作为最小栈.
 * 方式1：空间换时间的思想，封装节点的时候创建一个Node，分别存放当前节点的val以及目前为止所有节点的最小值.
 *
 * @author : code1997
 * @date : 2021/9/7 21:05
 */
public class _155_MinStack2 {

    static class Node {
        int val;
        int minVal;
    }

    Stack<Node> dataStack;

    public _155_MinStack2() {
        dataStack = new Stack<>();
    }

    public void push(int val) {
        Node node = new Node();
        node.val = val;
        node.minVal = dataStack.isEmpty() ? val : Math.min(val, dataStack.peek().minVal);
        dataStack.push(node);
    }

    public void pop() {
        dataStack.pop();
    }

    public int top() {
        return dataStack.peek().val;
    }

    public int getMin() {
        return dataStack.peek().minVal;
    }
}
