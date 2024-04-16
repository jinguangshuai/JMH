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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null, tail = null;
        while (null != list1 && null != list2) {
            if (null == head) {
                if (list1.val >= list2.val) {
                    head = tail = new ListNode(list2.val);
                    list2 = list2.next;
                } else {
                    head = tail = new ListNode(list1.val);
                    list1 = list1.next;
                }
            } else {
                if (list1.val >= list2.val) {
                    tail.next = new ListNode(list2.val);
                    tail = tail.next;
                    list2 = list2.next;
                } else {
                    tail.next = new ListNode(list1.val);
                    tail = tail.next;
                    list1 = list1.next;
                }
            }
        }
        while (null != list1){
            if(null == head){
                head = tail = new ListNode(list1.val);
                list1 = list1.next;
            }else {
                tail.next = new ListNode(list1.val);
                tail = tail.next;
                list1 = list1.next;
            }
        }
        while (null != list2){
            if(null == head){
                head = tail = new ListNode(list2.val);
                list2 = list2.next;
            }else {
                tail.next = new ListNode(list2.val);
                tail = tail.next;
                list2 = list2.next;
            }
        }
        return head;
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
        ListNode listNode = mergeTwoLists(l1, l2);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
