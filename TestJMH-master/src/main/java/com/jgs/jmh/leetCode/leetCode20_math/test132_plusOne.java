package com.jgs.jmh.leetCode.leetCode20_math;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/27 - 05 - 27 - 16:55
 * @Description:com.jgs.jmh.leetCode20_math
 * @version:1.0
 */

/**
 * * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class test132_plusOne {

    public static int[] plusOne1(int[] digits) {
        int length = digits.length;
        int[] result = new int[length];
        int carry = 0;
        if (digits[length - 1] + 1 == 10) {
            carry = 1;
        } else {
            result[length - 1] = digits[length - 1] + 1;
        }
        for (int i = length - 2; i >= 0; i--) {
            if (carry == 1) {
                int sum = digits[i] + carry;
                if (sum / 10 != 0) {
                    carry = 1;
                } else {
                    result[i] = digits[i] + carry;
                    carry = 0;
                }
            } else {
                result[i] = digits[i];
            }
        }
        if (carry != 0) {
            int[] ans = new int[length + 1];
            ans[0] = 1;
            return ans;
        }
        return result;
    }

    //判断第i位是否为0，如果为0，则继续前边的值进行计算
    public static int[] plusOne2(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int[] plusOne3(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < length; j++) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        digits = new int[length+1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        int[] ints = plusOne2(digits);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
