package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/9 - 05 - 09 - 9:47
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import java.util.*;

/**
 * * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，
 * * 你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 */
public class test94_findOrder {
    //广度优先遍历
    public static int[] findOrder1(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[]{};
        }
        //记录边集
        List<List<Integer>> edges = new ArrayList<>();
        //记录所有课程的入度
        int[] in = new int[numCourses];
        //判断最后可读的课程
        int count = 0;
        //返回结构
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            in[info[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int num = queue.poll();
            result[count] = num;
            count++;
            for (int i : edges.get(num)) {
                in[i]--;
                if (in[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if (count == numCourses) {
            return result;
        } else {
            return new int[]{};
        }
    }

    //深度优先遍历
    static boolean flag = true;
    static int count = 0;
    public static int[] findOrder2(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[]{};
        }
        //记录边集
        List<List<Integer>> edges = new ArrayList<>();
        //记录是否被搜索过
        int[] visit = new int[numCourses];
        int[] result = new int[numCourses];
        count = numCourses;
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && flag; i++) {
            if (visit[i] == 0) {
                dfs(i, edges, visit, result);
            }
        }
        if (flag) {
            return result;
        } else {
            return new int[]{};
        }
    }

    public static void dfs(int i, List<List<Integer>> edges, int[] visit, int[] result) {
        visit[i] = 1;
        for (int num : edges.get(i)) {
            if (visit[num] == 0) {
                dfs(num, edges, visit, result);
                if (!flag) {
                    return;
                }
            } else if (visit[num] == 1) {
                flag = false;
                return;
            }
        }
        visit[i] = 2;
        count--;
        result[count] = i;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{0, 3}, {1, 3}, {2, 0}, {2, 1}};
        int numCourses = 4;
        int[] order = findOrder2(numCourses, prerequisites);
        for (int i = 0; i < order.length; i++) {
            System.out.println(order[i]);
        }
    }
}
