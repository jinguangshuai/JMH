package com.jgs.jmh.leetcodeArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 16:27
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class test07_maxProfit {

    //动态规划
    public static int maxProfit(int[] prices) {
        int max = 0;
        int[][] arr = new int[prices.length][prices.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                arr[i][j] = prices[i] - prices[j];
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        return max;
    }

    //暴力递归
    public static int process(int[] prices, int index) {
        if (index == prices.length) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < index; i++) {
            if (prices[index] - prices[i] > max) {
                max = prices[index] - prices[i];
            }
        }
        max = Math.max(max, process(prices, index + 1));
        return max;
    }

    public static int maxProfit3(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < min){
                min = prices[i];
            }else if(prices[i] - min > max){
                max = prices[i] - min;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(maxProfit(nums));
        System.out.println(process(nums, 0));
        System.out.println(maxProfit3(nums));
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
    }
}
