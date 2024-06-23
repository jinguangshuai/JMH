package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/18 - 03 - 18 - 10:09
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 */
public class test09_canJump {
    //贪心算法
    public static boolean canJump(int[] nums) {
        int m = nums.length;
        int right = 0;
        for (int i = 0; i < m; i++) {
            if (i <= right) {
                right = Math.max(right, i + nums[i]);
            }
            if (right >= m - 1) {
                return true;
            }
        }
        return false;
    }
    //暴力递归
    public static boolean canJump2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }
        int process = process(nums, nums.length - 1);
        if (process >= nums.length - 1) {
            return true;
        }
        return false;
    }
    public static int process(int[] nums, int index) {
        if (index == 0) {
            return nums[0];
        }
        int right = process(nums, index - 1);
        if (index <= right) {
            right = Math.max(right, index + nums[index]);
        }
        return right;
    }

    public static int[] generate(int maxSize, int maxValue) {
        maxSize = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[maxSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] nums = {0};
        int[] generate = generate(20, 20);

        System.out.println(canJump(nums));
        System.out.println(canJump2(nums));
    }
}
