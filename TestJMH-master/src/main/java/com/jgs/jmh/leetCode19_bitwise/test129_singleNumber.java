package com.jgs.jmh.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/24 - 05 - 24 - 17:39
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 */
public class test129_singleNumber {

    //a^a = 0  a^0 = a
    public static int singleNumber1(int[] nums) {
        int m = 0;
        int n = 0;
        for (int num : nums) {
            m = ~n & (m ^ num);
            n = ~m & (n ^ num);
        }
        return m;
    }

    //每一个数字的二进制，都是0或者3，非答案的第i个二进制位，每一个都出现了三次
    //对于数组的每一个元素x，使用位运算 (x>>i) & 1得到第i个二进制位，然后对3取余，就可以得到答案的第i个二进制位
    public static int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            //确定每一位的和
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            //如果total不等0，那么答案的第i位为1，那么将ans的第i位设置为1，ans | (1 << i)
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    //数字电路设计
    //设计真值表，查看所有为1的值，利用与、非、或、异或进行运算
    //数字电路设计  以输出端的1为准，如果为0，则为非
    public static int singleNumber3(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num);
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }

    //数字电路优化
    //对于整数x的第i为
    //如果 xi = 0, a和b的第i位不发生变化
    //如果xi = 1，a和b的第i位按照(0,0)->(0,1)->(1,0)->(0,0)进行循环变化
    //首先计算b，在根据b的值计算a
    public static int singleNumber4(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2};
        System.out.println(singleNumber1(nums));
    }
}
