package com.jgs.jmh.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther：jgs
 * @Data：2024/6/23 - 06 - 23 - 21:28
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class ShortestPathInMatrix {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPath(int[][] matrix, int startX, int startY, int endX, int endY) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] point = queue.poll();
                if (point[0] == endX && point[1] == endY) {
                    return steps;
                }

                for (int[] direction : DIRECTIONS) {
                    int newX = point[0] + direction[0];
                    int newY = point[1] + direction[1];
                    if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
                            && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        ShortestPathInMatrix solver = new ShortestPathInMatrix();
        int startX = 0, startY = 0, endX = 3, endY = 2;
        int shortestPath = solver.shortestPath(matrix, startX, startY, endX, endY);
        System.out.println("Shortest path: " + shortestPath);
    }
}
