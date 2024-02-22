package com.mashibing.jmh.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/11 - 01 - 11 - 9:25
 * @Description:com.mashibing.jmh.class07
 * @version:1.0
 */
public class test02_level {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static void level(Node node){
        if(null == node){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            if(null!=poll.left){
                queue.add(poll.left);
            }
            if(null!=poll.right){
                queue.add(poll.right);
            }
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        level(head);
        System.out.println("========");
    }


}
