package com.jgs.jmh.leetCode.leetCode15_devide;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/16 - 05 - 16 - 20:55
 * @Description:com.jgs.jmh.leetCode15_devide
 * @version:1.0
 */

/**
 * * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class test111_mergeKLists {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //（1）递归每次分为两部分
    //（2）直到最后两个或者一个链表，进行合并排序
    //（3）返回最终结果
    public static ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        int m = lists.length;
        int mid = m / 2;
        ListNode[] subList1 = getSubList(lists, 0, mid);
        ListNode[] subList2 = getSubList(lists, mid, m);
        ListNode node1 = mergeKLists(subList1);
        ListNode node2 = mergeKLists(subList2);
        return sort1(node1, node2);
    }

    public static ListNode[] getSubList(ListNode[] lists, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return new ListNode[]{lists[left]};
        }
        ListNode[] result = new ListNode[right - left];
        for (int i = 0; i < right - left; i++) {
            result[i] = lists[left + i];
        }
        return result;
    }

    //递归排序
    public static ListNode sort1(ListNode node1, ListNode node2) {
        if (null == node1) {
            return node2;
        } else if (null == node2) {
            return node1;
        }
        if (node1.val <= node2.val) {
            node1.next = sort1(node1.next, node2);
            return node1;
        } else {
            node2.next = sort1(node1, node2.next);
            return node2;
        }
    }

    //非递归排序
    public static ListNode sort2(ListNode node1, ListNode node2) {
        if (null == node1) {
            return node2;
        } else if (null == node2) {
            return node1;
        }
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = node1, temp2 = node2;
        while (null != temp1 && null != temp2) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (null != temp1) {
            temp.next = temp1;
        }
        if (null != temp2) {
            temp.next = temp2;
        }
        return dummy.next;
    }


    //官方解法
    public static ListNode mergeKLists3(ListNode[] lists) {
        return merge3(lists, 0, lists.length - 1);
    }

    public static ListNode merge3(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = (left + right) >> 1;
        return sort3(merge3(lists, left, mid), merge3(lists, mid + 1, right));
    }

    public static ListNode sort3(ListNode node1, ListNode node2) {
        if (null == node1) {
            return node2;
        } else if (null == node2) {
            return node1;
        }
        if (node1.val <= node2.val) {
            node1.next = sort3(node1.next, node2);
            return node1;
        } else {
            node2.next = sort3(node1, node2.next);
            return node2;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);
        ListNode[] lists = {node1, node2, node3};
        ListNode listNode = mergeKLists(lists);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
