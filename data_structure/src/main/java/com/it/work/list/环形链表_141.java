package com.it.work.list;

/**
 * @author : code1997
 * @date :2020-11-2020/11/30 16:58
 */
public class 环形链表_141 {
    /**
     * 使用快慢指针的方式来解决
     * 联想：跑步套圈
     * @param head ：头节点
     * @return ：尾节点
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next ==null){
            return false;
        }
        ListNode slowNode = head;
        ListNode fastNode = head.next;
        while (fastNode != null && fastNode.next != null){
            if (slowNode == fastNode){
                return true;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return false;
    }
}
