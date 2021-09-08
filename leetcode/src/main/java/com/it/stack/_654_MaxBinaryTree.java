package com.it.stack;

import com.it.tree.TreeNode;

/**
 *
 * @author : code1997
 * @date : 2021/9/8 21:23
 */
public class _654_MaxBinaryTree {


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findRoot(nums, 0, nums.length);
    }

    /**
     * 给定一个数组以及范围[li,ri),找出根节点，实际上就是最大值
     */
    private TreeNode findRoot(int[] nums, int li, int ri) {
        //注意递归机终止条件
        if (li >= ri) {
            return null;
        }
        int maxIndex = li;
        for (int i = li + 1; i < ri; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = findRoot(nums, li, maxIndex);
        root.right = findRoot(nums, maxIndex + 1, ri);
        return root;
    }
}
