package com.jgs.jmh.leetCodeDoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/28 - 03 - 28 - 11:05
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的连续子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class test30_minSubArrayLen {
    //暴力解法 双重for循环  超时
    public static int minSubArrayLen(int target, int[] nums) {
        int m = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j - i + 1);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    //二分法
    public static int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            //官方二分法方法
            //int bound = Arrays.binarySearch(sums, target);
            //没有找到适合的值等于target，找到大于target的第一个位置的数字
            //if (bound < 0) {
            //    bound = -bound - 1;
            //}
            int bound = getIndexFromArray(sums, target);
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    //前缀和 + 二分法
    public static int minSubArrayLen3(int target, int[] nums) {
        int m = nums.length;
        int[] sums = new int[m + 1];
        int result = Integer.MAX_VALUE;
        //计算前缀和
        for (int i = 1; i < m + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= m; i++) {
            int sum = sums[i - 1] + target;
            int bound = getIndexFromArray(sums, sum);
            if (bound <= m) {
                result = Math.min(result, bound - (i - 1));
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    public static int getIndexFromArray(int[] arr, int target) {
        int m = arr.length;
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }


    //滑动窗口
    public static int minSubArrayLen4(int target, int[] nums) {
        int m = nums.length;
        int result = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < m) {
            sum += nums[end];
            while (sum >= target) {
                result = Math.min(result, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
        System.out.println(minSubArrayLen2(7, nums));
        System.out.println(minSubArrayLen3(7, nums));
        System.out.println(minSubArrayLen4(7, nums));




//        int[] numss = {0,2,5,6,8,12,15};
//        System.out.println(getIndexFromArray(numss, 7));
    }
}
