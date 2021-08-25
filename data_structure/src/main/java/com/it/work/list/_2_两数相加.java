package com.it.work.list;

/**
 * @author : code1997
 * @date :2021-03-2021/3/22 22:36
 */
public class _2_两数相加 {
    public static void main(String[] args) {

    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode tempNode=l3;
        int last=0;
        while (l1.next!=null||l2.next!=null){
            if (l1.next==null){
                l1.next=new ListNode(0,null);
            }
            if (l2.next==null){
                l2.next=new ListNode(0,null);
            }
            int sum=l1.val+l2.val;
            l3.val=(sum>10?sum-10:sum)+last;
            if (sum>10){
                last=1;
            }else {
                last=0;
            }
            l1=l1.next;
            l2=l2.next;
            l3.next=new ListNode();
        }
        return tempNode;
    }

    private static class ListNode {

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
