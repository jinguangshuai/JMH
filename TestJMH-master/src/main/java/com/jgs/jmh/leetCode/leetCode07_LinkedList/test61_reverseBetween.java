package com.jgs.jmh.leetCode.leetCode07_LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jgs
 * @Data：2024/4/17 - 04 - 17 - 20:57
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

/**
 * * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class test61_reverseBetween {
    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int val) {
            this.value = val;
            this.next = null;
        }
    }

    //利用list存储待逆转链表，然后翻转此链表
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head) {
            return null;
        } else if (left == right) {
            return head;
        }
        int start = 1;
        ListNode cur = head;
        ListNode pre = null, afterNode = null;
        List<ListNode> list = new ArrayList<>();
        while (null != cur) {
            if (start == left - 1) {
                pre = cur;
            } else if (start >= left && start <= right) {
                list.add(cur);
            } else if (start == right + 1) {
                afterNode = cur;
            }
            cur = cur.next;
            start++;
        }
        //逆转链表
        if (null != pre) {
            pre.next = list.get(list.size() - 1);
        } else {
            head = list.get(list.size() - 1);
        }
        int m = list.size();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i - 1 >= 0) {
                list.get(i).next = list.get(i - 1);
            }
        }
        if (null != afterNode) {
            list.get(0).next = afterNode;
        } else {
            list.get(0).next = null;
        }
        return head;
    }

    //穿针引线
    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        if (null == head) {
            return null;
        } else if (left == right) {
            return head;
        }
        //待翻转节点的第一个节点的前一个节点  rightNode待翻转节点的最后一个节点
        ListNode pre = null, rightNode = null;
        //创建虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        pre = dummyNode;
        //找到待旋转链表的前置链表
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        //待翻转节点的第一个节点
        ListNode leftNode = pre.next;
        //待翻转节点的最后一个节点的下一个节点
        ListNode afterNode = rightNode.next;

        //切断连接
        pre.next = null;
        rightNode.next = null;
        //翻转链表
        reverseNode(leftNode);
        pre.next = rightNode;
        leftNode.next = afterNode;
        return dummyNode.next;

    }

    public static void reverseNode(ListNode leftNode){
        ListNode pre = null;
        ListNode cur = leftNode;
        while (null != cur){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    //一次遍历「穿针引线」反转链表（头插法）
    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        if (null == head) {
            return null;
        } else if (left == right) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next = null;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }



    public static void print(ListNode node) {
        while (null != node) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);
//        l1.next.next.next.next = new ListNode(5);
//        ListNode listNode = reverseBetween(l1, 1, 2);
        ListNode listNode1 = reverseBetween1(l1, 1, 2);
//        print(listNode);
        print(listNode1);
    }
}
