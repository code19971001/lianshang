package com.it._05_queue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : code1997
 * @date :2021-03-2021/3/10 12:14
 */
public class MyQueue<E> {

    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {

        return list.get(0);
    }

    public void clear() {
        list.clear();
    }


}
