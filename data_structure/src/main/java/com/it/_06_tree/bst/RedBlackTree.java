package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * @author : code1997
 * @date :2021-03-2021/3/25 20:57
 */
public class RedBlackTree<E> extends AVL<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }


    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RedBlackNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return !colorOf(node) == RED;
    }


    /**
     * 对于红黑树节点
     *
     * @param <E>
     */
    private static class RedBlackNode<E> extends Node<E> {
        boolean color;

        public RedBlackNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }


}
