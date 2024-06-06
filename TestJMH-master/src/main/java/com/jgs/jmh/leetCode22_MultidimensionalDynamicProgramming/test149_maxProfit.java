package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/5 - 06 - 05 - 16:55
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

import java.util.Arrays;

/**
 * * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class test149_maxProfit {
    public static int maxProfit1(int k, int[] prices) {
        int m = prices.length;
        int[][][] dp = new int[m][k + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k + 1; j++) {
                //-1代表当前位置未计算过
                Arrays.fill(dp[i][j], -1);
            }
        }
        return dfs(m - 1, k, 0, dp, prices);
    }

    public static int dfs(int i, int j, int hold, int[][][] dp, int[] price) {
        if (j < 0) return Integer.MIN_VALUE / 2;
        if (i < 0) return hold == 1 ? Integer.MIN_VALUE / 2 : 0;
        if (dp[i][j][hold] != -1) return dp[i][j][hold];
        if (hold == 1) {
            return dp[i][j][hold] = Math.max(dfs(i - 1, j, 1, dp, price), dfs(i - 1, j, 0, dp, price) - price[i]);
        }
        return dp[i][j][hold] = Math.max(dfs(i - 1, j, 0, dp, price), dfs(i - 1, j - 1, 1, dp, price) + price[i]);
    }

    //记忆化搜索修改为动态规划
    public static int maxProfit2(int k, int[] prices) {
        int m = prices.length;
        if (m == 0) return 0;
        // k + 2避免对k<0进行判断，直接从第1次开始遍历到k+1次
        int[][][] dp = new int[m + 1][k + 2][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k + 2; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
            }
        }
        //初始化第0天
        for (int i = 1; i < k + 2; i++) {
            dp[0][i][0] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= k + 1; j++) {
                //以每次卖出股票为完成一笔交易
                dp[i + 1][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][1] + prices[i]);
                dp[i + 1][j][1] = Math.max(dp[i][j][1], dp[i][j][0] - prices[i]);
            }
        }
        return dp[m][k + 1][0];
    }


    //空间压缩
    public static int maxProfit4(int k, int[] prices) {
        int m = prices.length;
        int[][] dp = new int[k + 1][2];
        //对于股票[1,100]
        //dp[1][1] = Math.max(dp[1][1], dp[0][0] - prices[0]); 如果不赋值为最小值，则dp[1][0]初始值为1，明显不符合题意，实际应为-1
        for (int i = 0; i < k + 1; i++) {
            //防止内存溢出
            dp[i][1] = Integer.MIN_VALUE / 2;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < k + 1; j++) {
                //以每次买入  次数+1
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }


    //123题变化而来
    public static int maxProfit5(int k, int[] prices) {
        int m = prices.length;
        if (m == 0) return 0;
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, -prices[0]);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < k + 1; j++) {
                //buy的最大值为自身、或者卖出的j-1次在加上第i次的买入
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                //sell的最大值为自身、或者买出的j次在加速第i次的卖出
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }


    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit1(k, prices));
        System.out.println(maxProfit2(k, prices));
        System.out.println(maxProfit4(k, prices));
    }
}
