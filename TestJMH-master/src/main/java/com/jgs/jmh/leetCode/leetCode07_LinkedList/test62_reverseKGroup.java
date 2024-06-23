package com.jgs.jmh.leetCode.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/18 - 04 - 18 - 15:28
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class test62_reverseKGroup {
    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int val) {
            this.value = val;
            this.next = null;
        }
    }

    //每k个节点进行交换
    //首先记录 待翻转节点的前置节点，左节点，右节点，后置节点
    //然后前置节点、右节点切断链接，翻转数组
    //翻转完成之后
    //前置节点的下一个节点为右节点
    //左节点的下一个节点为后置节点
    //
    //更新前置节点为左节点（因为翻转完成之后，左节点位于最后）
    //更新下一个待翻转左节点，值为上一个翻转数组的后置节点
    //head起始位置为后置节点
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode, leftNode = dummyNode.next, rightNode = null;
        while (null != head) {
            for (int i = 0; i < k - 1; i++) {
                if (null == head) return dummyNode.next;
                head = head.next;
            }
            //记录当前范围最右节点
            if (null == head) return dummyNode.next;
            rightNode = head;
            if (null == rightNode) return dummyNode.next;
            //记录后继节点
            ListNode suc = rightNode.next;
            //切断链接
            pre.next = null;
            rightNode.next = null;
            //翻转节点 2->1
            reverseNode(leftNode);
            //翻转节点的结果   最后一个节点连接下一个待翻转节点的头部   2->1->3
            leftNode.next = suc;
            //前置节点的下一个节点为右节点  -1->2->1->3
            pre.next = rightNode;
            //前置节点为当前翻转队列的最后一个节点 pre = 1
            pre = leftNode;
            //待翻转节点的起始节点为后置节点  left = 3->4->5
            leftNode = suc;
            //head 位置为 head = 3->4->5
            head = suc;
        }
        return dummyNode.next;
    }

    // 1->2->null   2->1->null
    public static void reverseNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    //官方解法
    public static ListNode reverseKGroup1(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }
    //翻转过程中将head的下一个节点已经连接到tail的下一个节点
    // 1->2->3    1->3
    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }




    //官方解法优化  少了一个参数
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (null != head){
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if(null == tail){
                    return dummyNode.next;
                }
            }
            ListNode node = pre.next;
            ListNode[] listNodes = reverse2(node, tail);
            head = listNodes[0];
            tail = listNodes[1];
            pre.next = head;
            pre = tail;
        }
        return dummyNode.next;

    }

    public static ListNode[] reverse2(ListNode node ,ListNode tail){
        ListNode pre = tail.next;
        ListNode cur = node;
        while (pre != tail){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail,node};
    }






    public static void print(ListNode node) {
        while (null != node) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode listNode1 = reverseKGroup2(listNode, 2);
        print(listNode1);
    }
}
