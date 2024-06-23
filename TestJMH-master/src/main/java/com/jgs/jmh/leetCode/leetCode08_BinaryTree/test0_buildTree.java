package com.jgs.jmh.leetCode.leetCode08_BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 9:19
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */
public class test0_buildTree {
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

    //先遍历数组，每个数字构建一个TreeNode放到list里边
    //根据二叉树性质，根节点为i，则左节点为 2*i+1 右节点为2*i+2
    //遍历数组的长度为 list.size()/2 - 1
    public TreeNode intArrayToBTree(int[] arrs) {
        // 判空处理，返回空节点
        if (arrs == null || arrs.length == 0) {
            return new TreeNode();
        }
        // 创建和数组长度相同的集合
        List<TreeNode> nodes = new ArrayList<>(arrs.length);
        // 遍历数组，将数组元素转为集合节点
        for (int obj : arrs) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = obj;
            nodes.add(treeNode);
        }
        //  按照完全二叉树的规则构建，数组中的后半部分元素都是叶子节点，它们没有左右子节点。所以循环只需要处理前半部分的非叶子节点即可。
        //  i < arrs.length/2 - 1  能够将所有左右子节点不为null的元素给遍历出来，剩下最后一个（在左节点上）或者最后两个（在右节点上）
        //  保证循环只在前半部分有效的节点范围内进行迭代，避免处理不必要的叶子节点。
        for (int i = 0; i < arrs.length / 2 - 1; i++) {
            TreeNode node = nodes.get(i);
            // 首先，由于是通过数组构建的二叉树（数组下标从0开始）
            // 其次，由完全二叉树的性质（从1开始计数，左孩子为2i，右孩子为2i  +1 ）  再结合 数组下标0 开始计数 可知：
            // 树的左节点为 2i +1；树的右节点为：2i +2
            node.left = nodes.get(i * 2 + 1);
            node.right = nodes.get(i * 2 + 2);
        }
        // 只有当总节点数是奇数时，最后一个父节点才有右子节点
        int lastPNodeIndex = arrs.length / 2 - 1;
        TreeNode lastPNode = nodes.get(lastPNodeIndex);
        // 左孩子节点
        lastPNode.left = nodes.get(lastPNodeIndex * 2 + 1);
        if (arrs.length % 2 != 0) {
            lastPNode.right = nodes.get(lastPNodeIndex * 2 + 2);
        }
        return nodes.get(0);
    }



    //递归前序遍历
    public static void preByRecursion(TreeNode root){
        if(null == root){
            return;
        }
        System.out.print("前序遍历");
        System.out.print(root.val + " ");
        preByRecursion(root.left);
        preByRecursion(root.right);
    }
    //非递归前序遍历
    public static void preByNoRecursion(TreeNode root){
        if(null == root){
            return;
        }
        System.out.print("前序遍历结果为：");
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val + "  ");
            if(null != node.left){
                stack.push(node.left);
            }
            if(null != node.right){
                stack.push(node.right);
            }
        }
    }

    //递归中序遍历
    public static void inByRecursion(TreeNode root){
        if(null == root){
            return;
        }
        inByRecursion(root.left);
        System.out.print("中序遍历");
        System.out.print(root.val + " ");
        inByRecursion(root.right);
    }
    //非递归中序遍历
    public static void inByNoRecursion(TreeNode root){
        if(null == root){
            return;
        }
        System.out.print("中序遍历结果为：");
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != root){
            if(null != root){
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                System.out.print(root.val + "  ");
                root = root.right;
            }
        }
    }

    //递归后序遍历
    public static void posByRecursion(TreeNode root){
        if(null == root){
            return;
        }
        posByRecursion(root.left);
        posByRecursion(root.right);
        System.out.print("后序遍历");
        System.out.print(root.val + " ");
    }
    //非递归后序遍历
    public static void posByNoRecursion(TreeNode root){
        if(null == root){
            return;
        }
        System.out.print("后序遍历结果为：");
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            stack2.push(node);
            if(null != node.left){
                stack1.push(node.left);
            }
            if(null != node.right){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().val + "  ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        //前序遍历
        preByRecursion(root);
        System.out.println("---------------");
        preByNoRecursion(root);
        System.out.println("---------------");
        //中序遍历
        inByRecursion(root);
        System.out.println("---------------");
        inByNoRecursion(root);
        System.out.println("---------------");
        //后序遍历
        posByRecursion(root);
        System.out.println("---------------");
        posByNoRecursion(root);
        System.out.println("---------------");
    }
}
