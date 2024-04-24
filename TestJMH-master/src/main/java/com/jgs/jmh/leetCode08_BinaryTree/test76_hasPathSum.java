package com.jgs.jmh.leetCode08_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 15:54
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */
public class test76_hasPathSum {

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

    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.add(root);
        queueVal.add(root.val);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int temp = queueVal.poll();
            if(null == node.left && null == node.right){
                if(temp == targetSum){
                    return true;
                }
                continue;
            }
            if (null != node.left) {
                queueNode.add(node.left);
                queueVal.add(node.left.val+temp);
            }
            if (null != node.right) {
                queueNode.add(node.right);
                queueVal.add(node.right.val+temp);
            }
        }
        return false;
    }


    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(null == root){
            return false;
        }
        if(null == root.left && null == root.right){
            return root.val == targetSum;
        }
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right,targetSum-root.val);
        return left || right;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(hasPathSum(root, 8));
    }
}
