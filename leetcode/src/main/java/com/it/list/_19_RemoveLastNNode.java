package com.it.list;

/**
 * @author : code1997
 * @date : 2021/9/6 23:26
 */
public class _19_RemoveLastNNode {

    /**
     * 使用快慢指针:快指针领先慢指针n个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0,head);
        ListNode slowTail = newHead;
        ListNode fastTail = head;
        //fastTail领先slowTailn个节点
        while (n > 0) {
            fastTail = fastTail.next;
            n--;
        }
        //说明至少存在n个node节点
        while (fastTail != null) {
            fastTail = fastTail.next;
            slowTail = slowTail.next;
        }
        slowTail.next = slowTail.next.next;
        return newHead.next;
    }
}
