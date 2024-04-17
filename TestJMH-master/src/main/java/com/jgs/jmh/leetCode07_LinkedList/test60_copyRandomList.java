package com.jgs.jmh.leetCode07_LinkedList;

import com.jgs.jmh.class06.Code04_CopyListWithRandom;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/17 - 04 - 17 - 15:14
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */
public class test60_copyRandomList {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //map记录老节点与新建节点的关系，利用老节点的对应关系   找寻新建节点之前的关系
    //例如老节点 1.next = 2, 1.random = 4
    //则新节点 1’.next = 2', 1'.random = 4'
    //由于在1'节点的时候我们还没有建立4'，所以需要新建节点，在根据map的k-v键值对的关系确定新节点的关系
    public static Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        //先新建各个节点，根据map确定各个节点的关系
        while (null != cur) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (null != cur) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    //不用hash表 人为构造新节点和随机节点的对应关系
    //迭代+节点拆分
    public static Node copyRandomList1(Node head) {
        if (null == head) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1->1'->2->2'->3->3'
        while (null != cur) {
            // cur 老节点
            //首先记录当前老节点的下一个老节点 1->2   next = 2
            next = cur.next;
            //老节点的下一个节点为新节点   cur.next = 1'   1->1'
            cur.next = new Node(cur.val);
            //新节点的下一个节点为老节点的next节点  1->1'->2
            cur.next.next = next;
            //老节点变为老节点的下一个节点  1->2
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (null != cur) {
            //新拷贝的节点在老节点的后边
            //记录老节点的下一个老节点
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = null == cur.random ? null : cur.random.next;
            cur = next;
        }
        //将新老节点指针进行分离
        Node res = head.next;
        cur = head;
        while (null != cur){
            //记录老节点的下一个老节点
            next = cur.next.next;
            //记录新节点的首位
            curCopy = cur.next;
            //老节点的指针指向下一个老节点
            cur.next = next;
            //新节点的下一位指向   老节点下一个老节点的下一个节点
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    //递归 + hash
    static Map<Node, Node> cachedNode = new HashMap<Node, Node>();
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList2(head.next);
            headNew.random = copyRandomList2(head.random);
        }
        return cachedNode.get(head);
    }

    public static void print(Node node){
        while (null != node) {
            System.out.println(node.val);
            if (null != node.random) {
                System.out.println(node.random.val);
            } else {
                System.out.println("null");
            }
            node = node.next;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;
        Node node = copyRandomList(head);
        Node node1 = copyRandomList1(head);
        Node node2 = copyRandomList2(head);
        print(node);
        print(node1);
        print(node2);
    }
}
