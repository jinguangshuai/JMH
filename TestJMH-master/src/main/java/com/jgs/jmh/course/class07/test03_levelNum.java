package com.jgs.jmh.course.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/11 - 01 - 11 - 9:38
 * @Description:com.mashibing.jmh.class07
 * @version:1.0
 */
public class test03_levelNum {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int level(Node node) {
        if (null == node) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        //key 节点  value 该节点在哪一层
        Map<Node, Integer> map = new HashMap();
        map.put(node, 1);

        int levelNode = 1;//层数
        int num = 0;//宽度
        int max = 0;


        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int curNode = map.get(poll);

            if (null != poll.left) {
                map.put(poll.left, curNode + 1);
                queue.add(poll.left);
            }

            if (null != poll.right) {
                map.put(poll.right, curNode + 1);
                queue.add(poll.right);
            }
            //如果当前节点所在的层数（curNode）
            //和目前统计层的层数（levelNode）一致，说明当前层没有统计完成
            if (curNode == levelNode) {
                num++;
            } else {
                max = Math.max(max, curNode);
                levelNode++;
                num = 1;
            }

        }
        max = Math.max(max, num);
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        System.out.println(level(head));

    }
}
