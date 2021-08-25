package com.it.work.list;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author 龍
 */
public class 反转链表_206 {
    /**
     * 迭代的方式实现
     * @param head ：传入头节点
     * @return ：返回新的链表头
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur=head;
        ListNode preNode=null;
        while(cur!=null){
            //保存下一个节点
            ListNode temp=cur.next;
            //当前节点指向上一个节点
            cur.next=preNode;
            //节点后移动
            preNode=cur;
            cur=temp;
        }
        return preNode;
    }

    /**
     * 递归的方式实现链表的反转
     * @param head 传入头节点
     * @return :反转之后链表的头节点
     */
    public ListNode reverseList2(ListNode head) {
        //边界条件的判断
        if(head==null||head.next==null){
            return head;
        }
        ListNode newHead=reverseList2(head.next);
        //将当前的next的next指向当前元素
        head.next.next=head;
        head.next=null;
        return newHead;
    }

}
