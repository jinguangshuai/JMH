package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/13 - 03 - 13 - 16:28
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class test04_removeDuplicates2 {

    public static int removeDuplicates(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n <= 2 && n >= 0) {
            return n;
        }
        int left = 2;
        int right = 2;
        while (right < n) {
            if (nums[left - 2] != nums[right]) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(removeDuplicates(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
