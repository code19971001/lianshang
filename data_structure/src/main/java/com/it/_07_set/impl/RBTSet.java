package com.it._07_set.impl;

import com.it._06_tree.bst.RBT;
import com.it._07_set.ISet;

/**
 * 使用红黑树来实现set：要求元素必须存在可比较性.
 *
 * @author : code1997
 * @date : 2021/11/17 21:56
 */
public class RBTSet<E> implements ISet<E> {

    private RBT<E> set = new RBT<>();

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.size() == 0;
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean contains(E element) {
        return set.contains(element);
    }

    @Override
    public void add(E element) {
        set.add(element);
    }

    @Override
    public void remove(E element) {
        set.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        set.inorder(visitor::visit);

    }
}
