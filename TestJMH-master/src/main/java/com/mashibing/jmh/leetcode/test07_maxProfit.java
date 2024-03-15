package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 16:27
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
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
