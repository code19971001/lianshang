package com.it;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？使用morris实现O(1)级别的空间复杂度对二叉树的遍历。
 * link:https://leetcode-cn.com/problems/recover-binary-search-tree
 *
 * @author : code1997
 * @date : 2021/8/23 0:02
 */
public class _99_RecoverBinarySearchTree {

    private TreeNode preNode;

    private TreeNode firstNode;

    private TreeNode secondNode;

    /**
     * 核心：中序是升序的特点，
     * 1）如果被交换的两个节点是挨在一起的，则中序遍历之后只存在一个逆序对。
     * 2）如果被交换的两个节点不是挨在一起的，则中序遍历之后，存在两个逆序对。
     * 3）无论是一个或者两个逆序对，实际上我们我们要找的错误节点就是：第一个逆序对中较大的节点，第二个逆序对中较小二点节点。
     */
    public void recoverTree(TreeNode root) {
        //findWrongNodes(root);
        morris(root);
        //交换两个错误的节点的值
        swap(firstNode, secondNode);
    }

    /**
     * 中序遍历
     */
    private void findWrongNodes(TreeNode root) {
        if (root == null) {
            return;
        }
        findWrongNodes(root.left);
        find(root);
        findWrongNodes(root.right);
    }

    private void find(TreeNode root) {
        if (preNode != null && preNode.val > root.val) {
            //无论是存在几个逆序对都不会出现什么问题：总是保存逆序对中比较小的值。
            secondNode = root;
            //firstNode只能是第一个逆序对中比较大的值，不可以再次被覆盖。
            if (firstNode != null) {
                return;
            }
            firstNode = preNode;
        }
        //需要保留上一次便利的节点
        preNode = root;
        return;
    }

    private void swap(TreeNode firstNode, TreeNode secondNode) {
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    /**
     * 使用morris可以实现二叉树的遍历的时间复杂度为O(n)，空间复杂度为O(1).也被称为线索二叉树，线索指的是只想前驱或者后继。
     * 执行步骤：假设遍历到当前节点是N
     * ①如果N.left!=null,找到N的前驱节点p.
     * 如果p.right == null,那么令P.right=N,N=N.left.回到①
     * 如果P.right == N,那么p.right=null,打印N,N=N.right.回到①
     * 如果N.left==null，打印N,N=N.right.回到①
     */
    private void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            if (root.left != null) {
                //找到当前节点的前驱节点
                TreeNode precursor = root.left;
                while (precursor.right != null && precursor.right != root) {
                    precursor = precursor.right;
                }
                if (precursor.right == null) {
                    precursor.right = root;
                    root = root.left;
                } else {
                    //precursor.rightChild == node
                    precursor.right = null;
                    find(root);
                    root = root.right;
                }
            } else {
                find(root);
                root = root.right;
            }
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
