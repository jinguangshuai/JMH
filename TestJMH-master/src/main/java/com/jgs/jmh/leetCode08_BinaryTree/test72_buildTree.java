package com.jgs.jmh.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/23 - 04 - 23 - 9:51
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * * 给定两个整数数组 preorder 和 inorder ，
 * * 其中 preorder 是二叉树的先序遍历，
 * * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class test72_buildTree {

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

    //递归解决  可优化寻找中序遍历根节点的方式，利用map去获取
    //(1)只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。
    //(2)由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder) {
            return null;
        }
        return buildByPreAndIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildByPreAndIn(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int index = 0;
        //在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，
        //但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        // 得到左子树中的节点数目
        int size = index - inStart;
        //左子树：前序遍历左子树为根节点的后一位（preStart + 1）到根节点+左子树的长度（preStart + size）    中序遍历为第一个节点（inStart）到根节点前一位的位置（index - 1）
        root.left = buildByPreAndIn(preorder, inorder, preStart + 1, preStart + size, inStart, index - 1);
        //右子树：前序遍历左子树为左子树后一个（preStart + size + 1）到最后一个（preEnd）   中序遍历为根节点后一个（index + 1）到最后（inEnd）
        root.right = buildByPreAndIn(preorder, inorder, preStart + size + 1, preEnd, index + 1, inEnd);
        return root;
    }


    //递归解决  可优化寻找中序遍历根节点的方式，利用map去获取
    //(1)只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。
    //(2)由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildByPreAndIn1(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildByPreAndIn1(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int index = map.get(rootVal);
        // 得到左子树中的节点数目
        int size = index - inStart;
        //左子树：前序遍历左子树为根节点的后一位（preStart + 1）到根节点+左子树的长度（preStart + size）    中序遍历为第一个节点（inStart）到根节点前一位的位置（index - 1）
        root.left = buildByPreAndIn1(preorder, inorder, preStart + 1, preStart + size, inStart, index - 1);
        //右子树：前序遍历左子树为左子树后一个（preStart + size + 1）到最后一个（preEnd）   中序遍历为根节点后一个（index + 1）到最后（inEnd）
        root.right = buildByPreAndIn1(preorder, inorder, preStart + size + 1, preEnd, index + 1, inEnd);
        return root;
    }

    //迭代
    //本质是在模拟出入栈的过程
    //前序序列对应节点入栈序列，中序序列对应节点第一次出栈序列（第二次出栈是后序遍历才需要的，前序中序迭代法可以直接略过这次出栈）。
    // 迭代法中的index指向的元素是当前应该出栈元素，若指向元素不等于栈顶元素，说明还需要遍历左子树（即前序序列向前遍历）；
    // 若指向元素等于栈顶元素，说明左子树已遍历完全（即前序序列遍历完左子树部分），开始遍历栈顶元素的右子树。如此遍历一遍前序和中序序列，即可构建一颗二叉树。
    public static TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    //利用迭代
    public static TreeNode buildTree4(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int inIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preVal = preorder[i];
            TreeNode node = stack.peek();
            if(node.val != inorder[inIndex]){
                node.left = new TreeNode(preVal);
                stack.push(node.left);
            }else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]){
                    node = stack.pop();
                    inIndex++;
                }
                node.right = new TreeNode(preVal);
                stack.push(node.right);
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
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree4(pre, in);
        System.out.println("前序--------------");
        printPre(treeNode);
        System.out.println("中序--------------");
        printMid(treeNode);
        System.out.println("后序--------------");
        printSuc(treeNode);
    }
}
