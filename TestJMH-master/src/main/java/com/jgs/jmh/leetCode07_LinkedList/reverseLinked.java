package com.jgs.jmh.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/18 - 04 - 18 - 17:27
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */
public class reverseLinked {

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int val) {
            this.value = val;
            this.next = null;
        }
    }
    
    //翻转节点之后返回头结点
    public static ListNode reverseHeadNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    //翻转节点之后返回尾结点
    public static ListNode reverseTailNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return node;
    }

    public static void print(ListNode node) {
        while (null != node) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        ListNode listNode1 = reverseHeadNode(listNode);
        System.out.println(listNode1.value);

//        ListNode listNode2 = reverseTailNode(listNode);
//        System.out.println(listNode2.value);
    }
    
    
}
