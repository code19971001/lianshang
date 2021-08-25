package com.it._06_tree.bst;


import com.it._06_tree.BinaryTree;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * 前序：树状结构展示
 * 中序：二叉树中序遍历按升序或者降序处理节点
 * 后序遍历：适用于一些先子后父的操作。
 * 层序遍历：计算二叉树的高度，判断一棵树是否是完全二叉树。
 *
 * @author : code1997
 * @date :2021-03-2021/3/15 10:48
 */
public class BST<E> extends BinaryTree<E> {
    private Logger logger = Logger.getLogger("logger");
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 正数：e1>e2
     * 0：e1==e2
     * 负数：e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            //first node
            //root = new Node<>(element, null);
            root = createNode(element, null);
            size++;
            //新添加结点之后的操作
            afterAdd(root);
            return;
        }
        Node<E> parent = null;
        Node<E> node = root;
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            parent = node;
            if (compare > 0) {
                node = node.rightChild;
            } else if (compare < 0) {
                node = node.leftChild;
            } else {
                //如果值相等就覆盖
                node.element = element;
                return;
            }
        }
        //Node<E> newNode = new Node<>(element, parent);
        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.rightChild = newNode;
        } else {
            parent.leftChild = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> node) {
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }


    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 根据节点，删除节点
     */
    private void remove(Node<E> node) {
        logger.info("删除节点：" + node);
        if (node == null) {
            return;
        }

        if (node.hasTwoChildren()) {
            //该节点的度为2,找后继节点，覆盖度为2的节点的值
            Node<E> successor = successor(node);
            node.element = successor.element;
            //删除后继节点。
            node = successor;
        }
        //删除node节点(度为1或者0)。
        Node<E> replacement = node.leftChild != null ? node.leftChild : node.rightChild;
        if (replacement != null) {
            //删除节点度为1，
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
                //前面已经处理结束
                /*replacement.parent=null;*/
            }
            if (node == node.parent.leftChild) {
                node.parent.leftChild = replacement;
            } else {
                node.parent.rightChild = replacement;
            }
            //恢复平衡需要等待节点确定已经被删除。
            afterRemove(node);
        } else if (node.parent == null) {
            //只有一个根节点
            root = null;
            afterRemove(node);
        } else {
            //是叶子节点且度为1，且父节点不为空
            if (node.parent.leftChild == node) {
                node.parent.leftChild = null;
            } else {
                node.parent.rightChild = null;
            }
            afterRemove(node);
        }
        size--;
    }

    protected void afterRemove(Node<E> node) {
    }

    /**
     * 根据元素找到节点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.rightChild;
            } else {
                node = node.leftChild;
            }
        }
        //没找到
        return null;
    }


    public boolean contains(E element) {
        return node(element) != null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must be not null!");
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(root, builder, "");
        return builder.toString();
    }

    private void toString(Node<E> node, StringBuilder builder, String prefix) {
        if (node == null) {
            return;
        }
        builder.append(prefix).append(node.element).append("\n");
        toString(node.leftChild, builder, prefix + "[L]");
        toString(node.rightChild, builder, prefix + "[R]");

    }
}
