package com.it._09_heap;

/**
 * @author : code1997
 * @date : 2021/11/24 22:12
 */
public interface IHeap<E> {

    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    E get();

    E remove();

    E replace(E element);

}
