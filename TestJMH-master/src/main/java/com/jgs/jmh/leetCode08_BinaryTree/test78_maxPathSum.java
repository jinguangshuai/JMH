package com.jgs.jmh.leetCode08_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/25 - 04 - 25 - 9:12
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

/**
 * * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class test78_maxPathSum {

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

    //递归
    //当前节点的最大路径： max(自己，自己+左边，自己+右边，自己 + 左边 + 右边）
    //当前节点作为子节点时的贡献：max(自己，自己+左边，自己+右边）
    //后者相对前者，少了左右都存在的情况。因为作为子节点时，一条路如果同时包含左右，根就被包含了2次，不符合题目只出现一次的限制了。
    //维护一个路径的最大值
    static int max = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        maxGain(root);
        return max;
    }
    public static int maxGain(TreeNode root) {
        if (null == root) {
            return 0;
        }
        //如果左节点值大于0，可以采用
        int leftSum = Math.max(maxGain(root.left),0);
        //如果右节点值大于0，可以采用
        int rightSum = Math.max(maxGain(root.right),0);
        //最大值
        int sum = leftSum + rightSum + root.val;
        //判断路径的最大值
        max = Math.max(sum,max);
        return root.val + Math.max(leftSum,rightSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
    }
}
