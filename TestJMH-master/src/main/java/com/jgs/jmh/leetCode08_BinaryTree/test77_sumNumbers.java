package com.jgs.jmh.leetCode08_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 17:50
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */
public class test77_sumNumbers {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //双队列解决
    public static int sumNumbers1(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<String> queueVal = new LinkedList<>();
        int sum = 0;
        queueNode.add(root);
        queueVal.add(String.valueOf(root.val));
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            String temp = queueVal.poll();
            if (null == node.left && null == node.right) {
                sum += Integer.valueOf(temp);
            }
            if (null != node.left) {
                queueNode.add(node.left);
                queueVal.add(temp + String.valueOf(node.left.val));
            }
            if (null != node.right) {
                queueNode.add(node.right);
                queueVal.add(temp + String.valueOf(node.right.val));
            }
        }
        return sum;
    }

    public static int sumNumbers(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Integer.valueOf(sumNumbers(root,""));
    }

    public static String sumNumbers(TreeNode root, String sum) {
        if (null == root) {
            return "";
        }
        sum += String.valueOf(root.val);
        if (null == root.left && null == root.right) {
            return sum;
        }else {
            String left = sumNumbers(root.left,String.valueOf(root.val));
            String right = sumNumbers(root.right,String.valueOf(root.val));
            return String.valueOf(Integer.valueOf(left) + Integer.valueOf(right));
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(sumNumbers1(root));
        System.out.println(sumNumbers(root));
    }
}
