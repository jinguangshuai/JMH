package com.jgs.jmh.leetCode.leetCode04_Hash;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/10 - 04 - 10 - 11:08
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class test47_longestConsecutive {

    public static int longestConsecutive(int[] nums) {
        int m = nums.length;
        int result = Integer.MIN_VALUE;
        if (m <= 1) return m;
        Arrays.sort(nums);
        int count = 1;
        for (int i = 1; i < m; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                count++;
            } else if (nums[i - 1] == nums[i]) {
                continue;
            } else {
                result = Math.max(result, count);
                count = 1;
            }
        }
        result = Math.max(result, count);
        return result;
    }


    //hash表
    public static int longestConsecutive1(int[] nums) {
        int m = nums.length;
        int result = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            set.add(nums[i]);
        }
        for (int num : set) {
            // !set.contains(num - 1) 找到最低的数字
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curLength = 1;
                //判断这个数据是否存在连续的数字
                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    curLength += 1;
                }
                result = Math.max(result, curLength);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 1};
        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutive1(nums));
        System.out.println("---------");
    }
}
