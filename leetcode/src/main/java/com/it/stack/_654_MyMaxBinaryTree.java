package com.it.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 变种：返回一个数组，要求数组中存储每个节点的父节点的索引,如果没有父节点，就存储-1.
 * 方案：扫描一遍数组，每遍历一个值，就去左边以及右边进行遍历，分别找到第一个比他大的元素，一般来说，两个数中比较小的元素是他的的父节点.
 * 如何快速找到第一个比他大的值呢？
 * 维持一个单调递减的栈.
 * > 如果元素可以存放成功，则代表左边第一个比较大的元素就是之前的栈顶元素.
 * > 如果不可以存放成功，则弹出栈顶元素，代表栈顶元素的右边比他大的值是存放失败的元素.
 *
 * @author : code1997
 * @date : 2021/9/8 21:23
 */
public class _654_MyMaxBinaryTree {


    public int[] findParentIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        //分别存放左边和右边第一个比该位置元素大的所以.
        int[][] table = new int[2][nums.length];
        int[] parentNums = new int[nums.length];
        //存放索引
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums.length; i++) {
            table[1][i] = -1;
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                //需要弹出元素
                Integer pop = stack.pop();
                //设置右边第一个比当前值大的元素.
                table[1][pop] = i;
            }
            //如果栈顶不为空，说明栈顶元素是当前元素左边第一个最大的值的索引.,如果为空，这说明左边没有比他大的值，
            table[0][i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < table[0].length; i++) {
            if (table[0][i] == -1) {
                parentNums[i] = table[1][i];
            } else if (table[1][i] == -1) {
                //实际上要求值不相等
                parentNums[i] = table[0][i];
            } else if (nums[table[0][i]] > nums[table[1][i]]) {
                parentNums[i] = table[1][i];
            } else {
                parentNums[i] = table[0][i];
            }
        }
        System.out.println(Arrays.toString(parentNums));
        return parentNums;
    }

    public static void main(String[] args) {
        new _654_MyMaxBinaryTree().findParentIndex(new int[]{3, 2, 1, 6, 0, 5});
    }

}
