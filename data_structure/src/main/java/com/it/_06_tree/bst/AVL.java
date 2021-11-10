package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * 平衡二叉搜索树，亦可称为自平衡二叉搜索树，在AVL树中任意节点的两个子树的高度最大差别为1。增加和删除可能需要
 * 通过一次或者多次树旋转来平衡这个树。
 * <p>
 * 为什么出现AVL树？
 * 如果我们从小到大添加节点，那么二叉搜索树会退化成链表，导致查找效率急剧下降，当树越平衡，那么查找的效率肯定越高.
 * <p>
 * 注意：我们不追求完美平衡，使用尽量少的平衡次数达到适度平衡即可.
 * 平衡因子：某接待您的左右子树的高度差,因此AVL树的平衡因子只可能是1，0，-1。如果绝对值超过1，那么就处于失衡状态.
 *
 * @author : code1997
 * @date : 2021/3/20 20:17
 */
public class AVL<E> extends BST<E> {

    public AVL() {
        this(null);
    }

    public AVL(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 1.添加的节点总是在角落.
     * 2.添加节点的直接父节点不会失去平衡.
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //找到最先失衡的父节点，也就是我们称为g的节点
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度，只需要维护添加的节点的父节点即可。
                updateHeight(node);
            } else {
                //只要找到第一个不平衡的节点(实际上就是节点g)，然后恢复平衡，整体就会恢复平衡
                //rebalance(node);
                rebalance2(node);
                break;
            }
        }
    }


    /**
     * 节点的删除只会导致父节点失衡.
     * 如果父节点失衡了，我们恢复平衡可能会导致树的高度发生变化，就有可能造成一连串的祖父节点失衡，极端情况下，所有的祖先节点都会失衡(O(logn)).
     *
     */
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

    /**
     * 一定要注意被旋转节点
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        //根据节点的情况，进行旋转恢复平衡.
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

    /**
     * 旋转，更新节点的parent以及高度节点高度.
     */
    private void rotateLeft(Node<E> grand) {
        //注意根据图进行理解，更新节点的指向
        Node<E> parent = grand.rightChild;
        Node<E> child = parent.leftChild;
        grand.rightChild = parent.leftChild;
        parent.leftChild = grand;
        //旋转之后一定要更新节点之间关系.
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<E> grand) {
        //获取需要更改的节点
        Node<E> parent = grand.leftChild;
        Node<E> child = parent.rightChild;
        //更改grand和parent的指针
        grand.leftChild = parent.rightChild;
        parent.rightChild = grand;
        afterRotate(grand, parent, child);

    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 统一左旋及右旋的操作，根据图发现规律.
     * 传入旋转参数的参数，根据可能需要变化的参数，从小到大排好顺序，abc-d-efg
     */
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

    /**
     * 处理高度的时候一定要自底向上.
     */
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

        /**
         * 创建的肯定是叶子，叶子节点的高度肯定是1，一定要注意这里的取值.
         */
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
            //更新当前节点的高度.
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 返回比较高的左右子节点.
         */
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

}
