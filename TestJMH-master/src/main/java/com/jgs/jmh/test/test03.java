package com.jgs.jmh.test;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/20 - 06 - 20 - 18:33
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test03 {

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void reverse(Node node, int k) {
        if (null == node) {
            return;
        }
        Node cur = node;
        int count = 1;
        while (null != cur.next) {
            count++;
            cur = cur.next;
        }

        int add = count - k % count;
        if (add == count) {
            print(node);
        }
        cur.next = node;
        while (add > 0) {
            cur = cur.next;
            add--;
        }
        Node result = cur.next;
        cur.next = null;
        print(result);
    }

    public static void print(Node head) {
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        reverse(node,2);
    }
}
