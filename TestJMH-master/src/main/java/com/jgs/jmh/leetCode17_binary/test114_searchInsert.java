package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/20 - 05 - 20 - 9:03
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * 35. 搜索插入位置
 * * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class test114_searchInsert {
    //在一个有序数组中找第一个大于等于target的下标
    public static int searchInsert(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        int target = 0;
        System.out.println(searchInsert(nums, target));
    }
}
