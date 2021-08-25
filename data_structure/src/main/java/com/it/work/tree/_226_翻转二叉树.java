package com.it.work.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * link:https://leetcode-cn.com/problems/invert-binary-tree/
 * @author : code1997
 * @date :2021-03-2021/3/19 20:53
 */
public class _226_翻转二叉树 {

    /**
     * 前序遍历
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    /**
     * 后序遍历
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        invertTree2(root.left);
        invertTree2(root.right);
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        return root;
    }

    /**
     * 中序遍历
     */

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tempNode = root.left;
        invertTree3(root.left);
        root.left = root.right;
        root.right = tempNode;
        invertTree3(root.left);
        return root;
    }

    /**
     * 层序遍历
     */
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            TreeNode node = tempNode.left;
            tempNode.left = tempNode.right;
            tempNode.right = node;
            if (tempNode.left != null) {
                queue.offer(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.offer(tempNode.right);
            }
        }
        return root;
    }


}
