package com.jgs.jmh.leetCode.leetcode01_ArrayString;

import java.util.Arrays;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/19 - 03 - 19 - 15:42
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class test13_productExceptSelf {

    //垃圾做法  时间复杂度为n的平方
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    result[i] *= nums[j];
                }
            }
        }
        return result;
    }

    //运用双数组进行计算
    public static int[] productExceptSelf2(int[] nums) {
        int m = nums.length;
        int[] result = new int[m];
        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[m];
        int[] R = new int[m];
        Arrays.fill(L, 1);
        Arrays.fill(R, 1);
        for (int i = 1; i < m; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < m; i++) {
            result[i] = L[i] * R[i];
        }
        return result;
    }

    //空间复杂度为0（1）的做法
    public static int[] productExceptSelf3(int[] nums) {
        int m = nums.length;
        int[] result = new int[m];
        Arrays.fill(result, 1);
        for (int i = 1; i < m; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int R = 1;
        for (int i = m - 1; i >= 0; i--) {
            result[i] = result[i] * R;
            R = R * nums[i];
        }
        return result;
    }


    //快慢指针做法
    public static int[] productExceptSelf4(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int beforeSum = 1;
        int afterSum = 1;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            ans[i] *= beforeSum;
            ans[j] *= afterSum;
            beforeSum *= nums[i];
            afterSum *= nums[j];
        }
        return ans;
    }


    public static int[] getResult(int[] nums) {
        int m = nums.length;
        int[] result = new int[m];
        Arrays.fill(result, 1);
        int before = 1;
        int after = 1;
        for (int i = 0, j = m - 1; i < m; i++, j--) {
            result[i] = result[i] * before;
            result[j] = result[j] * after;
            before = before * nums[i];
            after = after * nums[j];
        }
        return result;

    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,5,6,8};
        int[] ints = getResult(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
