package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/7 - 05 - 07 - 17:50
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import java.util.*;

/**
 * * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 */
public class test92_calcEquation {
    //宽度优先遍历
    public static double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int index = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();

        int n = equations.size();
        //map记录每个节点的value值，从nvars = 0开始递增累加
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(equations.get(i).get(0))) {
                map.put(equations.get(i).get(0), index++);
            }
            if (!map.containsKey(equations.get(i).get(1))) {
                map.put(equations.get(i).get(1), index++);
            }
        }
        //对于每个点，存储其邻节点及对应的值
        List<Pair>[] edges = new List[index];
        for (int i = 0; i < index; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        //记录每个点的边集  a的边集为（ 1,2.0 ）其中1为b对应的value值，2.0为a->b的值
        for (int i = 0; i < n; i++) {
            //记录a的大小
            int va = map.get(equations.get(i).get(0));
            //记录b的大小
            int vb = map.get(equations.get(i).get(1));
            //记录a的边集
            edges[va].add(new Pair(vb, values[i]));
            //记录b的边集
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        //初始化结果集
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            //获取待计算的集合
            List<String> query = queries.get(i);
            //如果不存在，则返回-1.0
            double result = -1.0;
            if (map.containsKey(query.get(0)) && map.containsKey(query.get(1))) {
                //获取待计算的分子 ia，分母 ib
                int ia = map.get(query.get(0));
                int ib = map.get(query.get(1));
                //如果ia==ib则直接返回结果为1.0
                if (ia == ib) {
                    result = 1.0;
                } else {
                    //初始化队列
                    Queue<Integer> points = new LinkedList<>();
                    //添加分子
                    points.add(ia);
                    //初始化各个节点的结果
                    double[] rations = new double[index];
                    Arrays.fill(rations, -1.0);
                    //初始化待计算的分子为1.0，计算结果ib的值
                    rations[ia] = 1.0;
                    //rations[ib] < 0 代表待计算的节点未到达，需要继续计算
                    while (!points.isEmpty() && rations[ib] < 0) {
                        //宽度优先遍历，获取分子
                        int x = points.poll();
                        //遍历当前节点的边集
                        for (Pair pair : edges[x]) {
                            //获取边的指数值，例如a（0）->b（1） = 2.0,则y = 1  val = 2.0
                            // a的边集为b，记录b的value值为index，a->b的value为2.0
                            int y = pair.index;
                            double val = pair.value;
                            //判断该边集是否计算过，rations[y] < 0表示未被计算过
                            if (rations[y] < 0) {
                                rations[y] = rations[x] * val;
                                points.add(y);
                            }
                        }
                    }
                    //分为两种情况
                    //（1）从ia可到达ib，并计算最终的结果
                    //（2）从ia无法到达ib，返回-1.0
                    result = rations[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public static class Pair {
        int index;
        double value;

        public Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    //floyd算法
    public static double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int index = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        //初始化点集
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(equations.get(i).get(0))) {
                map.put(equations.get(i).get(0), index++);
            }
            if (!map.containsKey(equations.get(i).get(1))) {
                map.put(equations.get(i).get(1), index++);
            }
        }
        //初始化图矩阵，每个矩阵填充为-1
        double[][] graph = new double[index][index];
        for (int i = 0; i < index; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        //将已知条件填入矩阵中
        //       a(0)   b(1)   c(2)
        // a(0)  -1.0   2.0   -1.0
        // b(1)  1.0/2  -1.0   3.0
        // c(2)  -1.0   1.0/3  -1.0
        for (int i = 0; i < n; i++) {
            int va = map.get(equations.get(i).get(0)), vb = map.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }
        //预计算每个点位的值
        //       a(0)   b(1)   c(2)
        // a(0)  1.0    2.0    6.0
        // b(1)  0.5    1.0    3.0
        // c(2)  1.0/6  1.0/3  1.0
        //floyd算法
        for (int k = 0; k < index; k++) {
            //表示遍历
            for (int i = 0; i < index; i++) {
                for (int j = 0; j < index; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }
        //遍历所需计算的值
        //如果存在graph[ia][ib] > 0 ,则存储该值，否则返回-1.0
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (map.containsKey(query.get(0)) && map.containsKey(query.get(1))) {
                int ia = map.get(query.get(0)), ib = map.get(query.get(1));
                if (graph[ia][ib] > 0) {
                    result = graph[ia][ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    //带权并查集
    public static double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int index = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        int n = equations.size();
        //初始化点集
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(equations.get(i).get(0))) {
                map.put(equations.get(i).get(0), index++);
            }
            if (!map.containsKey(equations.get(i).get(1))) {
                map.put(equations.get(i).get(1), index++);
            }
        }
        //f为父点集   w为初始权重
        int[] f = new int[index];
        double[] w = new double[index];
        Arrays.fill(w, 1.0);
        //初始化，每个点集的父节点为自身
        for (int i = 0; i < index; i++) {
            f[i] = i;
        }
        //合并已知点集
        for (int i = 0; i < n; i++) {
            int va = map.get(equations.get(i).get(0)), vb = map.get(equations.get(i).get(1));
            //初始化合并点集
            merge(f, w, va, vb, values[i]);
        }
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (map.containsKey(query.get(0)) && map.containsKey(query.get(1))) {
                int ia = map.get(query.get(0)), ib = map.get(query.get(1));
                int fa = findf(f, w, ia), fb = findf(f, w, ib);
                if (fa == fb) {
                    result = w[ia] / w[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public static void merge(int[] parent, double[] weigh, int x, int y, double val) {
        //寻找两个节点的父亲fx  fy
        int fx = findf(parent, weigh, x);
        int fy = findf(parent, weigh, y);
        //将f[fx] = fy, 即fx的父节点记为fy
        parent[fx] = fy;
        //fx的权重为 k * w[y] / w[x]
        weigh[fx] = val * weigh[y] / weigh[x];
    }

    public static int findf(int[] parent, double[] weigh, int x) {
        if (parent[x] != x) {
            //获取父亲节点
            int father = findf(parent, weigh, parent[x]);
            //更新当前权值
            weigh[x] = weigh[x] * weigh[parent[x]];
            //更新父点集的值
            parent[x] = father;
        }
        return parent[x];
    }



    static class Graph {
        String name;
        List<Edge> edges;

        public Graph(String name) {
            this.name = name;
            edges = new ArrayList<>();
        }
    }
    static class Edge {
        Graph next;
        double val;

        Edge(Graph next, double val) {
            this.next = next;
            this.val = val;
        }
    }
    //深度优先遍历
    public static double[] calcEquation4(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Graph> map = new HashMap();
        //构建有向图
        for (int i = 0; i < equations.size(); i++) {
            //构建点集
            Graph node1 = getNode(map, equations.get(i).get(0));
            Graph node2 = getNode(map, equations.get(i).get(1));
            //构建边集
            node1.edges.add(new Edge(node2, values[i]));
            node2.edges.add(new Edge(node1, 1 / values[i]));
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            //获取待计算的分子
            String start = query.get(0);
            //获取待计算的分母
            String end = query.get(1);
            res[i] = find(map.get(start), map.get(end));
        }
        return res;
    }

    private static double find(Graph start, Graph end) {
        //如果当中有一方为空，则返回-1
        if(start==null||end==null){
            return -1;
        }
        HashSet<Graph> set = new HashSet<>();
        return find(start,end,set,1.0);
    }

    private static double find(Graph start, Graph end, HashSet<Graph> set, double val) {
        if(start==end){
            return val;
        }
        for (Edge line : start.edges) {
            if(set.contains(line.next)){
                continue;
            }
            set.add(line.next);
            double res = find(line.next, end, set, val * line.val);
            if(res!=-1){
                return res;
            }
            set.remove(line.next);
        }
        return -1;
    }

    private static Graph getNode(HashMap<String, Graph> map, String name) {
        Graph node;
        if (map.containsKey(name)) {
            node = map.get(name);
        } else {
            node = new Graph(name);
            map.put(name, node);
        }
        return node;
    }

    public static void initial(List<List<String>> equations, double[] values, List<List<String>> queries) {
        List<String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        List<String> l2 = new ArrayList<>();
        l2.add("b");
        l2.add("c");
        List<String> l3 = new ArrayList<>();
        l3.add("bc");
        l3.add("cd");
        equations.add(l1);
        equations.add(l2);
        equations.add(l3);

        List<String> q1 = new ArrayList<>();
        q1.add("a");
        q1.add("c");
        List<String> q2 = new ArrayList<>();
        q2.add("c");
        q2.add("b");
        List<String> q3 = new ArrayList<>();
        q3.add("bc");
        q3.add("cd");
        List<String> q4 = new ArrayList<>();
        q4.add("cd");
        q4.add("bc");
//        List<String> q5 = new ArrayList<>();
//        q5.add("x");
//        q5.add("x");
        queries.add(q1);
        queries.add(q2);
        queries.add(q3);
        queries.add(q4);
//        queries.add(q5);
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
//        double[] values = {2.0,3.0};
        double[] values = {1.5, 2.5, 5.0};
        List<List<String>> queries = new ArrayList<>();
        initial(equations, values, queries);
        double[] doubles = calcEquation4(equations, values, queries);
        for (int i = 0; i < doubles.length; i++) {
            System.out.println(doubles[i]);
        }

    }
}
