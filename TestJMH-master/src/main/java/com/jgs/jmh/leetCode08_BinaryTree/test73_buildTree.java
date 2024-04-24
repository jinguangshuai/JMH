package com.jgs.jmh.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/23 - 04 - 23 - 9:51
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.HashMap;
import java.util.Stack;

/**
 * * 给定两个整数数组 preorder 和 inorder ，
 * * 其中 preorder 是二叉树的先序遍历，
 * * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class test73_buildTree {

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

    static HashMap<Integer, Integer> inMap = new HashMap<>();

    public static TreeNode buildTree(int[] inorder, int[] posorder) {
        if (null == inorder || null == posorder || inorder.length != posorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildByInAndPos(inorder, posorder, 0, inorder.length - 1, 0, posorder.length - 1);
    }

    public static TreeNode buildByInAndPos(int[] inorder, int[] posorder, int inStart, int inEnd, int posStart, int posEnd) {
        if (inStart > inEnd || posStart > posEnd) {
            return null;
        }
        int rootVal = posorder[posEnd];
        TreeNode root = new TreeNode(rootVal);
        int index = inMap.get(rootVal);
        // 得到左子树中的节点数量
        int size = index - inStart;
        //构建左子树
        //左子树：后续遍历为 起始到  根节点+左子树的长度在减去1     中序遍历为第一个到根节点前一位的位置
        root.left = buildByInAndPos(inorder, posorder, inStart, index - 1, posStart, posStart + size - 1);
        //构建右子树
        //右子树：后续遍历为左子树后一个到最后一个       中序遍历为根节点后一个到最后
        root.right = buildByInAndPos(inorder, posorder, index + 1, inEnd, posStart + size, posEnd - 1);
        return root;
    }

    //迭代
    // 后续反向遍历   左右中 -> 中右左    中序反向遍历 左中右 -> 右中左
    //后续序列对应节点入栈序列，中序序列对应节点第一次出栈序列。
    // 迭代法中的index指向的元素是当前应该出栈元素，若指向元素不等于栈顶元素，说明还需要遍历右子树（即后序序列向前遍历）；
    // 若指向元素等于栈顶元素，说明右子树已遍历完全（即后序序列遍历完右子树部分），开始遍历栈顶元素的左子树。如此遍历一遍后序和中序序列，即可构建一颗二叉树。
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (null == inorder || null == postorder || inorder.length != postorder.length) {
            return null;
        }
        int n = postorder.length;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[n - 1]);
        stack.push(root);
        int inIndex = inorder.length - 1;
        for (int i = n - 2; i >= 0; i--) {
            int postVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inIndex]) {
                node.right = new TreeNode(postVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]) {
                    node = stack.pop();
                    inIndex--;
                }
                node.left = new TreeNode(postVal);
                stack.push(node.left);
            }
        }
        return root;
    }


    //前序打印二叉树
    public static void printPre(TreeNode node) {
        if (null == node) {
            return;
        }
        System.out.println(node.val);
        printPre(node.left);
        printPre(node.right);
    }

    //中序打印二叉树
    public static void printMid(TreeNode node) {
        if (null == node) {
            return;
        }
        printMid(node.left);
        System.out.println(node.val);
        printMid(node.right);
    }

    //后续打印二叉树
    public static void printSuc(TreeNode node) {
        if (null == node) {
            return;
        }
        printSuc(node.left);
        printSuc(node.right);
        System.out.println(node.val);
    }

    public static void main(String[] args) {
//        int[] in = {9, 3, 15, 20, 7};
//        int[] pos = {9, 15, 7, 20, 3};
        int[] pre = {3, 9, 8, 5, 4, 10, 20, 15, 7};
        int[] in = {4, 5, 8, 10, 9, 3, 15, 20, 7};
        int[] pos = {4, 5, 10, 8, 9, 15, 7, 20, 3};
        TreeNode treeNode = buildTree1(in, pos);
        System.out.println("前序--------------");
        printPre(treeNode);
        System.out.println("中序--------------");
        printMid(treeNode);
        System.out.println("后序--------------");
        printSuc(treeNode);
    }
}
