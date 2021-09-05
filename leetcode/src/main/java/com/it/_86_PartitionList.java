package com.it;

import com.it.list.ListNode;

/**
 * 分隔链表：给定一个链表和一个特定值x，对链表进行分隔，使得所有小于x的节点都在大于或等于x的节点之前.
 *
 * @author : code1997
 * @date : 2021/9/5 22:01
 */
public class _86_PartitionList {

    /**
     * 分隔为两个链表，然后将两个链表连接.
     */
    public ListNode partition(ListNode head, int x) {
        ListNode lhead = new ListNode(0);
        ListNode ltail = lhead;
        ListNode rhead = new ListNode(0);
        ListNode rtail = rhead;
        while (head != null) {
            if (head.val < x) {
                ltail.next = head;
                ltail = head;
            } else {
                rtail.next = head;
                rtail = head;
            }
            head = head.next;
        }
        //一定要添加这一步，否则可能会报错.
        rtail.next = null;
        ltail.next = rhead.next;
        return lhead.next;
    }
}
