package com.jgs.jmh.leetCode.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/19 - 04 - 19 - 9:13
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

/**
 * * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class test63_removeNthFromEnd {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //双指针法
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode left = dummyNode, right = head.next, target = head,tail = head;
        for (int i = 0; i < n - 1; i++) {
            tail = tail.next;
        }
        while (null != tail){
            tail = tail.next;
            if(null == tail){
                //目标节点的前置节点指向目标节点的右节点
                left.next = right;
                //删除目标节点的下一个指向
                target.next = null;
                return dummyNode.next;
            }else {
                left = left.next;
                right = right.next;
                target = target.next;
            }
        }
        return dummyNode.next;
    }

    //官方双指针法
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        ListNode target = second.next;
        second.next = second.next.next;
        target.next = null;
        ListNode ans = dummy.next;
        return ans;
    }


    public static void print(ListNode node) {
        if(null == node){
            System.out.println("null");
        }
        while (null != node) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
        ListNode listNode1 = removeNthFromEnd(listNode, 1);
        print(listNode1);
    }
}
