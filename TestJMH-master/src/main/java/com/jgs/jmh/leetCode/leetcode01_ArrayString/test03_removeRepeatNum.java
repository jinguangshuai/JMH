package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/13 - 03 - 13 - 15:21
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 *  给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 */
public class test03_removeRepeatNum {

    //低阶做法
    public static int removeDuplicates(int[] nums) {
        int right = nums.length;
        for (int i = 0; i < right; i++) {
            if ((i + 1) < right && nums[i] == nums[i + 1]) {
                right--;
                int n = i;
                while (n < right) {
                    nums[n] = nums[n + 1];
                    n++;
                }
                i--;
            }
        }
        return right;
    }

    //快慢指针
    public static int removeDuplicates2(int[] nums) {
        if(null == nums || nums.length == 0){
            return 0;
        }

        int left = 0;
        int right = 1;
        while(right < nums.length){
            if (nums[left] == nums[right]) {
                right++;
            } else if (nums[left] < nums[right]) {
                nums[left + 1] = nums[right];
                left++;
                right++;
            }
        }

        return ++left;
    }

    //快慢指针进阶做法
    public int removeDuplicates3(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 1;
        int right = 1;
        while (right < n) {
            if (nums[right] != nums[right - 1]) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates2(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
