package com.it;

import com.it.list.ListNode;

/**
 * @author : code1997
 * @date : 2021/9/5 15:09
 */
public class _203_RemoveNode {

    /**
     * 移除链表元素：移除链表中我们指定的元素
     */
    public ListNode removeElements1(ListNode head, int val) {
        //定义两个指针指向指向新的链表的头部和尾部
        ListNode newHead = null;
        ListNode tail = null;
        while (head != null) {
            if (head.val != val) {
                if (tail == null) {
                    //代表现在还没有元素
                    newHead = head;
                } else {
                    //连接链表
                    tail.next = head;
                }
                tail = head;
            }
            head = head.next;
        }
        if (tail == null) {
            //表明链表没有我们需要的元素
            return null;
        } else {
            //将最后的节点的下一个节点的值置为null
            tail.next = null;
        }
        return newHead;
    }

    /**
     * 技巧：使用虚拟头节点来简化我们的代码.
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
