package com.jgs.jmh.leetCode07_LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/21 - 04 - 21 - 19:31
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */
public class test66_partition {
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
    //双栈做法
    public static ListNode partition1(ListNode head, int x) {
        if (null == head) {
            return head;
        }
        Deque<ListNode> lessDeque = new ArrayDeque<>();
        Deque<ListNode> moreDeque = new ArrayDeque<>();
        ListNode cur = head;
        while (null != cur) {
            if (cur.val < x) {
                lessDeque.offerFirst(cur);
            } else {
                moreDeque.offerFirst(cur);
            }
            cur = cur.next;
        }
        ListNode newHead = null;
        if (!lessDeque.isEmpty()) {
            newHead = lessDeque.pollLast();
        } else {
            newHead = moreDeque.pollLast();
        }
        ListNode result = new ListNode(-1, newHead);
        while (!lessDeque.isEmpty()) {
            newHead.next = lessDeque.pollLast();
            newHead = newHead.next;
        }
        while (!moreDeque.isEmpty()) {
            newHead.next = moreDeque.pollLast();
            newHead = newHead.next;
        }
        newHead.next = null;
        return result.next;
    }

    public static ListNode partition2(ListNode head, int x) {
        if (null == head) {
            return head;
        }
        ListNode less = new ListNode(-1);
        ListNode more = new ListNode(-1);
        //计算结果的前置节点
        ListNode lessHead = less;
        //大于x数字的前置节点
        ListNode moreHead = more;
        while (null != head){
            if(head.val < x){
                less.next = head;
                less = less.next;
            }else {
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        more.next = null;
        less.next = moreHead.next;
        return lessHead.next;
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
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(2);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(2);
//        listNode.next.next.next.next.next.next = new ListNode(7);
//        ListNode listNode1 = partition1(listNode, 3);
        ListNode listNode2 = partition2(listNode, 3);
        print(listNode2);
    }
}   
