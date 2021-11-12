package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * 红黑树 : 心中要联想到B树
 *
 * @author : code1997
 * @date : 2021/3/25 20:57
 */
public class RBT<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBT() {
        this(null);
    }

    public RBT(Comparator<E> comparator) {
        super(comparator);

    }

    /**
     * 1.如果是根节点，染成黑色并返回
     * 2.如果父节点是黑色，不做任何处理.
     * 3.如果uncle节点是红色，将父节点和叔父节点染成黑色，祖父节点染成红色并上溢.
     * 4.如果uncle节点不是红色,根据位置进行进行合适的旋转.
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //如果添加的节点是根节点，直接染成黑色.
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) {
            //父节点和叔父节点需要染色
            black(parent);
            black(uncle);
            //祖父节点染色并向上合并
            afterAdd(red(grand));
            return;
        }
        //叔父节点不是红色，可以使用avl树的旋转
        red(grand);
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                black(parent);
            } else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else {
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }


    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return null;
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


    /**
     * 判断某个节点的颜色
     *
     * @return : default is black,
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RedBlackNode<>(element, parent);
    }

    /**
     * 对于红黑树来说存在颜色，节点的颜色只可能是BLACK或者RED
     */
    private static class RedBlackNode<E> extends Node<E> {

        /**
         * 默认为红色会是的红黑树的性质尽快满足
         */
        boolean color = RED;

        public RedBlackNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String colorStr = color == RED ? "R_" : "B_";
            return colorStr + element.toString();
        }
    }


}
