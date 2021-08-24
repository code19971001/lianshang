package com.it;

/**
 * @author : code1997
 * @date :2021-03-2021/3/25 21:58
 */
public class _82_删除排序链表中的重复元素 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);

        node.next=new ListNode(2);

        node.next=new ListNode(2);
        node.next=new ListNode(2);
        node.next=new ListNode(2);

        deleteDuplicates(node);
    }

    public static ListNode addNode(ListNode node,int val,boolean flag){
        if (flag){
            return null;
        }
        return node.next=new ListNode(val);
    }


    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode cur = new ListNode();
        cur.next=head;
        ListNode next = head;
        while (next.next!=null){
            if (cur.next.val==next.next.val){
                next=next.next;
                cur.next=next;
                continue;
            }
            cur=cur.next;
            next=next.next;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
