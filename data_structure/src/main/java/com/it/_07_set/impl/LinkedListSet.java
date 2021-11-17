package com.it._07_set.impl;

import com.it._07_set.ISet;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用链表实现set
 *
 * @author : code1997
 * @date : 2021/11/17 21:30
 */
public class LinkedListSet<E> implements ISet<E> {

    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    /**
     * 如果已经包含，存在两种处理方式
     * 1.直接返回
     * 2.找到元素，进行覆盖
     */
    @Override
    public void add(E element) {
        if (list.contains(element)) {
            return;
        }
        list.add(element);
    }

    @Override
    public void remove(E element) {
        list.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        for (E e : list) {
            if (visitor.visit(e)) {
                return;
            }
        }

    }
}
