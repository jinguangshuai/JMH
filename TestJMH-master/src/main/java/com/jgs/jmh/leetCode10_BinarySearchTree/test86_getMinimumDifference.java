package com.jgs.jmh.leetCode10_BinarySearchTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/26 - 04 - 26 - 17:18
 * @Description:com.jgs.jmh.leetCode10_BinarySearchTree
 * @version:1.0
 */

import com.jgs.jmh.leetCode09_BinaryTreeLevel.test85_zigzagLevelOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 差值是一个正数，其数值等于两值之差的绝对值。
 */
public class test86_getMinimumDifference {

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
    public static int getMinimumDifference(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (null != node.left) {
                queue.add(node.left);
            }
            if (null != node.right) {
                queue.add(node.right);
            }
        }
        if (list.size() == 1) {
            return root.val;
        } else {
            return getMin(list);
        }
    }
    public static int getMin(List<Integer> list) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                result = Math.min(result, Math.abs(list.get(i) - list.get(j)));
            }
        }
        return result;
    }

    //中序遍历
    //最后获取每两个节点之间的差值，去最小的差值
    static int pre = -1;
    static int result = Integer.MAX_VALUE;
    public int getMinimumDifference1(TreeNode root) {
        dfs(root);
        return result;
    }
    public static void dfs(TreeNode node){
        if(null == node){
            return;
        }
        dfs(node.left);
        if(-1 == pre){
            pre = node.val;
        }else{
            result = Math.min(result,Math.abs(node.val - pre));
            pre = node.val;
        }
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        System.out.println(getMinimumDifference(root));
    }


}
