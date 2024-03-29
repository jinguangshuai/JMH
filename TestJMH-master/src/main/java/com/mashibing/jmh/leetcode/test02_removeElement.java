package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/13 - 03 - 13 - 10:06
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */
public class test02_removeElement {

    public static int removeElement(int[] nums, int val) {
        int m = 0;
        for (int i = 0; i < nums.length - m; i++) {
            if (nums[i] == val) {
                m++;
                swap(nums, i, nums.length - m);
                i = i - 1;
            }
        }
        return nums.length - m;
    }

    public static void swap(int[] num, int i, int j) {
        int tem = num[i];
        num[i] = num[j];
        num[j] = tem;

    }

    public static int removeElement2(int[] nums, int val) {
        int left = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i]!=val){
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3, 3, 3, 5};
        int val = 3;
        System.out.println(removeElement(nums, val));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
