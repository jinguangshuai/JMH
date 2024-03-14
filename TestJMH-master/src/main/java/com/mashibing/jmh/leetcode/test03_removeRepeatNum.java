package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/13 - 03 - 13 - 15:21
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
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
