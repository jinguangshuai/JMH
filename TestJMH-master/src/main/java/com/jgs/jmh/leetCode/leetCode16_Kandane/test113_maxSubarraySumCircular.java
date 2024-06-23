package com.jgs.jmh.leetCode.leetCode16_Kandane;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/17 - 05 - 17 - 18:03
 * @Description:com.jgs.jmh.leetCode16_Kandane
 * @version:1.0
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空子数组的最大可能和 。
 * <p>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上，
 * * nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <p>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，
 * * 对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 */
public class test113_maxSubarraySumCircular {
    //先根据kadane算法求得最大连续子数组之和
    //如果最大连续子数组不在普通数组上，则为环形数组之上  环形数组之和为左数组的前缀和（0~i） 加上（j~n） 的和

    //最大连续子序列值 处于正常子序列或者  环形序列中
    //如果处于正常序列，则使用kadane算法
    //如果处于环形序列，则先从左到右计算每一位的最大前缀和，再从右到左遍历以右为起点的前缀和，加上左侧最大前缀和，即为最大环形子序列之和
    public static int maxSubarraySumCircular1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //前缀和
        int[] left = new int[n + 1];
        left[0] = nums[0];
        int cur = nums[0], res = nums[0], leftSum = nums[0], rightSum = 0;
        for (int i = 1; i < n; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            res = Math.max(res, cur);
            leftSum += nums[i];
            //获取前缀和最大的值
            left[i] = Math.max(left[i - 1], leftSum);
        }
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + left[i - 1]);
        }
        return res;
    }

    //分别计算最大连续子数组之和、最小连续子数组之和
    //如果最大子数组之和小于0，说明全部为负数，最终结果最大连续子数组之和
    //否则，所有数组之和减去最小连续子数组之和即为环形数组的最大和，与最大连续子数组之和比较
    public static int maxSubarraySumCircular2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int curMax = nums[0], preMax = nums[0], curMin = nums[0], preMin = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], curMax + nums[i]);
            preMax = Math.max(curMax, preMax);
            curMin = Math.min(nums[i], curMin + nums[i]);
            preMin = Math.min(curMin, preMin);
            sum += nums[i];
        }
        if (preMax < 0) {
            return preMax;
        } else {
            return Math.max(preMax, sum - preMin);
        }
    }

    //单调队列
    public static int maxSubarraySumCircular3(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //int[] 0位置存储下标，1位置存储最大值
        Deque<int[]> queue = new ArrayDeque<>();
        int pre = nums[0], res = nums[0];
        queue.offerFirst(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            //对于i>=n的数字来说， i-n =< j < i,j为结果的下标
            //queue.peekFirst()[0] < i - n 表示当前前缀和的长度已经大于n
            //例如n = 4，i = 5，则取值范围为 下标1-5的数字，
            // 若取出的值为0，小于1，得到的最大前缀和为 0 -5的前缀和，超过n的最大长度，不符合要求
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.poll();
            }
            //获取前缀和
            pre += nums[i % n];
            //最大值为当前最大值， 与前缀和减去队列的最大值
            res = Math.max(res, pre - queue.peekFirst()[1]);
            //如果队列尾部K大于前缀和Sk >= si，则出队
            while (!queue.isEmpty() && queue.peekLast()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5, -3, 6, 8, 5};
        System.out.println(maxSubarraySumCircular1(nums));
        System.out.println(maxSubarraySumCircular2(nums));
        System.out.println(maxSubarraySumCircular3(nums));
    }
}
