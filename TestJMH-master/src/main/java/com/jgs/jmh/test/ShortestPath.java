package com.jgs.jmh.test;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/23 - 06 - 23 - 20:53
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class ShortestPath {

    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 上、右、下、左
    char[][] matrix;
    boolean[][] visited;
    int rows, cols;

    public ShortestPath(char[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];
    }

    public void printShortestPath(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= rows || startY < 0 || startY >= cols ||
                endX < 0 || endX >= rows || endY < 0 || endY >= cols) {
            System.out.println("Invalid start or end coordinates");
            return;
        }
        dfs1(startX, startY, endX, endY, String.valueOf(matrix[startX][startY]));
        dfs(startX, startY, endX, endY, String.valueOf(matrix[startX][startY]));
    }

    static int min = Integer.MAX_VALUE;
    private void dfs1(int x, int y, int endX, int endY, String path) {
        if (x == endX && y == endY) {
            min = Math.min(min,path.length());
            return;
        }

        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                dfs1(newX, newY, endX, endY, path + "->" + matrix[newX][newY]);
            }
        }
        visited[x][y] = false; // Backtrack
    }

    private void dfs(int x, int y, int endX, int endY, String path) {
        if (x == endX && y == endY && path.length() == min) {
            System.out.println(path);
            return;
        }

        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                dfs(newX, newY, endX, endY, path + "->" + matrix[newX][newY]);
            }
        }
        visited[x][y] = false; // Backtrack
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        ShortestPath sp = new ShortestPath(matrix);
        sp.printShortestPath(0, 0, 2, 2); // 从(0, 0)到(3, 3)的最短路径
    }
}
