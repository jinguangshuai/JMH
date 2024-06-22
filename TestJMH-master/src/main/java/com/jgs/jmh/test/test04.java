package com.jgs.jmh.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/22 - 06 - 22 - 15:10
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test04 {

    public static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        Node<T> up;
        Node<T> down;

        public Node(T val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.up = null;
            this.down = null;
        }

        public void right(Node<T> newNode) {
        }
    }


    public static Node<Integer> generateNode(int n) {
        if (n <= 0) {
            return null;
        }
        //创建第一个节点
        Node<Integer> head = new Node<>(1);
        //创建第一维链表
        Node<Integer> cur = head;
        //创建第一列
        for (int i = 2; i <= n; i++) {
            Node<Integer> newNode = new Node<>(i);
            cur.right = newNode;
            newNode.left = cur;
            cur = newNode;
        }
        //创建剩余维度
        Node<Integer> preRowCur = head;//当前行
        for (int row = 2; row <= n; row++) {
            //按列创建，因为本列第一个已经创建过了，所以从第二个开始
            Node<Integer> newRowHead = new Node<>(row * n + 1);
            preRowCur.down = newRowHead;
            newRowHead.up = preRowCur;
            cur = newRowHead;
            for (int col = 2; col <= n; col++) {
                //按列创建，因为本列第一个已经创建过了
                Node<Integer> newNode = new Node<>(row * n + col);
                cur.right = newNode;
                newNode.left = cur;
                //每次创建一个都找他的帽子，帽子计算完成后移，以迎接下个col
                preRowCur = preRowCur.right;
                preRowCur.down = newNode;
                newNode.up = preRowCur;
                cur = newNode;
            }
            preRowCur = newRowHead;
        }
        return head;
    }

    public static void print(Node head){
        Node row = head;
        while (null != row){
            Node cur = row;
            while (null != cur){
                System.out.print(String.format("%3d  ",cur.val));
                cur = cur.right;
            }
            System.out.println();
            row = row.down;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println( n + "维度");
        Node node = generateNode(n);
        print(node);
    }
}
