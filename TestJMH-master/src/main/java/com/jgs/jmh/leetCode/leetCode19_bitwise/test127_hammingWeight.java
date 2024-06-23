package com.jgs.jmh.leetCode.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/24 - 05 - 24 - 17:20
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中
 * 设置位的个数（也被称为汉明重量）。
 */
public class test127_hammingWeight {
    //去除二进制的最后一个1
    //n & (n-1)
    //获取二进制的最后一个1
    //n & (-n)
    public static int hammingWeight1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }


    //对数字的二进制位进行遍历，获取所有的1
    public static int hammingWeight2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(hammingWeight1(n));
    }
}
