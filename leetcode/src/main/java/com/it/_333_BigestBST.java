package com.it;

/**
 * 给定一个二叉树，找到其中最大的二叉搜索树(BST)子树，其中最大指的是子树节点数最多的。
 * <p>
 * 中序遍历求最大升序子序列。
 *
 * @author : code1997
 * @date : 2021/8/23 22:57
 */
public class _333_BigestBST {

    /**
     * 采用自顶向下的方式实现:实际上就是前序遍历的变种。
     * 但是我们发现是可以进行优化的，采用自底向上的方式进行遍历，也就是后序遍历
     */
    public int largestBSTSubtree(TreeNode root) {
        return root == null ? 0 : getInfo(root).size;
    }

    /**
     * 返回以node为根节点的二叉树的最大BST子树信息。
     * 使用后序遍历的方式
     */
    private TreeInfo getInfo(TreeNode root) {
        //如果根节点是null，那么值就是null
        if (root == null) {
            return null;
        }
        TreeInfo li = getInfo(root.left);
        TreeInfo ri = getInfo(root.right);
        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
        //li和ri必须符合条件之一才可以算作是BST，因此leftBstSize，rightBstSize的初始值十分重要
        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max) {
            leftBstSize = li.size;
            min = li.min;
        }
        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.left && root.val < li.max) {
            rightBstSize = ri.size;
            max = ri.max;
        }
        if (leftBstSize >= 0 && rightBstSize >= 0) {
            //以root为根节点的二叉树就是BST
            return new TreeInfo(root, leftBstSize + rightBstSize + 1, max, min);
        }
        //以root为根节点的树不是BST
        if (li != null && ri != null) {
            //左右节点都为null
            return li.size > ri.size ? li : ri;
        }
        //左右节点其中一个为空
        return li != null ? li : ri;
    }

    private static class TreeInfo {
        TreeNode root;
        int size;
        /**
         * max和min主要用来判断其父节点是否是BST
         */
        int max;
        int min;

        public TreeInfo(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
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
