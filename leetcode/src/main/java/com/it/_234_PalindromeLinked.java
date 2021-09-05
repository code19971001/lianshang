package com.it;

import com.it.list.ListNode;

/**
 * 回文链表：判断一个链表是否是回文链表.
 * <p>
 * 链表中节点数目在范围[1, 105] 内.
 * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决.
 * 进阶：不破坏链表原来的结构.
 * 方案：将原来的链表再次翻转.
 *
 * @author : code1997
 * @date : 2021/9/5 22:29
 */
public class _234_PalindromeLinked {


    /**
     * 快慢指针：
     * 中间节点的定义：如果一个节点的下一个节点是后半部分的开始，那么他就是中间节点.
     * 将中间节点后面的链表进行翻转,然后快慢指针进行对比.
     * 为什么要进行翻转？因为是单链表，所以没有办法反向遍历.
     * 本解决方案会破坏链表的结构.
     * 时间复杂度：O(n).
     * 空间复杂度：O(1).
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        //找到中间节点
        ListNode mid = middleNode(head);
        //翻转右半部分节点.
        ListNode rhead = reverseList2(mid.next);
        ListNode lhead = head;
        ListNode rOldHead = rhead;
        //逐个时间复杂度O(n)
        boolean result = true;
        while (rhead != null) {
            if (lhead.val != rhead.val) {
                result = false;
                break;
            }
            rhead = rhead.next;
            lhead = lhead.next;
        }
        reverseList2(rOldHead);
        return result;
    }

    /**
     * 使用快慢指针得到中间节点.
     * 快指针每次走两格，慢指针每次走一格.
     * 时间复杂度：O(n).
     */
    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 翻转链表：返回翻转之后链表的头节点.
     * 时间复杂度：O(n)
     */
    private ListNode reverseList(ListNode head) {
        //边界条件的判断
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        //将当前的next的next指向当前元素
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代的方式：翻转链表
     * 1->2->3->4->null ==> 4->3->2->1->null
     */
    private ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
