package com.it.list;

/**
 * @author : code1997
 * @date : 2021/9/6 23:58
 */
public class _83_RemoveDuplicatesList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-10);
        ListNode tail = newHead;
        while (head != null) {
            if (tail.val != head.val) {
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }
        tail.next = null;
        return newHead.next;
    }
}
