package com.it.string;

import com.it.tree.TreeNode;

/**
 * 树自定义序列化方式，遍历二叉树，将所有节点的值保存下来。
 *
 * @author : code1997
 * @date : 2021/9/9 20:40
 */
public class _572_SubTree {


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return false;
        }
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        /*preOrder(root, s1);
        preOrder(subRoot, s2);*/
        postOrder(root, s1);
        postOrder(subRoot, s2);
        return s1.toString().contains(s2);
    }

    /**
     * 前序遍历
     * 节点无子节点：！val
     * 节点两个子节点：！val##
     * 节点1个子节点：！val！left###
     * !3!4!1##!2##5##
     * !4!1##!2##
     */
    private void preOrder(TreeNode root, StringBuilder s) {
        if (root == null) {
            s.append("#");
            return;
        }
        s.append("!" + root.val);
        preOrder(root.left, s);
        preOrder(root.right, s);
    }

    /**
     * 非空节点：val!
     * 空节点：#!
     * 注意：空节点需要也需要进行序列化
     */
    private void postOrder(TreeNode root, StringBuilder s) {
        if (root.left == null) {
            s.append("#!");
        } else {
            postOrder(root.left, s);
        }
        if (root.right == null) {
            s.append("#!");
        } else {
            postOrder(root.right, s);
        }
        s.append(root.val).append("!");
    }

}
