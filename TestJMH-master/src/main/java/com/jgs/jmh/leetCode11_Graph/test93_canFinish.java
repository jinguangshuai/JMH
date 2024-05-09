package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/8 - 05 - 08 - 16:03
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import com.sun.javafx.collections.MappingChange;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class test93_canFinish {
    public static class Graph{
        int val;//value值
        int in;//入度
        int out;//出度
        List<Edge> edges;
        List<Graph> neighbors;
        public Graph(int val, ArrayList<Edge> edges, ArrayList<Graph> neighbors){
            this.in = 0;
            this.out = 0;
            this.val = val;
            this.edges = edges;
            this.neighbors = neighbors;
        }
    }

    public static class Edge{
        Graph from;
        Graph to;
        public Edge(Graph from,Graph to){
            this.from = from;
            this.to = to;
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return true;
        } else if (null == prerequisites || prerequisites.length == 0) {
            return true;
        }
        //构建图
        HashMap<Integer, Graph> map = new HashMap<>();
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            //创建from图、to图
            Graph graphFrom = null;
            Graph graphTo = null;
            if (map.containsKey(from)) {
                //如果包含，直接获取
                graphFrom = map.get(from);
            } else {
                //如果不包含，新建
                graphFrom = new Graph(from, new ArrayList<>(), new ArrayList<>());
            }
            if (map.containsKey(to)) {
                graphTo = map.get(to);
            } else {
                graphTo = new Graph(to, new ArrayList<>(), new ArrayList<>());
            }
            //创建边集
            Edge edge = new Edge(graphFrom, graphTo);
            //from添加边集
            graphFrom.edges.add(edge);
            //from添加邻接图
            graphFrom.neighbors.add(graphTo);
            //from出度+1
            graphFrom.out += 1;
            map.put(from, graphFrom);
            //to入度+1
            graphTo.in += 1;
            map.put(to, graphTo);
        }
        List<Graph> result = new ArrayList<>();
        HashMap<Graph, Integer> inMap = new HashMap<>();
        Queue<Graph> queue = new LinkedList<>();
        //遍历图所有的节点，并存储入度为0的节点
        for(Graph graph : map.values()){
            inMap.put(graph, graph.in);
            if (graph.in == 0) {
                queue.add(graph);
            }
        }
        while (!queue.isEmpty()) {
            Graph graph = queue.poll();
            result.add(graph);
            for (Graph neighbor : graph.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) - 1);
                if (inMap.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        //判断最终的课程数是否等于图节点的个数
        return result.size() == map.values().size();
    }

    //拓扑排序
    //深度优先遍历
    //利用三个状态标记点位
    //0表示未搜索 1表示搜索中 2表示已搜索
    static List<List<Integer>> edges;
    static int[] visit;
    static boolean valid = true;
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visit = new int[numCourses];
        for(int[] info: prerequisites){
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if(visit[i] == 0){
                dfs(i);
            }
        }
        return valid;
    }
    // 0表示未搜索 1表示搜索中 2表示已搜索
    public static void dfs(int i){
        visit[i] = 1;
        for(int num : edges.get(i)){
            if(visit[num] == 0){
                dfs(num);
                if(!valid){
                    return;
                }
            }else if(visit[num] == 1){
                valid = false;
                return;
            }
        }
        visit[i] = 2;
    }

    //拓扑排序
    //广度优先遍历
    //初始化点、边
    //判断入度为0的点集，根据队列一直遍历，最终判断点集数量是否等于最终的课程总数
    public static boolean canFinish3(int numCourses, int[][] prerequisites) {
        //记录边
        List<List<Integer>> edges = new ArrayList<>();
        //记录每个元素的入度
        int[] in = new int[numCourses];
        int result = 0;
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for(int[] info: prerequisites){
            edges.get(info[1]).add(info[0]);
            in[info[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(in[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int num = queue.poll();
            result++;
            for(int i: edges.get(num)){
                in[i]--;
                if(in[i] == 0){
                    queue.add(i);
                }
            }
        }
        return result == numCourses;
    }



    public static void main(String[] args) {
        int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2}};
        int numCourses = 5;
        System.out.println(canFinish3(numCourses, prerequisites));
    }
}
