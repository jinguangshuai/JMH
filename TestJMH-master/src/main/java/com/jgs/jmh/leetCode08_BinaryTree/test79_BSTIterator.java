package com.jgs.jmh.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/25 - 04 - 25 - 11:06
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * <p>
 * *  boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * <p>
 * * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 */
public class test79_BSTIterator {
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


    public class BSTIterator {
        List<Integer> list = new ArrayList<>();
        int index = 0;

        public BSTIterator(TreeNode root) {
            //非递归添加数据
//            if (null == root) {
//                return;
//            }
//            Stack<TreeNode> stack = new Stack<>();
//            while (!stack.isEmpty() || null != root) {
//                if (null != root) {
//                    stack.push(root);
//                    root = root.left;
//                } else {
//                    root = stack.pop();
//                    list.add(root.val);
//                    root = root.right;
//                }
//            }
            //递归方法添加数据
            addtWithRecurison(root);
        }

        public int next() {
            index++;
            return list.get(index - 1);
        }

        public boolean hasNext() {
            if (index < list.size()) {
                return true;
            } else {
                return false;
            }
        }

        public void addtWithRecurison(TreeNode node) {
            if (null == node) {
                return;
            }
            addtWithRecurison(node.left);
            list.add(node.val);
            addtWithRecurison(node.right);
        }
    }

    //官方迭代方法
    public class BSTIterator1 {
        TreeNode cur;
        Stack<TreeNode> stack;

        public BSTIterator1(TreeNode root) {
            cur = root;
            stack = new Stack<>();
        }

        public int next() {
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int result = cur.val;
            cur = cur.right;
            return result;
        }

        public boolean hasNext() {
            //当可以继续进行中序遍历，则说明还有子节点，否则返回false
            return null != cur || !stack.isEmpty();
        }

    }

    public static void printWithRecurison(TreeNode node) {
        if (null == node) {
            return;
        }
        printWithRecurison(node.left);
        System.out.println(node.val);
        printWithRecurison(node.right);
    }

    public static void printNoRecursion(TreeNode node) {
        if (null == node) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != node) {
            if (null != node) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.println(node.val);
                node = node.right;
            }
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
        printWithRecurison(root);
        System.out.println("------------");
        printNoRecursion(root);
    }
}
