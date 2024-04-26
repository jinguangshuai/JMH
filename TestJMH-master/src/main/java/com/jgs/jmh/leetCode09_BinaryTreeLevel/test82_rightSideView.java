package com.jgs.jmh.leetCode09_BinaryTreeLevel;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/26 - 04 - 26 - 11:18
 * @Description:com.jgs.jmh.leetCode09_BinaryTreeLevel
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class test82_rightSideView {
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

    //宽度优先遍历
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (null != node.left) {
                    queue.add(node.left);
                }
                if (null != node.right) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    //深度优先遍历
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightDfs(root, 0, result);
        return result;
    }
    public static void rightDfs(TreeNode root, int level, List<Integer> result) {
        if (null == root) {
            return;
        }
        if (result.size() <= level) {
            result.add(root.val);
        } else {
            result.set(level, root.val);
        }
        rightDfs(root.left, level + 1,result);
        rightDfs(root.right, level + 1,result);
    }

    //递归求左视图
    public static List<Integer> leftSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        leftDfs(root, 0, result);
        return result;
    }
    public static void leftDfs(TreeNode root, int level, List<Integer> result) {
        if (null == root) {
            return;
        }
        if (result.size() <= level) {
            result.add(root.val);
        }
        leftDfs(root.left, level + 1,result);
        leftDfs(root.right, level + 1,result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<Integer> list = leftSideView1(root);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
