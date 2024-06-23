package com.jgs.jmh.course.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/15 - 01 - 15 - 16:00
 * @Description:com.mashibing.jmh.class10
 * @version:1.0
 */
public class test_union {

    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parent;
        public HashMap<Node<V>, Integer> sizeMap;

        public void init(List<V> list) {
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> node) {
            Stack<Node<V>> stack = new Stack<>();
            if (null == node) {
                return null;
            }

            while (node != parent.get(node)) {
                stack.push(node);
                node = parent.get(node);
            }

            while (!stack.isEmpty()) {
                parent.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSame(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return false;
            }
            return findFather(nodes.get(x)) == findFather(nodes.get(y));
        }

        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return;
            }
            Node<V> xNode = findFather(nodes.get(x));
            Node<V> yNode = findFather(nodes.get(y));

            int xSize = sizeMap.get(xNode);
            int ySize = sizeMap.get(yNode);
            if(xNode!=yNode){
                Node<V> big = xSize >= ySize ? xNode : yNode;
                Node<V> small = big == xNode ? yNode : xNode;
                parent.put(small, big);
                sizeMap.put(big, xSize + ySize);
                sizeMap.remove(small);
            }



        }


    }

}
