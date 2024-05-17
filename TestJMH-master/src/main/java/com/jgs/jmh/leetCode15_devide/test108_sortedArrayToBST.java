package com.jgs.jmh.leetCode15_devide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/15 - 05 - 15 - 17:23
 * @Description:com.jgs.jmh.leetCode15_devide
 * @version:1.0
 */
public class test108_sortedArrayToBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //自己递归
    public static TreeNode sortedArrayToBST1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return dfs1(list);
    }

    public static TreeNode dfs1(List<Integer> list) {
        if (list.size() == 0) return null;
        if (list.size() == 1) return new TreeNode(list.get(0));
        if (list.size() == 2) {
            TreeNode root = new TreeNode(list.get(1));
            root.left = new TreeNode(list.get(0));
            return root;
        }
        TreeNode root = new TreeNode(list.get(list.size() / 2));
        root.left = dfs1(list.subList(0, list.size() / 2));
        root.right = dfs1(list.subList(list.size() / 2 + 1, list.size()));
        return root;
    }

    //中序遍历，总是选择中间位置左边的数字作为根节点
    public static TreeNode sortedArrayToBST2(int[] nums) {
        return dfs2(nums, 0, nums.length - 1);
    }

    public static TreeNode dfs2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs2(nums, left, mid - 1);
        root.right = dfs2(nums, mid + 1, right);
        return root;
    }

    //中序遍历，总是选择中间位置右边的数字作为根节点
    public static TreeNode sortedArrayToBST3(int[] nums) {
        return dfs3(nums, 0, nums.length - 1);
    }
    public static TreeNode dfs3(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs3(nums, left, mid - 1);
        root.right = dfs3(nums, mid + 1, right);
        return root;
    }

    //中序遍历，总是选择中间位置任意一边的数字作为根节点
    public static TreeNode sortedArrayToBST4(int[] nums) {
        return dfs4(nums, 0, nums.length - 1);
    }

    public static TreeNode dfs4(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //总是选择中间位置任意一边的数字作为根节点
        int mid = (left + right + new Random().nextInt(2)) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs4(nums, left, mid - 1);
        root.right = dfs4(nums, mid + 1, right);
        return root;
    }


    public static void midPrint(TreeNode node) {
        if (null == node) {
            return;
        }
        midPrint(node.left);
        System.out.println(node.val);
        midPrint(node.right);
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        TreeNode treeNode = sortedArrayToBST1(nums);
        midPrint(treeNode);
    }


}
