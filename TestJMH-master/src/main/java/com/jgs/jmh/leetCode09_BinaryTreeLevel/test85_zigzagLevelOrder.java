package com.jgs.jmh.leetCode09_BinaryTreeLevel;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/26 - 04 - 26 - 17:09
 * @Description:com.jgs.jmh.leetCode09_BinaryTreeLevel
 * @version:1.0
 */

import java.util.*;

/**
 * * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class test85_zigzagLevelOrder {
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
    //官方解法
    //宽度优先遍历 双端队列，节省翻转队列的消耗
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 如果为true，则从左往右，如果为false，则从右往左
        boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            //创建双端队列
            Deque<Integer> list = new LinkedList<>();
            for(int i = 0; i< size;i++){
                TreeNode node = queue.poll();
                if(flag){
                    list.offerLast(node.val);
                }else{
                    list.offerFirst(node.val);
                }
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null != node.right){
                    queue.add(node.right);
                }
            }
            result.add(new LinkedList<>(list));
            flag = !flag;
        }
        return result;
    }

    //宽度优先遍历
    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        //如果为true，则从左往右，如果为false，则从右往左
        boolean flag = true;
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
            if(flag){
                flag = false;
            }else{
                list = reverse(list);
                flag = true;
            }
            result.add(list);
        }
        return result;
    }

    //深度优先遍历
    public static List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        dfs(root,0,result);
        // 如果为true，则从左往右，如果为false，则从右往左
        boolean flag = true;
        for(int i = 0; i< result.size();i++){
            if(flag){
                flag = false;
            }else{
                result.set(i,reverse(result.get(i)));
                flag = true;
            }
        }
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

    public static List<Integer> reverse(List<Integer> list){
        List<Integer> result = new ArrayList<>();
        if(list.isEmpty()){
            return result;
        }
        for(int i = list.size()-1; i>=0; i--){
            result.add(list.get(i));
        }
        return result;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<List<Integer>> list = zigzagLevelOrder1(root);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
