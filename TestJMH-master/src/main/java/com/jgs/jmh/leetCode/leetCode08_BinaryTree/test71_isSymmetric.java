package com.jgs.jmh.leetCode.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/23 - 04 - 23 - 8:56
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class test71_isSymmetric {
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

    //深度优先遍历
    public static boolean isSymmetric1(TreeNode root) {
        if (null == root) {
            return true;
        }
        return checkDFS(root, root);
    }

    public static boolean checkDFS(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) {
            return true;
        } else if (null == root1 || null == root2) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }
        return checkDFS(root1.left, root2.right) && checkDFS(root1.right, root2.left);
    }

    //宽度优先遍历
    public static boolean isSymmetric2(TreeNode root) {
        if (null == root) {
            return true;
        }
        return checkBFS(root, root);
    }

    public static boolean checkBFS(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root1);
        queue2.add(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode poll1 = queue1.poll();
            TreeNode poll2 = queue2.poll();
            if(null != poll1.left){
                queue1.add(poll1.left);
                if(null != poll2.right){
                    queue2.add(poll2.right);
                    if(poll1.left.val != poll2.right.val){
                        return false;
                    }
                }else {
                    return false;
                }
            }

            if(null != poll1.right){
                queue1.add(poll1.right);
                if(null != poll2.left){
                    queue2.add(poll2.left);
                    if(poll1.right.val != poll2.left.val){
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    //官方宽度优先遍历版本，只有一个额外的空间变量
    public static boolean isSymmetric3(TreeNode root) {
        if (null == root) {
            return true;
        }
        return checkBFS1(root, root);
    }
    public static boolean checkBFS1(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        queue.add(root2);
        while (!queue.isEmpty()){
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();
            if(null == p && null == q){
                continue;
            }
            if((null == p || null == q) || (p.val != q.val)){
                return false;
            }

            queue.add(p.left);
            queue.add(q.right);

            queue.add(p.right);
            queue.add(q.left);
        }
        return true;
    }




    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric1(root));
        System.out.println(isSymmetric2(root));
        System.out.println(isSymmetric3(root));
    }
}
