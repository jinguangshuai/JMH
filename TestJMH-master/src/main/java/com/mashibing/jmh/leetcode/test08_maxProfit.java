package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 18:25
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 */
public class test08_maxProfit {

    //贪心算法
    public static int process(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    //暴力递归
    public static int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        return Math.max(processNo(prices, prices.length - 1), processYes(prices, prices.length - 1));

    }

    public static int processNo(int[] prices, int index) {
        if (index == 0) {
            return 0;
        }
        int currentNoNo = processNo(prices, index - 1);
        int currentYesNo = processYes(prices, index - 1) + prices[index];
        return Math.max(currentNoNo, currentYesNo);
    }

    public static int processYes(int[] prices, int index) {
        if (index == 0) {
            return -prices[0];
        }
        int currentYesYes = processYes(prices, index - 1);
        int currentNoYes = processNo(prices, index - 1) - prices[index];
        return Math.max(currentYesYes, currentNoYes);
    }

    //动态规划
    public static int dp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];

    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(process(nums));
        System.out.println(maxProfit(nums));
        System.out.println(dp(nums));

//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
    }
}
