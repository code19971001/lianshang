package com.it._06_tree;


import com.it._06_tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : code1997
 * @date :2021-03-2021/3/20 19:34
 * <p>
 * 如果我们看到一颗二叉搜索树是完全二叉树，我们想要还原这棵树，只需要对这棵树进行层序遍历即可.
 * 重构二叉树：中序遍历+前/后序遍历。中序遍历的意义在于可以根据节点将左右子树分离开来.
 * 前序遍历+后序遍历不可以重构出二叉树，因为存在很多种可能性，无法区分左右子树，但是如果是真二叉树，则结果是唯一的。
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
        public E element;
        public Node<E> leftChild;
        public Node<E> rightChild;
        public Node<E> parent;

        public boolean isLeftChild() {
            return parent != null && this == parent.leftChild;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.rightChild;
        }

        /**
         * @return : sibling node
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.rightChild;
            }
            if (isRightChild()) {
                return parent.leftChild;
            }
            //如果不是左/右节点，是叶子节点，所以肯定没有兄弟节点。
            return null;
        }


        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_P(" + parentString + ")";
        }

        public boolean isLeaf() {
            return this.leftChild == null && this.rightChild == null;
        }

        public boolean hasTwoChildren() {
            return this.leftChild != null && this.rightChild != null;
        }

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
     * @return :树的高度
     */
    public int height() {

        return height(root);
    }

    /**
     * 递归实现
     */
    protected int height(Node<E> node) {
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
            //我们要做的事情
            if (levelSize == 0) {
                //说明要访问下一层
                levelSize = queue.size();
                height++;
            }

        }
        return height;
    }


    /**
     * 相对于第一种做法，只是将入队判断操作进行了修改.
     *
     * @return
     */
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
                //代表左边为空，右边不为空，则肯定不是完全二叉树
                return false;
            }
            if (tempNode.rightChild != null) {
                //代表左边肯定不为空.
                queue.offer(tempNode.rightChild);
            } else {
                //左边不为空，右边为空，那么接下来的节点应该全部都是叶子节点.
                leaf = true;
            }
        }
        return true;
    }


    /**
     * 判断二叉树是否是完全二叉树.完全二叉树的叶子节点全部在最后两层且如果一个节点的左子节点不为空，右子节点为null，则后面的节点全部为null
     * 1.如果node.left!=null && node.right!=null,将node.left和node.right按照顺序入队。
     * 2.如果node.left==null && node.right!=null, return false.
     * 3.如果node.left!=null && node.right==null或者node.left==null && node.right==null, 则后面的节点全部都是叶子节点.否则不是完全二叉树。
     * 根据如上的判断，我们可以使用一个flag来表示后面的节点是否应该是叶子节点，只要我们分析全情况就没有什么问题.
     */
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

    protected Node<E> precursor2(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.leftChild != null) {
            Node<E> pre = node.leftChild;
            while (pre.rightChild != null) {
                pre = pre.rightChild;
            }
            return pre;
        } else {
            while (node.parent != null && node == node.parent.leftChild) {
                node = node.parent;
            }
            return node.parent;
        }
    }


    /**
     * 获取节点的前驱节点.
     * note:前驱节点指的是中序遍历的前一个节点.为了方便理解，我们可以使用BST树来理解
     */
    protected Node<E> precursor(Node<E> node) {
        if (node == null) {
            return null;
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
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.rightChild != null) {
            //如果存在右子节点，那么后继节点肯定是当前节点的右子节点的最左子节点
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

    public void invertTree() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        Node<E> tmp;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            tmp = node.leftChild;
            node.leftChild = node.rightChild;
            node.rightChild = tmp;
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

        return node;
    }


}
