package com.it.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : code1997
 * @date : 2021/11/4 21:59
 */
public class _225_InvertBinaryTree {

    /**
     * 实际上所有的遍历方式都可以实现二叉树的翻转.只要保证可以遍历到每一个节点就可以了
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode tmp;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
