package com.jgs.jmh.leetCode.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/27 - 05 - 27 - 15:09
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 给你两个整数 left 和 right ，表示区间 [left, right] ，
 * * 返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 */
public class test130_rangeBitwiseAnd {

    //寻找公共前缀
    public static int rangeBitwiseAnd1(int left, int right) {
        int count = 0;
        //每次向右移动一位，直至所有的前缀都相同，或全部为0，或全部为1，最多31次即可获取答案
        while (left < right){
            left >>= 1;
            right >>= 1;
            count++;
        }
        //left最后的值为0或者1，返回结果 left<<count 或者 right<<count
        return left << count;
    }

    public static int rangeBitwiseAnd2(int left, int right) {
        while (left < right){
            //去除最右侧的1
            right = right & (right - 1);
        }
        return right;
    }

    public static void main(String[] args) {
        int left = 5,right = 7;
        System.out.println(rangeBitwiseAnd1(left, right));
    }
}
