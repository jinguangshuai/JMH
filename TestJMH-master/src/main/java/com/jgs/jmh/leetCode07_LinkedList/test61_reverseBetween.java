package com.jgs.jmh.leetCode07_LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jgs
 * @Data：2024/4/17 - 04 - 17 - 20:57
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
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

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head) {
            return null;
        } else if (left == right) {
            return head;
        }
        int start = 1;
        ListNode cur = head;
        ListNode pre = null,afterNode = null;
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
        if(null != pre ){
            pre.next = list.get(list.size()-1);
        }else {
            head = list.get(list.size()-1);
        }
        int m = list.size();
        for (int i = list.size() - 1; i >= 0; i--) {
            if( i - 1 >= 0){
                list.get(i).next = list.get(i-1);
            }
        }
        if(null != afterNode){
            list.get(0).next = afterNode;
        }else {
            list.get(0).next = null;
        }
        return head;
    }

    public static void print(ListNode node){
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
        ListNode listNode = reverseBetween(l1, 1, 2);
        print(listNode);
    }
}
