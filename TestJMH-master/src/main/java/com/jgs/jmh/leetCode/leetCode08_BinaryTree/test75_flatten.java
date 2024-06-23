package com.jgs.jmh.leetCode.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 14:41
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.*;

/**
 * * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class test75_flatten {
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
    //非递归前序遍历
    public static void flatten(TreeNode root) {
        if(null == root){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<TreeNode> list = new ArrayList<>();
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node);
            if(null != node.right){
                stack.push(node.right);
            }
            if(null != node.left){
                stack.push(node.left);
            }
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i+1);
        }
    }
    //节省空间的前序遍历  一边遍历，一边进行连接
    public static void flatten2(TreeNode root) {
        if(null == root){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(null != pre){
                pre.left = null;
                pre.right = node;
            }
            TreeNode left = node.left, right = node.right;
            if(null != right){
                stack.push(right);
            }
            if(null != left){
                stack.push(left);
            }
            pre = node;
        }
    }
    //对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
    // 然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。
    // 对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
    public static void flatten3(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


    public static void printPre(TreeNode node){
        if(null == node){
            return;
        }
        System.out.println(node.val);
        printPre(node.left);
        printPre(node.right);
    }

    //非递归前序遍历
    public static void pre(TreeNode head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //非递归中序遍历
    public static void in(TreeNode head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }
    //非递归后续遍历
    public static void pos1(TreeNode head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().val + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        flatten2(root);
        pre(root);
        
    }

}
