package com.jgs.jmh.leetCode09_BinaryTreeLevel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/26 - 04 - 26 - 17:06
 * @Description:com.jgs.jmh.leetCode09_BinaryTreeLevel
 * @version:1.0
 */

/**
 * * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class test84_levelOrder {
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
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null != node.right){
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    //深度优先遍历
    //result.size <= level
    //List<Integer> list = new ArrayList<>();
    //list.add(node.val);
    //result.add(list);
    //否则
    //List<Integer> list = result.get(level);
    //list.add(node.val);
    //result.set(level, list);
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    public static void dfs(TreeNode node, int level, List<List<Integer>> result) {
        if (null == node) {
            return;
        }

        if (result.size() <= level) {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            result.add(list);
        } else {
            List<Integer> list = result.get(level);
            list.add(node.val);
            result.set(level, list);
        }
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<List<Integer>> list = levelOrder1(root);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
