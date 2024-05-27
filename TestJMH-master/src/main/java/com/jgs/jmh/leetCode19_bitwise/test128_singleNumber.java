package com.jgs.jmh.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/24 - 05 - 24 - 17:31
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class test128_singleNumber {

    //a^a = 0  a^0 = a
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }
}
