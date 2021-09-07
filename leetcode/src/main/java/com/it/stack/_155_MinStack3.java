package com.it.stack;

/**
 * 设计一个栈,支持push，pop,top操作,并可以在常熟时间内检索到最小元素的栈.
 * 方式1：空间换时间的思想，创建2个栈，一个栈用来存储正常的元素，另外一个栈作为最小栈.
 * 方式2：空间换时间的思想，封装节点的时候创建一个Node，分别存放当前节点的val以及目前为止所有节点的最小值.
 * 方式3：使用链表实现
 *
 * @author : code1997
 * @date : 2021/9/7 21:05
 */
public class _155_MinStack3 {

    Node head;

    static class Node {
        int val;
        int minVal;
        Node next;

        public Node(int val, int minVal, Node next) {
            this.val = val;
            this.minVal = minVal;
            this.next = next;
        }
    }


    public _155_MinStack3() {
        head = new Node(0, 0, null);
    }

    public void push(int val) {
        int min = head.next == null ? val : Math.min(val, head.next.minVal);
        head.next = new Node(val, min, head.next);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.next.val;
    }

    public int getMin() {
        return head.next.minVal;
    }
}
