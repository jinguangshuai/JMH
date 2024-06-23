package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/13 - 03 - 13 - 10:06
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。
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

    //单指针法
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
