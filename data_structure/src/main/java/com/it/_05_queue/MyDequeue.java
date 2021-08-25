package com.it._05_queue;

import java.util.LinkedList;
import java.util.List;

/**
 * 双端队列：double end queue,可以在头尾两端添加,删除队列。
 *
 * @author : code1997
 * @date :2021-03-2021/3/10 13:46
 */
public class MyDequeue<E> {

    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueueRear(E element) {
        list.add(element);
    }

    public void enQueueFront(E element) {
        list.add(0, element);
    }

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public E rear() {

        return list.get(list.size() - 1);
    }

    public E front() {

        return list.get(0);
    }

    public void clear() {
        list.clear();
    }


}
