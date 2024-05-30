package com.jgs.jmh.leetCode21_DynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/29 - 05 - 29 - 10:28
 * @Description:com.jgs.jmh.leetCode21_DynamicProgramming
 * @version:1.0
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 */
public class test140_coinChange {
    //暴力递归
    static int result = -1;
    public static int coinChange1(int[] coins, int amount) {
        process(0, 0, coins, amount);
        return result;
    }

    public static void process(int cur, int count, int[] coins, int amount) {
        if (cur > amount) return;
        //剪枝
        if (result != -1 && count >= result) return;
        if (cur == amount) {
            if (result == -1) {
                result = count;
            } else {
                result = Math.min(result, count);
            }
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            //取当前值
            process(cur + coins[i], count + 1, coins, amount);
        }
    }

    //官方答案：暴力递归2 记忆化搜索
    public static int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }

    public static int coinChange(int[] coins, int remain, int[] count) {
        if (remain < 0) return -1;
        if (remain == 0) return 0;
        //因为原始的amount长度为amount，最总结果为count[amount-1]
        if (count[remain] != 0) return count[remain];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, remain - coin, count);
            if (res >= 0 && res < min) {
                //min = res + 1因为已经使用了一个硬币 coin，需要结果在+1
                min = 1 + res;
            }
        }
        count[remain] = (min == Integer.MAX_VALUE ? -1 : min);
        return count[remain];
    }

    //动态规划
    public static int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            int count = 0;
            for (int num : coins) {
                if (i - num >= 0) {
                    //符合条件的值最小硬币数为1，比如硬币数为5，则dp[5]的最小硬币组合数量为1
                    dp[num] = 1;
                    if (dp[num] != 0 && dp[i - num] != 0) {
                        if (count == 0) {
                            count = dp[num] + dp[i - num];
                        } else {
                            count = Math.min(count, dp[num] + dp[i - num]);
                        }
                    }
                }
            }
            //如果dp[i]已经存在值，则直接赋值即可
            dp[i] = dp[i] == 0 ? count : dp[i];
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    //官方解法：动态规划
    public static int coinChange4(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange1(coins, amount));
        System.out.println(coinChange2(coins, amount));
        System.out.println(coinChange3(coins, amount));
        System.out.println(coinChange4(coins, amount));
    }
}
