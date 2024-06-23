package com.jgs.jmh.leetCode.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/7 - 05 - 07 - 10:19
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class test90_solve {
    //深度优先遍历
    public static void solve1(char[][] board) {
        if (null == board || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int row = 0; row < m; row++) {
            //判断每一行第一个  左边界
            dfs(board, row, 0);
            //判断每一行的最后一个 右边界
            dfs(board, row, n - 1);
        }
        for (int col = 1; col < n - 1; col++) {
            //判断第一行的第col列 上边界
            dfs(board, 0, col);
            //判断最后一行的第col列  下边界
            dfs(board, m - 1, col);
        }
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                //如果等于Z 说明Z位置在边界上或者与边界上的O相连接
                if (board[row][col] == 'Z') {
                    board[row][col] = 'O';
                    //如果仍然存在为O的位置，则说明此位置不在边界或者不与边界的O相连接
                } else if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                }
            }
        }
        System.out.println("----------------");
    }

    public static void dfs(char[][] board, int x, int y) {
        if (null == board || board.length == 0) {
            return;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'Z';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }

    public static void solve2(char[][] board) {
        if (null == board || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            //判断每一行第一个  左边界
            if (board[row][0] == 'O') {
                queue.add(new int[]{row, 0});
                board[row][0] = 'A';
            }
            //判断每一行的最后一个 右边界
            if (board[row][n - 1] == 'O') {
                queue.add(new int[]{row, n - 1});
                board[row][n - 1] = 'A';
            }
        }
        for (int col = 1; col < n - 1; col++) {
            if (board[0][col] == 'O') {
                queue.add(new int[]{0, col});
                board[0][col] = 'A';
            }
            if (board[m - 1][col] == 'O') {
                queue.add(new int[]{m - 1, col});
                board[m - 1][col] = 'A';
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                queue.add(new int[]{row - 1, col});
                board[row - 1][col] = 'A';
            }
            if (row + 1 < m && board[row + 1][col] == 'O') {
                queue.add(new int[]{row + 1, col});
                board[row + 1][col] = 'A';
            }
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                queue.add(new int[]{row, col - 1});
                board[row][col - 1] = 'A';
            }
            if (col + 1 < n && board[row][col + 1] == 'O') {
                queue.add(new int[]{row, col + 1});
                board[row][col + 1] = 'A';
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                //如果等于A 说明A位置在边界上 或者与边界上的O相连接
                if(board[row][col] == 'A'){
                    board[row][col] = 'O';
                    //如果仍然存在为O的位置，则说明此位置不在边界或者不与边界的O相连接
                }else if(board[row][col] == 'O'){
                    board[row][col] = 'X';
                }
            }
        }
        System.out.println("---------------");
    }


    public static void main(String[] args) {
//        char[][] board = {{'X','X','X','X'},
//                {'X','O','O','X'},
//                {'X','X','O','X'},
//                {'X','O','X','X'}};
        char[][] board = {{'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        solve2(board);
    }
}
