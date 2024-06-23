package com.jgs.jmh.leetCode.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/6 - 06 - 06 - 11:18
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 * * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 * *https://leetcode.cn/problems/count-square-submatrices-with-all-ones/description/
 */
public class test151_countSquares {
    public static int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    ans += dp[i][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(countSquares(matrix));
    }
}
