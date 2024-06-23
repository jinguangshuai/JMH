package com.jgs.jmh.test;

import javax.xml.transform.Result;
import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/22 - 06 - 22 - 19:35
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class Node<T> {

    T val;
    Node<T> left;
    Node<T> right;
    Node<T> up;
    Node<T> down;


    public Node() {

    }

    public Node(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getUp() {
        return up;
    }

    public void setUp(Node<T> up) {
        this.up = up;
    }

    public Node<T> getDown() {
        return down;
    }

    public void setDown(Node<T> down) {
        this.down = down;
    }

    public static Node generate(int k) {
        if (k <= 0) {
            return null;
        }
        Node<Integer> head = new Node<>(1);
        Node<Integer> cur = head;
        for (int i = 2; i <= k; i++) {
            Node<Integer> newNode = new Node<>(i);
            cur.right = newNode;
            newNode.left = cur;
            cur = newNode;
        }
        Node<Integer> nodeRowCur = head;
        int val = k;
        for (int row = 2; row <= k; row++) {
            Node<Integer> newRowNode = new Node<>(++val);
            newRowNode.up = nodeRowCur;
            nodeRowCur.down = newRowNode;
            cur = newRowNode;
            for (int col = 2; col <= k; col++) {
                Node<Integer> newColNode = new Node<>(++val);
                cur.right = newColNode;
                newColNode.left = cur;
                nodeRowCur = nodeRowCur.right;
                newColNode.up = nodeRowCur;
                nodeRowCur.down = newColNode;

                cur = newColNode;
            }
            nodeRowCur = newRowNode;
        }
        return head;
    }

    public static void print(Node node) {
        Node row = node;
        while (null != row) {
            Node cur = row;
            while (null != cur) {
                System.out.print(String.format("%3d  ", cur.val));
                cur = cur.right;
            }
            System.out.println();
            row = row.down;
        }
    }

    //遍历获得所有路径，并找到最短路径，最后进行处理
    public static List<Set<Node>> getMinPath(Node start, Node end) {
        List<Set<Node>> result = new ArrayList<>();
        if (null == start || null == end || start == end) {
            return result;
        }
        dfs(start, end, new HashSet<>(),result);
        return result;
    }
    static int min = Integer.MAX_VALUE;
    public static void dfs(Node start, Node end, Set<Node> element,List<Set<Node>> result) {
        if (null == start || null == end) {
            return;
        }
        if (start == end) {
            min = Math.min(min, element.size());
            result.add(new HashSet<>(element));
        }
        element.add(start);
        if (null != start.right && !element.contains(start.right)) {
            dfs(start.right, end, new HashSet<>(element),result);
        }
        if (null != start.left && !element.contains(start.left)) {
            dfs(start.left, end, new HashSet<>(element),result);
        }
        if (null != start.up && !element.contains(start.up)) {
            dfs(start.up, end, new HashSet<>(element),result);
        }
        if (null != start.down && !element.contains(start.down)) {
            dfs(start.down, end, new HashSet<>(element),result);
        }
        element.remove(start);
    }



    public static void main(String[] args) {
        Node node = generate(5);
//        print(node);
        Node start = node.right.down;//7
        Node end = node.right.right.right.down.down.down;//19
        
        List<Set<Node>> minPath = getMinPath(start, end);
        for(Set<Node> nodes : minPath){
            if(nodes.size() == min){
                Node inStart = start;
                Node inEnd = end;
                Map<Node, Integer> map = new HashMap<>();
                for (Node no : nodes) {
                    map.put(no, (Integer) no.val);
                }
                map.put(inEnd, (Integer) inEnd.val);

                System.out.print(String.format("%3d  ", inStart.val));
                map.remove(inStart);
                while (inStart != inEnd) {
                    if (map.containsKey(inStart.left)) {
                        inStart = inStart.left;
                        System.out.print(String.format("%3d  ", inStart.val));
                        map.remove(inStart);
                        continue;
                    }
                    if (map.containsKey(inStart.right)) {
                        inStart = inStart.right;
                        System.out.print(String.format("%3d  ", inStart.val));
                        map.remove(inStart);
                        continue;
                    }
                    if (map.containsKey(inStart.up)) {
                        inStart = inStart.up;
                        System.out.print(String.format("%3d  ", inStart.val));
                        map.remove(inStart);
                        continue;
                    }
                    if (map.containsKey(inStart.down)) {
                        inStart = inStart.down;
                        System.out.print(String.format("%3d  ", inStart.val));
                        map.remove(inStart);
                        continue;
                    }
                }
                System.out.println();

            }
        }
//        for (Set<Node> nodes : result) {
//            for (Node cur : nodes) {
//                System.out.print(String.format("%3d  ", cur.val));
//            }
//            System.out.println();
//        }
    }
}
