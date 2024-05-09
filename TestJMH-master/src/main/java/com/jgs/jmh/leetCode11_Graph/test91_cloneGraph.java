package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/7 - 05 - 07 - 14:48
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import java.util.*;

/**
 * * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * 测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），
 * 第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 */
public class test91_cloneGraph {
    public static class Node {
        int val;
        List<Node> neighbors;

        public Node() {
            this.val = 0;
            this.neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    //宽度优先遍历
    public static Node cloneGraph1(Node node) {
        if (null == node) {
            return null;
        }
        //key为老节点---value为新建节点
        HashMap<Node, Node> map = new HashMap<>();
        // 将题目给定的节点添加到队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        map.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node old = queue.poll();
            // 遍历该节点的邻居
            for (Node neighbor : old.neighbors) {
                if (!map.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                //map.get(old).neighbors  克隆节点的邻居节点
                map.get(old).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    //深度优先遍历
    static HashMap<Node, Node> map = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }
        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (map.containsKey(node)) {
            return map.get(node);
        }
        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node clone = new Node(node.val, new ArrayList<>());
        // 哈希表存储
        map.put(node, clone);
        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        System.out.println("-----------");
        Node node = cloneGraph1(node1);
        System.out.println(node);

    }
}
