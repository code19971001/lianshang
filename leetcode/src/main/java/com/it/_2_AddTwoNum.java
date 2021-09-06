package com.it;

import com.it.list.ListNode;

/**
 * @author : code1997
 * @date : 2021/9/5 15:38
 */
public class _2_AddTwoNum {

    /**
     * 需要注意对于链表长度和进位
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        //记录进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
