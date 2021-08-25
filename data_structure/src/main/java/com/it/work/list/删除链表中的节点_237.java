package com.it.work.list;

/**
 * 237. 删除链表中的节点
 */
public class 删除链表中的节点_237 {

    /**
     * 小技巧：
     * 以前我们删除链表中节点的时候需要我们找到当前节点的前一个节点，但是此时我们是无法获取前一个节点的信息的。
     * 因此需要用其他的方式来实现。当前节点就是要删除的节点，因为要删除的节点就是当前要删除的节点，因此我们可以采用值覆盖。
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

}
