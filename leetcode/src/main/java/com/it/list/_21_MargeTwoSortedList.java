package com.it.list;

/**
 * @author : code1997
 * @date : 2021/9/6 23:15
 */
public class _21_MargeTwoSortedList {


    /**
     * 技巧：虚拟头节点
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            tail.next = l1;
            tail = l1;
            l1 = l1.next;
        }
        while (l2 != null) {
            tail.next = l2;
            tail = l2;
            l2 = l2.next;
        }
        tail.next = null;
        return newHead.next;
    }
}
