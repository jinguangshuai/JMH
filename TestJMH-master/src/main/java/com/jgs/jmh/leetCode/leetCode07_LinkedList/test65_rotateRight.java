package com.jgs.jmh.leetCode.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/21 - 04 - 21 - 18:14
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */
public class test65_rotateRight {

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
    //遍历一次，获取长度
    //第二次拼接
    public static ListNode rotateRight(ListNode head, int k) {
        if(null == head || k == 0 || null == head.next){
            return head;
        }
        int count = 1;
        ListNode cur = head;
        while (null != cur.next){
            cur = cur.next;
            count++;
        }
        int add = count - k % count;
        if(add == count){
            return head;
        }
        cur.next = head;
        while (add > 0){
            cur = cur.next;
            add--;
        }
        ListNode result = cur.next;
        cur.next = null;
        return result;
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
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
//        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);
        ListNode listNode1 = rotateRight(listNode,3);
        print(listNode1);
    }
}
