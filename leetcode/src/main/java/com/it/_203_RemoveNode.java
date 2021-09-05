package com.it;

import com.it.list.ListNode;

/**
 * @author : code1997
 * @date : 2021/9/5 15:09
 */
public class _203_RemoveNode {

    public ListNode removeElements1(ListNode head, int val) {
        //定义两个指针指向指向新的链表的头部和尾部
        ListNode newHead = null;
        ListNode tail = null;
        while (head != null) {
            if (head.val != val) {
                if (tail == null) {
                    newHead = head;
                    tail = head;
                } else {
                    tail.next = head;
                    tail = head;
                }
            }
            head = head.next;
        }
        if (tail == null) {
            return null;
        } else {
            tail.next = null;
        }
        return newHead;
    }

    /**
     * 技巧：虚拟头节点
     */
    public ListNode removeElements2(ListNode head, int val) {
        //定义两个指针指向指向新的链表的头部和尾部
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (head != null) {
            if (head.val != val) {
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }
        tail.next = null;
        return newHead.next;
    }
}
