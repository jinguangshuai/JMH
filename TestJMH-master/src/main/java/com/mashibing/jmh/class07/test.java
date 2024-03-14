package com.mashibing.jmh.class07;

import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2023/10/11 - 10 - 11 - 16:15
 * @Description:com.mashibing.jmh.class07
 * @version:1.0
 */
public class test {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public void pre(Node head) {
        if (null != head) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value);
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public void in(Node head) {
        if (null != head) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || null != head) {
                if (null != head) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }

    public void pos(Node head) {
        if (null != head) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (null != c.left && head != c.right && head != c.left) {
                    stack.push(c.left);
                    c = c.left;
                } else if (head != c.right && null != c.right) {
                    stack.push(c.right);
                    c = c.right;
                } else {
                    System.out.println(stack.pop().value);
                    head = c;
                }
            }

        }

    }

}
