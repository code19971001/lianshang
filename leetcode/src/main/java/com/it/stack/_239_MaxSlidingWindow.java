package com.it.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : code1997
 * @date : 2021/9/7 21:51
 */
public class _239_MaxSlidingWindow {

    /**
     * 使用双端队列，双端队列中存储的由大到小的节点
     * 1.如果nums[i]>= nums[队尾]，不断地删除队尾.
     * 2.将i加入队尾
     * 3.检验一下对头是否在滑动窗口的范围内(新的变量begin).
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int[] maxNums = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //移除小于等nums[i]的值
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //添加元素到队尾
            deque.offerLast(i);
            int begin = i - k + 1;
            if (begin < 0) {
                continue;
            }
            if (deque.peekFirst() < begin) {
                //移除已经过期的索引
                deque.pollFirst();
            }
            maxNums[begin] = nums[deque.peekFirst()];
        }
        return maxNums;
    }

    /**
     * 不使用双端队列，直接使用简单粗暴的方式.
     * 时间复杂度：O(nk).
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int[] maxNums = new int[nums.length - k + 1];
        //存储当前滑动窗口最大值的索引
        int maxIndex = 0;
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        for (int li = 0; li < maxNums.length; li++) {
            int ri = li + k - 1;
            if (maxIndex < li) {
                //索引过期，需要从新计算最大值的索引
                maxIndex = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[maxIndex] < nums[i]) {
                        maxIndex = i;
                    }
                }
            } else if (nums[maxIndex] <= nums[ri]) {
                //说明索引没有过期，不需要重新扫描
                maxIndex = ri;
            }
            maxNums[li] = nums[maxIndex];
        }
        return maxNums;
    }

    @Test
    public void test1() {
        System.out.println(System.currentTimeMillis());
        System.out.println("执行结束:" + System.currentTimeMillis());

    }


}
