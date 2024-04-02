package com.jgs.jmh.class08;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/2 - 01 - 02 - 16:00
 * @Description:com.mashibing.jmh.class08
 * @version:1.0
 */
public class test01 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class info{
        public boolean flag;
        public int high;

        public info(boolean flag, int high) {
            this.flag = flag;
            this.high = high;
        }
    }

    public static info process(Node node){
        if(null == node){
            return new info(true,0);
        }
        info left = process(node.left);
        info right = process(node.right);

        int hight = Math.max(left.high,right.high) + 1;
        boolean flag = true;
        if(!left.flag || !right.flag || Math.abs(left.high-right.high)>1){
            flag = false;
        }
        return new info(flag,hight);
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        node.left.left = new Node(4);
        node.left.right = new Node(5);

        node.left.left.left = new Node(6);

        System.out.println(process(node).flag);
    }

}
