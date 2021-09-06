package com.it;

import com.it.list.ListNode;

/**
 * @author : code1997
 * @date : 2021/9/5 16:04
 */
public class _150_IntersectionTwoLinked {

    /**
     * 将两个链表改造成相同长度，将两个链表分别加入到对方的后面.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //求出两个链表的节点的数量
        ListNode curA = headA;
        ListNode curB = headB;
        //如果没有相交的节点，那么最终都为null，如果存在相交，则会返回
        while (curA != curB) {
            //注意这里的判断：对curA判断，而不是curA.next.如果没有相交节点，可能会出现死循环.
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

}
