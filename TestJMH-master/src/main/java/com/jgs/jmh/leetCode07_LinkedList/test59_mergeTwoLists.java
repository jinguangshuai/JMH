package com.jgs.jmh.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/16 - 04 - 16 - 17:28
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */
public class test59_mergeTwoLists {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //合并排序
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode head = null, tail = null;
        while (null != list1 && null != list2) {
            if (null == head) {
                if (list1.val >= list2.val) {
                    head = tail = list2;
                    list2 = list2.next;
                } else {
                    head = tail = list1;
                    list1 = list1.next;
                }
            } else {
                if (list1.val >= list2.val) {
                    tail.next = list2;
                    tail = tail.next;
                    list2 = list2.next;
                } else {
                    tail.next = list1;
                    tail = tail.next;
                    list1 = list1.next;
                }
            }
        }
        while (null != list1){
            if(null == head){
                head = tail = list1;
                list1 = list1.next;
            }else {
                tail.next = list1;
                tail = tail.next;
                list1 = list1.next;
            }
        }
        while (null != list2){
            if(null == head){
                head = tail = list2;
                list2 = list2.next;
            }else {
                tail.next = list2;
                tail = tail.next;
                list2 = list2.next;
            }
        }
        return head;
    }

    //递归解决有序链表合并问题
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if(null == list1){
            return list2;
        }else if(null == list2){
            return list1;
        }else if(list1.val < list2.val){
            list1.next = mergeTwoLists2(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists2(list1,list2.next);
            return list2;
        }
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
//        ListNode l1 = null;
//        ListNode l2 = new ListNode(0);
//        ListNode listNode = mergeTwoLists(l1, l2);
//        ListNode listNode1 = mergeTwoLists1(l1, l2);
        ListNode listNode2 = mergeTwoLists2(l1, l2);
        while (null != listNode2) {
            System.out.println(listNode2.val);
            listNode2 = listNode2.next;
        }

    }
}
