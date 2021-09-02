package com.it;

/**
 * @author : code1997
 * @date : 2021/9/2 21:08
 */
public class _offer_22_GetKthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        //获取节点的数量
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head;
        for (int i = 0; i < size - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 使用快慢指针的方式
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        //获取节点的数量
        if (head == null) {
            return null;
        }
        ListNode si = head;
        ListNode qi = head;
        for (int i = 0; i < k; i++) {
            qi = qi.next;
        }
        while (qi != null) {
            si = si.next;
            qi = qi.next;
        }
        return si;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
