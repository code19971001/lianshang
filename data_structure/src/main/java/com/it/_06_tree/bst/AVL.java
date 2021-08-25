package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * 平衡二叉树：
 *
 * @author : code1997
 * @date :2021-03-2021/3/20 20:17
 */
public class AVL<E> extends BST<E> {

    public AVL() {
        this(null);
    }

    public AVL(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        //找到最先失衡的父节点
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度，只需要维护添加的节点的父节点即可。
                updateHeight(node);
            } else {
                //恢复平衡
                //rebalance(node);
                rebalance2(node);
                break;
            }
        }
    }

    /**
     * 更新左右节点，更新高度。
     */
    private void rotateLeft(Node<E> grand) {
        //注意根据图进行理解，更新节点的指向
        Node<E> parent = grand.rightChild;
        Node<E> child = parent.leftChild;
        grand.rightChild = parent.leftChild;
        parent.leftChild = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<E> grand) {
        //更新子节点指向
        Node<E> parent = grand.leftChild;
        Node<E> child = parent.rightChild;
        grand.leftChild = parent.rightChild;
        parent.rightChild = grand;
        afterRotate(grand, parent, child);

    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //p成为根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.leftChild = parent;
        } else if (grand.isRightChild()) {
            grand.parent.rightChild = parent;
        } else {
            //grand是根节点
            root = parent;
        }
        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
        updateHeight(grand);
        updateHeight(parent);
    }

    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL:右旋
                rotate(grand, node.leftChild, node, node.rightChild, parent, parent.rightChild, grand, grand.rightChild);
            } else {
                //LR：先右旋后左旋；
                rotate(grand, parent.leftChild, parent, node.leftChild, node, node.rightChild, grand, grand.rightChild);

            }
        } else {
            if (node.isLeftChild()) {
                //RL:先左旋后右旋
                rotate(grand, grand.leftChild, grand, node.leftChild, node, node.rightChild, parent, parent.rightChild);

            } else {
                //RR：左旋
                rotate(grand, grand.leftChild, grand, parent.leftChild, parent, node.leftChild, node, node.rightChild);
            }
        }
    }

    private void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {
        //d称为子树的跟节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.leftChild = d;
        } else if (r.isRightChild()) {
            r.parent.rightChild = d;
        } else {
            root = d;
        }
        //处理a-b-c
        b.leftChild = a;
        b.rightChild = c;
        if (a != null) {
            a.parent = b;
        }
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);
        //处理e-f-g
        f.leftChild = e;
        f.rightChild = g;
        if (e != null) {
            e.parent = f;
        }
        if (g != null) {
            g.parent = f;
        }
        updateHeight(f);
        //处理b-d-f
        b.parent = d;
        f.parent = d;
        d.leftChild = b;
        d.rightChild = f;
        updateHeight(d);
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL:右旋
                rotateRight(grand);
            } else {
                //LR：先右旋后左旋；
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                //RL:先左旋后右旋
                rotateRight(parent);
                rotateLeft(grand);

            } else {
                //RR：左旋
                rotateLeft(grand);
            }
        }
    }


    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }


    private static class AVLNode<E> extends Node<E> {


        public int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子：左子树的高度-右子树的高度。
         */
        public int balanceFactor() {
            int leftHeight = (leftChild == null ? 0 : ((AVLNode<E>) leftChild).height);
            int rightHeight = (rightChild == null ? 0 : ((AVLNode<E>) rightChild).height);
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = (leftChild == null ? 0 : ((AVLNode<E>) leftChild).height);
            int rightHeight = (rightChild == null ? 0 : ((AVLNode<E>) rightChild).height);
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        private Node<E> tallerChild() {
            int leftHeight = (leftChild == null ? 0 : ((AVLNode<E>) leftChild).height);
            int rightHeight = (rightChild == null ? 0 : ((AVLNode<E>) rightChild).height);
            if (leftHeight > rightHeight) {
                return leftChild;
            }
            if (leftHeight < rightHeight) {
                return rightChild;
            }

            return isLeftChild() ? leftChild : rightChild;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_P(" + parentString + ")_h(" + height + ");";
        }

    }

    @Override
    protected void afterRemove(Node<E> node) {
        //找到最先失衡的父节点
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度，只需要维护添加的节点的父节点即可。
                updateHeight(node);
            } else {
                //恢复平衡:逐层进行更新，直到根节点都没有失去平衡
                //rebalance(node);
                rebalance2(node);
            }
        }
    }
}
