package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * 相对于BST，新增了旋转的功能.
 *
 * @author : code1997
 * @date : 2021/11/12 14:30
 */
public class BBST<E> extends BST<E> {


    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 旋转，更新节点的parent以及高度节点高度.
     */
    protected void rotateLeft(Node<E> grand) {
        //注意根据图进行理解，更新节点的指向
        Node<E> parent = grand.rightChild;
        Node<E> child = parent.leftChild;
        grand.rightChild = parent.leftChild;
        parent.leftChild = grand;
        //旋转之后一定要更新节点之间关系.
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> grand) {
        //获取需要更改的节点
        Node<E> parent = grand.leftChild;
        Node<E> child = parent.rightChild;
        //更改grand和parent的指针
        grand.leftChild = parent.rightChild;
        parent.rightChild = grand;
        afterRotate(grand, parent, child);

    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //使得parent成为根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.leftChild = parent;
        } else if (grand.isRightChild()) {
            grand.parent.rightChild = parent;
        } else {
            //grand是根节点
            root = parent;
        }
        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的父节点
        grand.parent = parent;
        //更新grand以及parent节点的高度，因为我们已经进行了旋转，所以需要先更新grand，然后更新parent.
    }


    /**
     * 处理高度的时候一定要自底向上.
     */
    protected void rotate(
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

        //处理e-f-g
        f.leftChild = e;
        f.rightChild = g;
        if (e != null) {
            e.parent = f;
        }
        if (g != null) {
            g.parent = f;
        }

        //处理b-d-f
        b.parent = d;
        f.parent = d;
        d.leftChild = b;
        d.rightChild = f;
    }

}
