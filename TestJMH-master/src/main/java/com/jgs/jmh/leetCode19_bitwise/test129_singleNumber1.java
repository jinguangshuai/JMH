package com.jgs.jmh.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/27 - 05 - 27 - 14:49
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 */
public class test129_singleNumber1 {

    public static int[] singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        //去出sum的最低位的1进行计算sum & (-sum)
        //最低位的1只有x1的第l位为0，x2的第l位为1才会成立
        int newSum = (sum == Integer.MIN_VALUE ? sum : (sum & (-sum)));
        int ans1 = 0, ans2 = 0;
        for (int num : nums) {
            //如果与最低位的newSum与运算不为0，说明该位置为1，进行与或运算
            if ((num & newSum) != 0) {
                ans1 ^= num;
            } else {
                ans2 ^= num;
            }
        }
        return new int[] { ans1, ans2 };
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,4,2,8};
        int[] ints = singleNumber(nums);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
