package com.jgs.jmh.course.class08;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/9 - 01 - 09 - 16:46
 * @Description:com.mashibing.jmh.class08
 * @version:1.0
 */
public class test_isFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class info {
        public int num;
        public int level;

        public info(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    public static info process(Node node) {
        if (null == node) {
            return new info(0,0);
        }
        info left = process(node.left);
        info right = process(node.right);
        int num = left.num + right.num + 1;
        int level = Math.max(left.level,right.level) + 1;
        return new info(num,level);
    }

    public static void main(String[] args) {
        Node node = new Node(3);
        node.left = new Node(5);
        node.right = new Node(6);
        node.left.left = new Node(7);
        node.left.right = new Node(8);
        node.right.left = new Node(9);
        node.right.right = new Node(10);
//        node.left.left.left = new Node(9);
//        node.left.left.right = new Node(10);
        info process = process(node);
        System.out.println(process.num);
        System.out.println(process.level);
        if(process.num == ((1<<process.level)-1)){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }

}
