package com.jgs.jmh.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jgs
 * @Data：2024/6/23 - 06 - 23 - 21:30
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class ShortestPaths {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 上、右、下、左
    private char[][] matrix;
    private boolean[][] visited;
    private List<List<Integer>> allPaths = new ArrayList<>();
    private int rows, cols;

    public ShortestPaths(char[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];
    }

    public void printAllShortestPaths(int startX, int startY, int endX, int endY) {
        List<Integer> path = new ArrayList<>();
        dfs(startX, startY, endX, endY, path);
    }

    private void dfs(int x, int y, int endX, int endY, List<Integer> path) {
        if (x < 0 || y < 0 || x >= rows || y >= cols || visited[x][y] || matrix[x][y] == '#') {
            return;
        }

        path.add(x * cols + y); // 将当前位置添加到路径中
        visited[x][y] = true;

        if (x == endX && y == endY) {
            allPaths.add(new ArrayList<>(path)); // 找到一条最短路径
        } else {
            for (int[] direction : DIRECTIONS) {
                dfs(x + direction[0], y + direction[1], endX, endY, path);
            }
        }

        visited[x][y] = false; // 回溯时重置访问状态
        path.remove(path.size() - 1); // 移除当前位置
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'a', 'b', 'c', 'd', 'e'},
                {'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o'},
                {'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y'}
        };

        ShortestPaths sp = new ShortestPaths(matrix);
        sp.printAllShortestPaths(1, 1, 3, 3);
        int min = Integer.MAX_VALUE;
        for (List<Integer> path : sp.allPaths) {
            min = Math.min(min,path.size());
        }
        for (List<Integer> path : sp.allPaths) {
            if(path.size() == min){
                for (int p : path) {
                    int x = p / matrix[0].length;
                    int y = p % matrix[0].length;
                    System.out.print(matrix[x][y] + "->");
                }
                System.out.println();
            }
        }
    }
}
