package com.it._06_tree.bst;

import com.it.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
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
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private Logger logger = Logger.getLogger("logger");
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;


    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 获取节点的前驱节点.
     */
    private Node<E> precursor(Node<E> node) {
        if (node == null) {
            return node;
        }
        if (node.leftChild != null) {
            Node<E> tempNode = node.leftChild;
            while (tempNode.rightChild != null) {
                tempNode = tempNode.rightChild;
            }
            return tempNode;
        } else {
            while (node.parent != null && node == node.parent.leftChild) {
                node = node.parent;
            }
            //node.parent==null.
            //node节点==node.parent.rightChild
            return node.parent;
        }
    }

    /**
     * 获取节点的后继节点
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return node;
        }
        if (node.rightChild != null) {
            Node<E> tempNode = node.rightChild;
            while (tempNode.leftChild != null) {
                tempNode = tempNode.leftChild;
            }
            return tempNode;
        } else {
            while (node.parent != null && node == node.parent.rightChild) {
                node = node.parent;
            }
            //node.parent==null.
            //node节点==node.parent.leftChild
            return node.parent;
        }
    }


    public boolean isComplete2() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> tempNode = queue.poll();
            if (leaf && !tempNode.isLeaf()) {
                return false;
            }

            if (tempNode.leftChild != null) {
                queue.offer(tempNode.leftChild);
            } else if (tempNode.rightChild != null) {
                return false;
            }
            if (tempNode.rightChild != null) {
                queue.offer(tempNode.rightChild);
            } else {
                leaf = true;
            }
        }
        return true;
    }


    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> tempNode = queue.poll();
            if (leaf && !tempNode.isLeaf()) {
                return false;
            }

            if (tempNode.hasTwoChildren()) {
                queue.offer(tempNode.leftChild);
                queue.offer(tempNode.rightChild);
            } else if ((tempNode.leftChild == null) && (tempNode.rightChild != null)) {
                return false;
            } else {
                //后面的节点必须是叶子节点
                leaf = true;
                //解决左边不为空，右边为空的情况下节点无法入队的弊端。
                if (tempNode.leftChild != null) {
                    queue.offer(tempNode);
                }
            }
        }
        return true;

    }


    /**
     * @return :树的高度
     */
    public int height() {

        return height(root);
    }

    //递归
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    //层序遍历
    public int height2() {
        if (root == null) {
            return 0;
        }
        int height = 0;
        //每一层的元素树
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> tempNode = queue.poll();
            levelSize--;
            if (tempNode.leftChild != null) {
                queue.offer(tempNode.leftChild);
            }
            if (tempNode.rightChild != null) {
                queue.offer(tempNode.rightChild);
            }
            if (levelSize == 0) {
                //说明要访问下一层
                levelSize = queue.size();
                height++;
            }

        }
        return height;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            //first node
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        if (compare > 0) {
            parent.rightChild = newNode;
        } else {
            parent.leftChild = newNode;
        }
        size++;
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
        } else if (node.parent == null) {
            //只有一个根节点
            root = null;
        } else {
            //是叶子节点且度为1，且父节点不为空
            if (node.parent.leftChild == node) {
                node.parent.leftChild = null;
            } else {
                node.parent.rightChild = null;
            }
        }
        size--;
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
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).leftChild;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).rightChild;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return ((Node<E>) node).element + "_" + parentString;
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

    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + "\t");
        preorderTraversal(node.leftChild);
        preorderTraversal(node.rightChild);

    }

    /**
     * 中序遍历：对于二叉搜索树来说中序遍历是升序或者降序
     */
    public void inorderTraversal() {
        ineorderTraversal(root);
    }

    private void ineorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        ineorderTraversal(node.leftChild);
        System.out.print(node.element + "\t");
        ineorderTraversal(node.rightChild);

    }

    /**
     * 后序遍历
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.leftChild);
        postorderTraversal(node.rightChild);
        System.out.print(node.element + "\t");

    }

    /**
     * 层序遍历:使用队列实现，将
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.print(node.element + "\t");
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }

    }


    public static interface Visitor<E> {
        /**
         * 实现访问的具体操作
         *
         * @param element ：返回的元素。
         */
        void visit(E element);
    }

    public void preorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorder(root, visitor);
    }

    public void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.leftChild, visitor);
        preorder(node.rightChild, visitor);
    }

    public void inorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        inorder(root, visitor);
    }

    public void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        inorder(node.leftChild, visitor);
        visitor.visit(node.element);
        inorder(node.rightChild, visitor);
    }

    public void postorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    public void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        postorder(node.leftChild, visitor);
        postorder(node.rightChild, visitor);
        visitor.visit(node.element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }


    private static class Node<E> {
        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }

        public boolean isLeaf() {

            return this.leftChild == null && this.rightChild == null;
        }

        public boolean hasTwoChildren() {
            return this.leftChild != null && this.rightChild != null;
        }

    }


}
