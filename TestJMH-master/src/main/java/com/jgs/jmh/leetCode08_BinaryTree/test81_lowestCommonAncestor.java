package com.jgs.jmh.leetCode08_BinaryTree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/25 - 04 - 25 - 17:39
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

/**
 * * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class test81_lowestCommonAncestor {
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

    //递归解决
    //公共祖先 左子树 右子树 根节点
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root,p,q).ans;
    }
    public static NodeInfo process(TreeNode root, TreeNode pNode, TreeNode qNode) {
        if (null == root) {
            return new NodeInfo(null,false,false);
        }
        NodeInfo leftInfo = process(root.left, pNode, qNode);
        NodeInfo rightInfo = process(root.right, pNode, qNode);
        //公共祖先 左子树 右子树 根节点
        boolean findP = pNode == root || leftInfo.pFlag || rightInfo.pFlag;
        boolean findQ = qNode == root || leftInfo.qFlag || rightInfo.qFlag;
        TreeNode ans = null;
        if(null != leftInfo.ans){
            ans = leftInfo.ans;
        }
        if(null != rightInfo.ans){
            ans = rightInfo.ans;
        }
        if(null == ans){
            if(findP && findQ){
                ans = root;
            }
        }
        return new NodeInfo(ans,findP,findQ);
    }
    public static class NodeInfo {
        TreeNode ans;
        boolean pFlag;
        boolean qFlag;
        public NodeInfo(TreeNode ans, boolean pFlag, boolean qFlag) {
            this.ans = ans;
            this.pFlag = pFlag;
            this.qFlag = qFlag;
        }
    }

    //暴力解决  存储p的所有公共祖先，存储q的所有公共祖先
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(null == root){
            return null;
        }
        HashMap<TreeNode,TreeNode> map = new HashMap<>();
        //root的祖先为null
        map.put(root,null);
        //获取所有节点的祖先
        //例如 [1 2 3 4 5 6 7]  （2,1）（4,2）（5,2）（3,1）（6,3）（7,3）
        // p = 2 q = 7
        fillMap(root,map);
        HashSet<TreeNode> pSet = new HashSet<>();
        // p的祖先也可以为自身
        pSet.add(p);
        TreeNode cur = p;
        while (map.get(cur)!=null){
            cur = map.get(cur);
            pSet.add(cur);
        }
        //set此时为 1
        cur = q;
        while (!pSet.contains(cur)){
            cur = map.get(cur);
        }
        return cur;
    }
    public static void fillMap(TreeNode root, HashMap<TreeNode,TreeNode> map){
        if(null != root.left){
            map.put(root.left,root);
            fillMap(root.left,map);
        }
        if(null != root.right){
            map.put(root.right,root);
            fillMap(root.right,map);
        }
    }
    //递归
    //root == p  || root == q判断
    //获取pq的root.left        获取pq的root.right
    //left right 如果都不为空，则根节点为 root
    //如果left为空，则为right
    //如果left不为空，则反馈left
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        if(left == null) return right;
        return left;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        TreeNode treeNode = lowestCommonAncestor2(root, root.left, root.right.right);
        System.out.println(treeNode.val);
    }
}
