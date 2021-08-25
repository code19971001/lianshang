package com.it._05_queue;

import java.util.Arrays;

/**
 * 循环队列：CircleQueue，底层一般使用数组来实现。
 *
 * @author : code1997
 * @date :2021-03-2021/3/10 14:29
 */
public class MyCircleQueue<E> {

    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public MyCircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[indexOf(i)] = null;
        }
        size = 0;
        front = 0;
    }


    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[(front + size) % (elements.length)] = element;
        size++;
    }

    /**
     * 动态扩容的代码
     *
     * @param capacity ：容量，size+1。
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(i + front) % elements.length];

        }
        elements = newElements;
        front = 0;
    }

    /**
     * 用于索引的映射封装，给我们逻辑上的index，返回底层数组上实际的index.
     * 尽量避免对数据的/，%,*，以及浮点运算等，会消耗大量的cpu性能。
     */
    private int indexOf(int index) {
        //return (front + index) % elements.length;
        index = index + front;
        return index - ((index >= elements.length) ? elements.length : 0);
    }

    public E deQueue() {
        if (size <= 0) {
            throw new RuntimeException("MyCircleQueue is empty");
        }
        E temp = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return temp;
    }

    public E front() {
        return elements[front];
    }


    @Override
    public String toString() {
        return "MyCircleQueue{" +
                "capacity=" + elements.length +
                ", front=" + front +
                ", size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
