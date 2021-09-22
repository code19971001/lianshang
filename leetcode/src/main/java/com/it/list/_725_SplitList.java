package com.it.list;

/**
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 *
 * @author : code1997
 * @date : 2021/9/22 23:31
 */
public class _725_SplitList {

    /**
     * 使用虚拟头节点简化代码
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        if (head == null) {
            return res;
        }
        ListNode cur = head;
        int size = 0;
        //log(n)
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        System.out.println(size);
        //最大的连续长度
        int maxSize = size / k;
        //最大连续长度的数量
        int maxCount = size % k;
        cur = head;
        for (int i = 0; i < k && cur != null; i++) {
            res[i] = cur;
            int curSize = maxSize + (maxCount-- > 0 ? 1 : 0);
            for (int j = 0; j < curSize - 1; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return res;
    }
}
