package com.jgs.jmh.leetCode.leetCode10_BinarySearchTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/28 - 04 - 28 - 9:02
 * @Description:com.jgs.jmh.leetCode10_BinarySearchTree
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
public class test87_kthSmallest {
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

    //中序遍历
    public static int kthSmallest(TreeNode root, int k) {
        if (null == root || k < -1) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        process(root, k, list);
        return list.get(k - 1);
    }

    public static void process(TreeNode node, int k, List<Integer> list) {
        if (null == node) {
            return;
        }
        process(node.left, k, list);
        list.add(node.val);
        process(node.right, k, list);
    }

    //中序遍历
    static int index;
    static int result;
    public static int kthSmallest1(TreeNode root, int k) {
        if (null == root || k < -1) {
            return -1;
        }
        process1(root, k);
        return result;
    }
    public static void process1(TreeNode node, int k) {
        if (null == node) {
            return;
        }
        process1(node.left, k);
        if(++index == k){
            result = node.val;
            return;
        }
        process1(node.right, k);
    }

    //对当前结点 node 进行如下操作：
    //如果 node 的左子树的结点数 left 小于 k−1，则第 k小的元素一定在 node 的右子树中，令 node 等于其的右子结点，k等于 k−left−1，并继续搜索；
    //如果 node 的左子树的结点数 left 等于 k−1，则第 k小的元素即为 node ，结束搜索并返回 node 即可；
    //如果 node 的左子树的结点数 left 大于 k−1则第 k小的元素一定在 node 的左子树中，令 node 等于其左子结点，并继续搜索。
    //中序遍历 记录节点数量
    public static int kthSmallest3(TreeNode root, int k) {
        int leftNodes = countNodes(root.left);
        if(leftNodes < k - 1){  //答案存在右子树中
            return kthSmallest3(root.right,k - leftNodes - 1);
        }else if(leftNodes == k - 1){
            return root.val;
        }else{
            return kthSmallest3(root.left,k);
        }
    }
    //左神递归套路分析左右子树返回信息只需要节点数，因此无需额外定义数据结构
    public static int countNodes(TreeNode root){
        //base case返回0
        if(root == null) return 0;
        //递归处理左右子树并接收返回值
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        //判断分析本层递归返回值的具体值
        return leftNodes + rightNodes + 1;
    }

    public static void main(String[] args) {
        //二叉搜索树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        System.out.println(kthSmallest3(root, 8));
    }
}
