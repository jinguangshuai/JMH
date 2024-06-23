package com.jgs.jmh.course.classTest;

import java.util.Arrays;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 17:43
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test09_inCrease {
    public static int findLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp数组存储每个位置i之前的最长递增子序列的长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = Integer.MIN_VALUE;
        for (int len : dp) {
            maxLen = Math.max(len, maxLen);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,6,7,9};
        System.out.println("最长递增子序列的长度为：" + findLIS(nums));
    }
}
