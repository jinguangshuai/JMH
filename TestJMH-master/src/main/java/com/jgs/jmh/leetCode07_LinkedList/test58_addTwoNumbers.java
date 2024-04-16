package com.jgs.jmh.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/16 - 04 - 16 - 14:13
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class test58_addTwoNumbers {
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
    //相加会超出数字的取值范围
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long sum1 = 0;
        long sum2 = 0;
        int m = 0, n = 0;
        while (null != l1) {
            sum1 += l1.val * Math.pow(10, m);
            l1 = l1.next;
            m++;
        }
        while (null != l2) {
            sum2 += l2.val * Math.pow(10, n);
            l2 = l2.next;
            n++;
        }
        long sum = sum1 + sum2;
        String s = String.valueOf(sum);
        List<ListNode> list = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            ListNode listNode = new ListNode(s.charAt(i) - '0');
            list.add(listNode);
        }
        if (list.size() > 1) {
            for (int i = 0; i < list.size() - 1; i++) {
                list.get(i).next = list.get(i+1);
            }
        }
        return list.get(0);
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            //如果头结点为空，则头结点为新建节点
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                //如果头节点不为空，则头结点的下一个节点为新建节点
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }




    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
        ListNode listNode = addTwoNumbers(l1, l2);
        ListNode listNode1 = addTwoNumbers1(l1, l2);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
