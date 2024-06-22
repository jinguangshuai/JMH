package com.jgs.jmh.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/22 - 06 - 22 - 16:44
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
//构建三叉数
public class test05 {

    public static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        public Node() {

        }

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public static void generate(Node node, int k) {
        int count = 1;
        int val = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node left = new Node(++val);
            left.parent = cur;
            cur.left = left;
            count++;
            queue.add(left);

            Node right = new Node(++val);
            right.parent = cur;
            cur.right = right;
            count++;
            queue.add(right);
            if (count == Math.pow(2, k - 1) + 1) {
                break;
            }
        }
    }

    public static void dfs(Node node) {
        if (null == node) {
            return;
        }
        System.out.println(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    public static void print(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.val);
            if (null != cur.left) {
                queue.add(cur.left);
            }
            if (null != cur.right) {
                queue.add(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        generate(node, 4);
        print(node);
        System.out.println("----------");
        dfs(node);
    }
}
