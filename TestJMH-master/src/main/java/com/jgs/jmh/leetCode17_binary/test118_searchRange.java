package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/20 - 05 - 20 - 17:47
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class test118_searchRange {

    //左逼近  右逼近
    public static int[] searchRange1(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int begin = 0;
        int left = 0, right = nums.length - 1;
        //左侧逼近，寻找最左侧的节点
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) return new int[]{-1, -1};
        begin = left;
        left = 0;
        right = nums.length - 1;
        //右侧逼近，寻找最右侧的节点
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return new int[]{begin, left};
    }

    //官方解法
    public static int[] searchRange2(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        //寻找大于等于target的第一个数字
        int start = binarySearch(nums, target, true);
        int end = binarySearch(nums, target, false) - 1;
        if (start <= end && end < nums.length && start >= 0 && nums[start] == target && nums[end] == target) {
            return new int[]{start, end};
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target || (flag && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] ints = searchRange2(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
