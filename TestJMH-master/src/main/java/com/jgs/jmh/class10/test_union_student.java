package com.jgs.jmh.class10;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/15 - 01 - 15 - 16:39
 * @Description:com.mashibing.jmh.class10
 * @version:1.0
 */

public class test_union_student {

    public static class Node<V> {
        public V v;

        public Node(V value) {
            this.v = value;
        }
    }

    public class UnionSet<V> {
        public HashMap<V, Node<V>> nodes = new HashMap<>();
        public HashMap<Node<V>, Node<V>> parent = new HashMap<>();
        public HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> list) {
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public int getNum() {
            return sizeMap.size();

        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parent.get(cur)) {
                path.push(cur);
                cur = parent.get(cur);
            }
            // cur头节点
            // 链扁平
            while (!path.isEmpty()) {
                parent.put(path.pop(), cur);
            }
            return cur;
        }

        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return;
            }

            Node<V> xNode = findFather(nodes.get(x));
            Node<V> yNode = findFather(nodes.get(y));

            if (xNode != yNode) {
                int xSize = sizeMap.get(xNode);
                int ySize = sizeMap.get(yNode);
                Node<V> big = xSize > ySize ? xNode : yNode;
                Node<V> small = big == xNode ? yNode : xNode;
                parent.put(small, big);
                sizeMap.put(big, xSize + ySize);
                sizeMap.remove(small);
            }
        }
    }


    public static class Student {
        public String x;
        public String y;
        public String z;

        public Student(String x, String y, String z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public int getNum(List<Student> students) {
        UnionSet<Student> unionSet = new UnionSet<>(students);

        HashMap<String, Student> mapA = new HashMap<>();
        HashMap<String, Student> mapB = new HashMap<>();
        HashMap<String, Student> mapC = new HashMap<>();

        for (Student student : students) {
            if (mapA.containsKey(student.x)) {
                unionSet.union(student, mapA.get(student.x));
            } else {
                mapA.put(student.x, student);
            }

            if (mapB.containsKey(student.y)) {
                unionSet.union(student, mapB.get(student.y));
            } else {
                mapB.put(student.y, student);
            }

            if (mapC.containsKey(student.x)) {
                unionSet.union(student, mapC.get(student.z));
            } else {
                mapC.put(student.z, student);
            }
        }
        return unionSet.getNum();
    }

    public String getString(int str,int length){
        char[] chars = new char[(int) Math.random() * str + 1];
        for (int i = 0; i < chars.length; i++) {
            int s = (int) (Math.random() * length);
            chars[i] = (char) (97 + s);
        }
        char[] charss = new char[(int) Math.random() * str + 1];
        for (int i = 0; i < chars.length; i++) {
            int s = (int) (Math.random() * length);
            charss[i] = (char) (97 + s);
        }
        return String.valueOf(chars) + String.valueOf(charss);
    }

    public List<Student> getRandom(int size) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Student student = new Student(getString(1,11),getString(2,22),getString(3,33));
            list.add(student);
        }
        return list;
    }

    public static void main(String[] args) {
//        List<Student> list = new ArrayList<>();
//        Student student = new Student("a", "b", "c");
//        Student student1 = new Student("d", "b", "c");
//        Student student2 = new Student("c", "b", "f");
//        Student student3 = new Student("r", "q", "o");
//        Student student4 = new Student("m", "p", "z");
//        list.add(student);
//        list.add(student1);
//        list.add(student2);
//        list.add(student3);
//        list.add(student4);
//
//        int num = new test_union_student().getNum(list);
//        System.out.println(num);

        List<Student> random = new test_union_student().getRandom(10);
        int num = new test_union_student().getNum(random);
        System.out.println(num);

    }


}
