package com.jgs.jmh.class08;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/9 - 01 - 09 - 16:20
 * @Description:com.mashibing.jmh.class08
 * @version:1.0
 */
public class test_isBalanced {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class info {
        public boolean flag;
        public int height;

        public info(boolean flag, int height) {
            this.flag = flag;
            this.height = height;
        }
    }

    public static info process(Node head) {
        if (null == head) {
            return new info(true, 0);
        }
        info left = process(head.left);
        info right = process(head.right);
        boolean flag = true;
        if (!left.flag || !right.flag || Math.abs(left.height - right.height) > 1) {
            flag = false;
        }
        return new info(flag, Math.max(left.height,right.height) + 1);
    }

    public static void main(String[] args) {
        Node node = new Node(3);
        node.left = new Node(5);
        node.right = new Node(6);
        node.left.left = new Node(7);
        node.left.right = new Node(8);
        node.left.left.left = new Node(9);
        info process = process(node);
        System.out.println(process.height);
        System.out.println(process.flag);
    }
}
