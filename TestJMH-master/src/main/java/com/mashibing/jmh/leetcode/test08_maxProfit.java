package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 18:25
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */
public class test08_maxProfit {

    //暴力递归
    public static int process(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(process(nums));
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
    }
}
