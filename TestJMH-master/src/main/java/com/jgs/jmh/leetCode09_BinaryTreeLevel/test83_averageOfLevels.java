package com.jgs.jmh.leetCode09_BinaryTreeLevel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/26 - 04 - 26 - 15:04
 * @Description:com.jgs.jmh.leetCode09_BinaryTreeLevel
 * @version:1.0
 */

/**
 * * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 */
public class test83_averageOfLevels {

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
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //防止int数字越界
            Double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (null != node.left) {
                    queue.add(node.left);
                }
                if (null != node.right) {
                    queue.add(node.right);
                }
                sum += (double) node.val;
            }
            result.add((double) sum / (double) size);
        }
        return result;
    }

    //深度优先遍历
    //sum计算每层的和，count记录每层的个数
    public static List<Double> averageOfLevels1(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<Double> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        dfs(root, 0, sum, count);
        for (int i = 0; i < sum.size(); i++) {
            result.add(sum.get(i) / count.get(i));
        }
        return result;
    }

    public static void dfs(TreeNode node, int level, List<Double> sum, List<Integer> count) {
        if (null == node) {
            return;
        }
        //如果sum的长度小于等于层数，说明还在往下遍历，应该添加数值
        //如果sum的长度大于层数，说明已经遍历到最底层，已经开始往右遍历，应该更新数值
        if (sum.size() <= level) {
            sum.add(1.0 * node.val);
            count.add(1);
        } else {
            sum.set(level, sum.get(level) + node.val);
            count.set(level, count.get(level) + 1);
        }
        dfs(node.left, level + 1, sum, count);
        dfs(node.right, level + 1, sum, count);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<Double> list = averageOfLevels1(root);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
