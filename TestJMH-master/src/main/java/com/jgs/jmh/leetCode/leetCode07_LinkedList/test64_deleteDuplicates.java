package com.jgs.jmh.leetCode.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/19 - 04 - 19 - 14:47
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

/**
 * * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class test64_deleteDuplicates {

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

    //一次遍历
    // 关键点，每次判断 cur.next  cur.next.next是否为空
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        //每次判断 cur.next  cur.next.next是否为空 如果为空直接结束
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                //如果节点值等于value，那么当前cur直接指向下一个节点
                // 即cur.next = cur.next.next;
                // 0 1 1 2...
                // 0->1->1->2...  0->1->2... 0->2...
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummyNode.next;
    }

    public static void print(ListNode node) {
        if (null == node) {
            System.out.println("null");
        }
        while (null != node) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next.next = new ListNode(5);
        ListNode listNode1 = deleteDuplicates(listNode);
        print(listNode1);
    }
}
