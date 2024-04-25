package com.jgs.jmh.leetCode08_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 17:50
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

/**
 * * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
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
        return Integer.parseInt(sumNumbers(root, ""));
    }

    public static String sumNumbers(TreeNode root, String sum) {
        if (null == root) {
            return "";
        }
        sum += String.valueOf(root.val);
        if (null == root.left && null == root.right) {
            return sum;
        } else {
            String left = sumNumbers(root.left, sum);
            String right = sumNumbers(root.right, sum);
            int leftValue = 0, rightValue = 0;
            if (!"".equals(left)) {
                leftValue = Integer.parseInt(left);
            }
            if (!"".equals(right)) {
                rightValue = Integer.parseInt(right);
            }
            return String.valueOf(leftValue + rightValue);
        }
    }

    //回溯解决
    //目标是寻找每一个叶子结点，在寻找过程中维护一个路径数字num，一个路径数字总和sum。dfs到叶子结点时，
    // 立即累计num到sum。当前结点的子空间探索结束后，num需要撤销node带来的贡献，撤销动作为num = (num - node.val) / 10。
    //在典型的回溯问题中，探索一个结点的子空间通常以for（多叉树回溯形式）来完成，
    // 但本题的树是二叉树，且回溯条件是左右儿子皆空（即当前结点为叶子结点），
    // 因此将dfs(node.left)与dfs(node.right)依次写出即相当于完成了当前node子空间的探索。
    static int sum = 0, num = 0;

    public static int sumNumbers2(TreeNode root) {
        dfs(root);
        return sum;
    }

    private static void dfs(TreeNode node) {
        if (node == null) return;
        num = num * 10 + node.val;
        if (node.left == null && node.right == null) {
            sum += num;
        }
        dfs(node.left);
        dfs(node.right);
        num = (num - node.val) / 10;
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
