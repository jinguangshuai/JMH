package com.jgs.jmh.leetCode21_DynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/28 - 05 - 28 - 16:39
 * @Description:com.jgs.jmh.leetCode21_DynamicProgramming
 * @version:1.0
 */

/**
 * * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class test138_rob {
    //暴力递归
    public static int rob1(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return process(0,nums,0);
    }

    public static int process(int index, int[] nums, int sum) {
        if (index >= nums.length) {
            return sum;
        }
        int a = nums[index] + process(index + 2, nums, sum);
        int b = process(index + 1, nums, sum);
        return Math.max(a, b);
    }
    //动态规划
    public static int rob2(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        System.out.println(rob1(nums));
        System.out.println(rob2(nums));
    }
}
