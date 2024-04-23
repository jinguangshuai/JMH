package com.jgs.jmh.leetCode07_LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/22 - 04 - 22 - 9:00
 * @Description:com.jgs.jmh.leetCode07_LinkedList
 * @version:1.0
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
 * 如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class test67_LRUCache {
    public static class ListNode {
        int key;
        int value;
        ListNode pre;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LRUCache {
        int size = 0;
        int capacity = 0;
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode head, tail;

        public LRUCache(int capacity) {
            size = 0;
            this.capacity = capacity;
            head = new ListNode();
            tail = new ListNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            ListNode node = map.get(key);
            if (null == node) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            ListNode node = map.get(key);
            if (null == node) {
                //如果key不存在。则创建一个新的节点
                ListNode newNode = new ListNode(key, value);
                //添加到map中
                map.put(key,newNode);
                //添加新增节点到头部
                addToHead(newNode);
                size++;
                if(size > capacity){
                    //如果超出容量，则删除双向链表的尾部节点
                    ListNode tail = removeTail();
                    //删除hash表对应的项
                    map.remove(tail.key);
                    size--;
                }
            }else {
                //如果key存在，先通过hash定位，在修改value，并移动到头部
                node.value = value;
                moveToHead(node);
            }
        }

        public void addToHead(ListNode node) {
            //在虚拟节点下边插入一个双向节点
            // -1<->1<->2<->3  -1<->4<->1<->2<->3
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        public void removeNode(ListNode node) {
            //删除指定节点
            //-1<->1<->2<->3   -1<->1<->3
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void moveToHead(ListNode node) {
            //将指定节点 移动到头部，然后删除头部节点
            addToHead(node);
            removeNode(node);
        }

        public ListNode removeTail() {
            //删除尾节点
            //因为tail为虚拟节点，所以删除尾节点为虚拟节点的前置节点
            ListNode res = tail.pre;
            removeNode(res);
            return res;
        }

    }


    public static void print(ListNode node) {
        if (null == node) {
            System.out.println("null");
        }
        while (null != node) {
            System.out.println(node.value);
            node = node.next;
        }
    }
    
}
