package com.jgs.jmh.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/22 - 04 - 22 - 15:22
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import com.sun.org.apache.regexp.internal.RE;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class test69_isSameTree {

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
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if ((null == p && null != q) || (null != p && null == q)) {
            return false;
        } else if (null == p && null == q) {
            return true;
        }
        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();
        pQueue.add(p);
        qQueue.add(q);
        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();
            if (pNode.val != qNode.val) {
                return false;
            }
            if (null != pNode.left && null != qNode.left) {
                if (pNode.left.val != qNode.left.val) {
                    return false;
                } else {
                    pQueue.add(pNode.left);
                    qQueue.add(qNode.left);
                }
            } else if ((null == pNode.left && null != qNode.left) || (null != pNode.left && null == qNode.left)) {
                return false;
            }
            if (null != pNode.right && null != qNode.right) {
                if (pNode.right.val != qNode.right.val) {
                    return false;
                } else {
                    pQueue.add(pNode.right);
                    qQueue.add(qNode.right);
                }
            } else if ((null == pNode.right && null != qNode.right) || (null != pNode.right && null == qNode.right)) {
                return false;
            }
        }
        if (pQueue.isEmpty() && qQueue.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //深度优先遍历
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if ((null == p && null != q) || (null != p && null == q)) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }else {
            return isSameTree2(p.left,q.left) && isSameTree2(p.right,q.right);
        }
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(0);
        p.left = new TreeNode(1);
        p.right = new TreeNode(2);
        TreeNode q = new TreeNode(0);
        q.left = new TreeNode(1);
        q.right = new TreeNode(2);
        boolean sameTree = isSameTree1(p, q);
        System.out.println(sameTree);
    }
}
