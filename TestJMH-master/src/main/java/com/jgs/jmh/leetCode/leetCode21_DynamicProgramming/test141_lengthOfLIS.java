package com.jgs.jmh.leetCode.leetCode21_DynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/29 - 05 - 29 - 15:24
 * @Description:com.jgs.jmh.leetCode21_DynamicProgramming
 * @version:1.0
 */

import java.util.Arrays;

/**
 * * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
 * 子序列
 */
public class test141_lengthOfLIS {
    //暴力递归解法
    public static int lengthOfLIS1(int[] nums) {
        return process(0, -1, nums);
    }

    public static int process(int index, int pre, int[] nums) {
        if (index == nums.length) {
            return 0;
        }
        int length = 0;
        //不包含当前元素
        int lengthWithout = process(index + 1, pre, nums);
        //包含当前元素
        if (pre == -1 || nums[index] > nums[pre]) {
            length = process(index + 1, index, nums) + 1;
        }
        return Math.max(length, lengthWithout);
    }

    public static int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //贪心+二分
    //如果当前值比d[len]大，则在d[len]末尾追加
    //如果当前值比d[len]小，找到nums[i]大于d数组的第一个位置，重新进行赋值，赋值不影响数组的单调性
    public static int lengthOfLIS3(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) return 0;
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            //如果当前值大于d[len]，则在d[len+1]位置填入当前值
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
                //原因：虽然将 d[k+1]更新为 nums[i] 不会影响 当前最长升序子序列（同时也不会影响已经判断过的升序子序列的长度），
                // 但是将 d[k+1]更新为 nums[i] 是有意义的，因为在 nums[i] 之后的序列中，还会有更多的数字，
                // 且刚好可以和 d[1,...,k] + nums[i] +其他数字凑成最长升序子序列。
                //这一步的意义，在于记录最小序列，代表了一种“最可能性”
                //例如[0,4,12,2,3,5]中，当判断到 nums[i] = 2时，d={0,4,12}，此时根据这个原则，
                // 会将4替换为2，即d更新为d={0,2,12}，虽然此时不会影响最长升序子序列的长度，
                // 但是这一步保存了之后有数字可以和{0,2}组成更长的升序子序列的可能性。
                // 例如，该数组的最长升序子序列为 {0,2,3,5}，刚好0,2}是这个子序列的前缀，
                // 也正是因为我们将 4 替换为了2，所以才保留了这种可能性，否则，遇到3时，将忽略这个数，从而计算错误。
            } else {
                //如果找不到说明所有的数都比nums[i]大，此时要更新d[1]，所以这里将pos设置为0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS1(nums));
        System.out.println(lengthOfLIS2(nums));
    }
}
