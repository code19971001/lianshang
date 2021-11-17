package com.it._07_set;

/**
 * Set接口：遍历，添加，删除的时间复杂度依赖内部实际存储的时间复杂度.
 *
 * @author : code1997
 * @date : 2021/11/17 21:25
 */
public interface ISet<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    abstract class Visitor<E> {

        boolean stop;

        public abstract boolean visit(E element);
    }
}
