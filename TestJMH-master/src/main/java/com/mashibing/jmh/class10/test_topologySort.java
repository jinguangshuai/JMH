package com.mashibing.jmh.class10;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/31 - 01 - 31 - 15:37
 * @Description:com.mashibing.jmh.class10
 * @version:1.0
 */
public class test_topologySort {

    public List<Node> topologySort(Graph graph) {
        HashMap<Node, Integer> hashMap = new HashMap<>();
        List<Node> result = new ArrayList<>();
        Queue<Node> list = new LinkedList<>();
        if (null != graph) {
            for (Node node : graph.nodes.values()) {
                hashMap.put(node, node.in);
                if (0 == node.in) {
                    list.add(node);
                }
            }
        }
        while (!list.isEmpty()) {
            Node cur = list.poll();
            result.add(cur);
            if (null != cur.nexts && cur.nexts.size() > 0) {
                for (Node node : cur.nexts) {
                    hashMap.put(node, hashMap.get(node) - 1);
                    if (0 == hashMap.get(node)) {
                        list.add(node);
                    }
                }
            }
        }
        return result;
    }
}
