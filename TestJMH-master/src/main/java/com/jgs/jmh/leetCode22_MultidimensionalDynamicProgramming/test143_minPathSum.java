package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/30 - 05 - 30 - 11:17
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 * * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 */
public class test143_minPathSum {
    //动态规划
    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        //为第一列赋值
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        //为第一行赋值
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int minPathSum2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j-1],dp[j]) + grid[i][j];
            }
        }
        return dp[col-1];
    }
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(grid));
        System.out.println(minPathSum2(grid));
    }
}
