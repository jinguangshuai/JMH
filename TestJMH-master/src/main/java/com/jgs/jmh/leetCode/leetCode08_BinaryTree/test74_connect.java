package com.jgs.jmh.leetCode.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/24 - 04 - 24 - 9:28
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.*;

/**
 * * 给定一个二叉树：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 */
public class test74_connect {
    public static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    //BFS
    //利用队列记录每行的数字，根据队列的大小进行判断
    //比如第三行有四个数字，则size = 4 ，queue.poll().next = queue.peek;
    //if（size >1）,则证明队列至少有两个数字，则数字的下一个为queue.peek(),
    //否则size = 1，则证明当前行只剩下一个数字，则该数字的next为null
    public static Node connect(Node root) {
        if (null == root) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        root.next = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size;
            while (count > 0) {
                Node node = queue.poll();
                if (null != queue.peek() && count > 1) {
                    node.next = queue.peek();
                } else {
                    node.next = null;
                }
                if (null != node.left) {
                    queue.add(node.left);
                }
                if (null != node.right) {
                    queue.add(node.right);
                }
                count--;
            }
        }
        return root;
    }

    //节省空间复杂度
    //利用指针串联每一层的节点
    public static Node connect1(Node root) {
        if (root == null)
            return root;
        //cur我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            //遍历当前层的时候，为了方便操作在下一
            //层前面添加一个哑结点（注意这里是访问
            //当前层的节点，然后把下一层的节点串起来）
            Node dummy = new Node(0);
            //pre表示访下一层节点的前一个节点
            Node pre = dummy;
            //然后开始遍历当前层的链表
            while (cur != null) {
                if (cur.left != null) {
                    //如果当前节点的左子节点不为空，就让pre节点
                    //的next指向他，也就是把它串起来
                    pre.next = cur.left;
                    //然后再更新pre
                    pre = pre.next;
                }
                //同理参照左子树
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                //继续访问这样行的下一个节点
                cur = cur.next;
            }
            //把下一层串联成一个链表之后，让他赋值给cur，
            //后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }

    //深度优先遍历
    static List<Node> list = new ArrayList<>();
    public static Node connect3(Node root) {
        dfs(root,0);
        return root;
    }
    public static void dfs(Node root, int level) {
        if (null == root) {
            return;
        }
        //level大于list的长度，说明未遍历到底部，需要继续添加元素
        if(list.size()<= level){
            list.add(root);
        }else {
            //判断当前元素与数组存储元素是否相同，如果不同，则右指针指向当前元素
            if (list.get(level) != root) {
                list.get(level).next = root;
            }
            //更新数组当前元素
            list.set(level, root);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }


    //官方解法
    static Node last = null, nextStart = null;

    public static Node connect2(Node root) {
        if (null == root) {
            return null;
        }
        Node start = root;
        while (null != start) {
            last = null;
            nextStart = null;
            for (Node p = start; null != p; p = p.next) {
                if (null != p.left) {
                    help(p.left);
                }
                if (null != p.right) {
                    help(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public static void help(Node p) {
        if (null != last) {
            last.next = p;
        }
        if (null == nextStart) {
            nextStart = p;
        }
        last = p;
    }


    //横向打印
    public static void print(Node node) {
        if (null == node) {
            System.out.println("null");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node node1 = queue.poll();
            if (null == node1.next) {
                System.out.println(node1.val + "-------" + "null");
            } else {
                System.out.println(node1.val + "-------" + node1.next.val);
            }
            if (null != node1.left) {
                queue.add(node1.left);
            }
            if (null != node1.right) {
                queue.add(node1.right);
            }
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
//        node.right.left = new Node(6);
        node.right.right = new Node(7);
        Node connect = connect3(node);
        print(connect);


    }
}
