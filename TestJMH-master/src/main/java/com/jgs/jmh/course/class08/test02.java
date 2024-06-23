package com.jgs.jmh.course.class08;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/2 - 01 - 02 - 17:01
 * @Description:com.mashibing.jmh.class08
 * @version:1.0
 */
public class test02 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class info {
        public int distance;
        public int high;

        public info(int distance, int high) {
            this.distance = distance;
            this.high = high;
        }
    }

    public static info process(Node node) {
        if (null == node) {
            return new info(0, 0);

        }

        info left = process(node.left);
        info right = process(node.right);
        int high = Math.max(left.high, right.high) + 1;
        int distance = Math.max(Math.max(left.distance, right.distance), left.high + right.high + 1);
        return new info(distance,high);
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        node.left.left = new Node(4);
        node.left.right = new Node(5);

        node.left.left.left = new Node(6);
        node.left.left.right = new Node(7);

        System.out.println(process(node).distance);
    }

}
