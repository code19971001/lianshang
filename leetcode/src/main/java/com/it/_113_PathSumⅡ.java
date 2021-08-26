package com.it;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : code1997
 * @date : 2021/8/26 23:34
 */
public class _113_PathSumⅡ {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        dfs(root, list, targetSum, new ArrayList<>());
        return list;
    }

    private void dfs(TreeNode treeNode, List<List<Integer>> list, int targetSum, List<Integer> nums) {
        if (treeNode == null) {
            return;
        }
        targetSum -= treeNode.val;
        nums.add(treeNode.val);
        if (treeNode.left == null && treeNode.right == null) {
            //说明到了叶子节点,判断值的和
            if (targetSum == 0) {
                list.add(new ArrayList<>(nums));
            }
        } else {
            dfs(treeNode.left, list, targetSum, nums);
            dfs(treeNode.right, list, targetSum, nums);
        }
        //恢复现场
        nums.remove(nums.size() - 1);
    }

    public class TreeNode {
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
