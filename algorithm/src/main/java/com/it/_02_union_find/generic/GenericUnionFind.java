package com.it._02_union_find.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 支持自定义对象并查集实现。
 *
 * @author : code1997
 * @date : 2021/4/7 21:32
 */
public class GenericUnionFind<E> {

    private Map<E, Node<E>> nodes = new HashMap<>();

    public void makeSet(E e) {
        if (nodes.containsKey(e)) {
            return;
        }
        Node<E> initNode = new Node<>(e);
        nodes.put(e, initNode);
    }

    /**
     * 基于路径分裂的实现。
     *
     * @param e
     * @return
     */
    private Node<E> findNode(E e) {
        Node<E> eNode = nodes.get(e);
        if (eNode == null) {
            return null;
        }
        while (!Objects.equals(eNode.value, eNode.parent.value)) {
            eNode.parent = eNode.parent.parent;
            eNode = eNode.parent;
        }
        return eNode;
    }

    public E find(E e) {
        Node<E> eNode = findNode(e);
        return eNode == null ? null : eNode.value;

    }

    public void union(E e1, E e2) {
        Node<E> eNode1 = findNode(e1);
        Node<E> eNode2 = findNode(e2);
        if (eNode1 == null || eNode2 == null) {
            return;
        }
        if (Objects.equals(eNode1.value, eNode2.value)) {
            return;
        }
        if (eNode1.rank > eNode2.rank) {
            eNode2.parent = eNode1;
        } else if (eNode1.rank < eNode2.rank) {
            eNode1.parent = eNode2;
        } else {
            eNode1.parent = eNode2;
            eNode2.rank++;
        }
    }

    public boolean isSame(E e1, E e2) {
        return Objects.equals(find(e1), find(e2));
    }

    private static class Node<E> {
        E value;
        Node<E> parent = this;
        int rank = 1;

        private Node(E e) {
            this.value = e;
        }
    }

}
