package com.jgs.jmh.leetCode10_BinarySearchTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/28 - 04 - 28 - 10:17
 * @Description:com.jgs.jmh.leetCode10_BinarySearchTree
 * @version:1.0
 */

import com.jgs.jmh.class10.Node;

import java.util.Stack;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class test88_isValidBST {

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

    //递归方法1  判断左右子树的最大最小值,判断当前值是否大于左子树的最大值，当前值是否小于右子树的最小值
    public static boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        NodeInfo process = process(root);
        return process.flag;
    }

    public static NodeInfo process(TreeNode node) {
        if (null == node) {
            return null;
        }
        NodeInfo leftInfo = process(node.left);
        NodeInfo rightInfo = process(node.right);
        int min = node.val;
        int max = node.val;
        boolean isBST = false;
        if (null != leftInfo) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (null != rightInfo) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        if ((null == leftInfo ? true : leftInfo.flag && (leftInfo.max < node.val))
                && (null == rightInfo ? true : rightInfo.flag && (rightInfo.min > node.val))) {
            isBST = true;
        }
        return new NodeInfo(isBST, min, max);
    }

    public static class NodeInfo {
        boolean flag;
        int min;
        int max;

        public NodeInfo(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }
    }

    //递归方式根据二叉搜索树的定义，可以利用中序遍历进行判断
    static long pre = Long.MIN_VALUE;

    public static boolean isValidBST2(TreeNode root) {
        if (null == root) {
            return true;
        }
        boolean left = isValidBST2(root.left);
        if (root.val <= pre || !left) {
            return false;
        }
        pre = root.val;
        boolean right = isValidBST2(root.right);
        return left && right;
    }

    //非递归方式根据二叉搜索树的定义，可以利用中序遍历进行判断
    public static boolean isValidBST3(TreeNode root) {
        if (null == root) {
            return true;
        }
        long pre = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != root) {
            if (null != root) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= pre) {
                    return false;
                } else {
                    pre = root.val;
                }
                root = root.right;
            }
        }
        return true;
    }

    //官方递归方式  利用二叉搜索树的性质判断
    public static boolean isValidBST4(TreeNode root) {
        return process2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean process2(TreeNode root, long min, long max) {
        if (null == root) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        //在递归调用左子树时，我们需要把上界改为node.val因为左子树里所有节点的值均小于它的根节点的值。
        boolean left = process2(root.left, min, root.val);
        //同理递归调用右子树时,我们需要把下界改为node.val因为右子树里所有节点的值均大于它的根节点的值。
        boolean right = process2(root.right, root.val, max);
        return left && right;

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
        System.out.println(isValidBST4(root));
    }

}
