package com.jgs.jmh.leetCode.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/6 - 06 - 06 - 10:11
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 * * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class test150_maximalSquare {

    //利用数组正方形的性质
    //例如一个边长为4的正方形，他的右下角dp[i - 1][j], dp[i][j - 1]，dp[i - 1][j - 1]三个正方形边长的最小值都为3，
    //则dp[i][j]的边长才会为4
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSide = 0;
        dp[0][0] = matrix[0][0] == '0' ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(matrix));
    }
}
