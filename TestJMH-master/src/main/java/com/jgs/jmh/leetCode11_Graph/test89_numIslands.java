package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/7 - 05 - 07 - 8:42
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class test89_numIslands {
    //深度优先遍历
    public static int numIslands1(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1') {
                    dfs(grid, row, col);
                    result++;
                }
            }
        }
        return result;
    }

    public static void dfs(char[][] grid, int x, int y) {
        if (null == grid || grid.length == 0) {
            return;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

    //宽度优先遍历
    public static int numIslands2(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1') {
                    queue.add(row * n + col);
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        int newRow = id / n;
                        int newCol = id % n;
                        if (newRow - 1 >= 0 && newRow - 1 < m && grid[newRow - 1][newCol] == '1') {
                            queue.add((newRow - 1) * n + newCol);
                            grid[newRow - 1][newCol] = '0';
                        }
                        if (newRow + 1 >= 0 && newRow + 1 < m && grid[newRow + 1][newCol] == '1') {
                            queue.add((newRow + 1) * n + newCol);
                            grid[newRow + 1][newCol] = '0';
                        }
                        if (newCol - 1 >= 0 && newCol - 1 < n && grid[newRow][newCol - 1] == '1') {
                            queue.add(newRow * n + newCol - 1);
                            grid[newRow][newCol - 1] = '0';
                        }
                        if (newCol + 1 >= 0 && newCol + 1 < n && grid[newRow][newCol + 1] == '1') {
                            queue.add(newRow * n + newCol + 1);
                            grid[newRow][newCol + 1] = '0';
                        }
                    }
                    grid[row][col] = '0';
                    result++;
                }
            }
        }
        return result;
    }

    public static class Graph {
        int count;
        int[] parent;
        int[] rank;

        public Graph(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            count = 0;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == '1') {
                        parent[row * n + col] = row * n + col;
                        count++;
                    }
                    rank[row * n + col] = 0;
                }
            }
        }

        public int findFather(int i) {
            if (i != parent[i]) {
                parent[i] = findFather(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootX = findFather(x);
            int rootY = findFather(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static int numIslands3(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        Graph union = new Graph(grid);
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1') {
                    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                        union.union(row * n + col, (row - 1) * n + col);
                    }
                    if (row + 1 < m && grid[row + 1][col] == '1') {
                        union.union(row * n + col, (row + 1) * n + col);
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                        union.union(row * n + col, row * n + col - 1);
                    }
                    if (col + 1 < n && grid[row][col + 1] == '1') {
                        union.union(row * n + col, row * n + col + 1);
                    }
                }
            }
        }
        return union.getCount();
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands3(grid));

    }
}
