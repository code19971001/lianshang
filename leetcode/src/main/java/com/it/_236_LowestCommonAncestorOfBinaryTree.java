package com.it;


/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * link:https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author : code1997
 * @date : 2021/8/22 23:09
 */
public class _236_LowestCommonAncestorOfBinaryTree {
    public static void main(String[] args) {

    }

    /**
     * 这是错误的代码：有可能左右两边都可以找到公共父节点。
     * p和q的公共祖先存在三种情况
     * 1)p,q在两边，最近公共祖先是根节点。
     * 2)p,q在一边，最近公共祖先必定在一边。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        //去root的leftChild去找公共祖先
        TreeNode leftParentNode = lowestCommonAncestor(root.left, p, q);
        //去root的leftChild去找公共祖先
        TreeNode rightParentNode = lowestCommonAncestor(root.right, p, q);
        if (leftParentNode != null && rightParentNode == null) {
            return leftParentNode;
        }
        if (leftParentNode == null && rightParentNode != null) {
            return rightParentNode;
        }
        //左右都没有，则为根节点，还存在一种情况：left和right都为null
        return leftParentNode == null && rightParentNode == null ? null : root;
    }

    /**
     * p和q的公共祖先‘
     * 1）如果p，q同时存在在这个二叉树中，就可以成功返回他们的最近公共祖先。
     * 2）如果p,q都不存在这颗二叉树中，返回null。
     * 3）如果只有p存在于这个二叉树中，返回p.
     * 4）如果只有q存在于这个二叉树中，返回q。
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        //去root的leftChild去找公共祖先
        TreeNode leftParentNode = lowestCommonAncestor2(root.left, p, q);
        //去root的leftChild去找公共祖先
        TreeNode rightParentNode = lowestCommonAncestor2(root.right, p, q);
        //如果两边都不为空，则表示最近公共祖先是父节点。
        if (leftParentNode != null && rightParentNode != null) {
            return root;
        }
        return leftParentNode != null ? leftParentNode : rightParentNode;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
