package com.it._05_queue;

import java.util.Arrays;

/**
 * 循环双端队列
 *
 * @author : code1997
 * @date :2021-03-2021/3/11 21:21
 */
public class MyCircleDequeue<E> {

    private int front;
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public MyCircleDequeue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[indexOf(size)] = element;
        size++;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = indexOf(-1);
        elements[front] = element;
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
            newElements[i] = elements[indexOf(i)];
        }
        elements = newElements;
        front = 0;
    }

    /**
     * 需要注意:
     * 1.front+index可能会出现负数的情况,如果是负数可以通过加上数组的长度来转化。
     */
    private int indexOf(int index) {
        index = front + index;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

    public E deQueueRear() {
        int realIndex = indexOf(size - 1);
        E temp = elements[realIndex];
        elements[realIndex] = null;
        size--;
        return temp;
    }

    public E deQueueFront() {
        E temp = elements[front];
        elements[front] = null;
        front = indexOf(1);
        size--;
        return temp;
    }

    public E rear() {
        return elements[indexOf(size - 1)];
    }

    public E front() {

        return elements[front];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[indexOf(i)] = null;
        }
        size = 0;
        front = 0;
    }

    @Override
    public String toString() {
        return "MyCircleDequeue{" +
                "capacity=" + elements.length +
                ", front=" + front +
                ", size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
