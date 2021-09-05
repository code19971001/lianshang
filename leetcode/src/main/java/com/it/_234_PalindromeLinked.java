package com.it;

import com.it.list.ListNode;

/**
 * 回文链表：判断一个链表是否是回文链表.
 * <p>
 * 链表中节点数目在范围[1, 105] 内.
 * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决.
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
        ListNode rhead = reverseList(head);
        ListNode lhead = head;
        while (rhead != null) {
            if (lhead.val != rhead.val) {
                return false;
            }
            rhead = rhead.next;
            lhead = lhead.next;
        }
        return true;
    }

    private ListNode middleNode(ListNode head) {
        return null;
    }

    /**
     * 翻转链表：返回翻转之后链表的头节点.
     */
    private ListNode reverseList(ListNode head) {

        return null;
    }
}
