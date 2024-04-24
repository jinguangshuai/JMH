package com.jgs.jmh.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/22 - 04 - 22 - 16:05
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class test70_invertTree {

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
    public static TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    //宽度优先遍历
    public static TreeNode invertTree2(TreeNode root) {
        if(null == root){
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(null != node.right){
                stack.add(node.right);
            }
            if(null != node.left){
                stack.add(node.left);
            }
        }
        return root;
    }
    //非递归方式的深度优先遍历
    public static TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = stack.pop();
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return root;
    }



    //前序打印二叉树
    public static void printPre(TreeNode node){
        if(null == node){
//            System.out.println("null");
            return;
        }
        System.out.println(node.val);
        printPre(node.left);
        printPre(node.right);
    }

    //中序打印二叉树
    public static void printMid(TreeNode node){
        if(null == node){
//            System.out.println("null");
            return;
        }
        printMid(node.left);
        System.out.println(node.val);
        printMid(node.right);
    }

    //后续打印二叉树
    public static void printSuc(TreeNode node){
        if(null == node){
//            System.out.println("null");
            return;
        }
        printSuc(node.left);
        printSuc(node.right);
        System.out.println(node.val);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.left = new TreeNode(6);
        p.right.right = new TreeNode(7);
        TreeNode treeNode = invertTree2(p);
        printMid(treeNode);


    }


}
