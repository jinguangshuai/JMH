package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/20 - 05 - 20 - 14:55
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class test116_findPeakElement {

    //爬坡寻找最优解
    //对于nums[mid] > nums[mid + 1]  说明当前值可能为峰值，right = mid，只要判断left - mid即可
    //对于nums[mid] < nums[mid + 1] 说明当前值不可能为峰值，left = mid + 1
    public static int findPeakElement1(int[] nums) {
        if (null == nums || nums.length == 0 || nums.length == 1) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //迭代爬坡
    public static int findPeakElement2(int[] nums) {
        int n = nums.length;
        int index = (int) (Math.random() * n);
        //compare(nums, index - 1, index) < 0 表示 nums[index-1] < nums[index]
        //compare(nums, index, index + 1) > 0 表示 nums[index] > nums[index + 1]
        //只有index同时满足  nums[index-1] < nums[index] > nums[index + 1]为最终答案
        while (!(compare(nums, index - 1, index) < 0 && compare(nums, index, index + 1) > 0)) {
            //如果nums[index] < nums[index + 1] index右移
            if (compare(nums, index, index + 1) < 0) {
                index += 1;
            } else {
                //否则 左移
                index -= 1;
            }
        }
        return index;
    }

    public static int findPeakElement3(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public static int[] get(int[] nums, int index) {
        if (index == -1 || index == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[index]};
    }

    public static int compare(int[] nums, int index1, int index2) {
        int[] num1 = get(nums, index1);
        int[] num2 = get(nums, index2);
        //如果两个数字的下标不等，说明有一个数字越界
        //num1[0] > num2[0] 说明num2[0]越界， index右移
        //num1[0] < num2[0] 说明num1[0]越界， index左移
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        //如果两个数字相等，返回0
        if (num1[1] == num2[1]) {
            return 0;
        }
        //如果num1[1] > num2[1] 返回1向右移
        //如果num1[1] < num2[1] 返回-1向左移
        return num1[1] > num2[1] ? 1 : -1;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 6, 5, 4};
        System.out.println(findPeakElement1(nums));
    }
}
