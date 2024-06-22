package com.jgs.jmh.leetCode20_math;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/27 - 05 - 27 - 17:37
 * @Description:com.jgs.jmh.leetCode20_math
 * @version:1.0
 */

/**
 * * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 */
public class test133_trailingZeroes {
    //我们要明白尾数里面有0是怎么来的，末尾有0肯定是乘以10了，而10是2*5来的，
    // 而2的数量是一定大于等于5放入数量，如果这个问题就是，在阶乘的过程中一共出现了多少个5相乘。
    //首先我们使用n/5，这样就可以获取n后面有多少个5或者5的倍数的相乘因子。
    //但是有些数比如25是2个5相乘，125是3个5相乘，所以我们要把i除以5。再看看有多少个5或者5或者5的倍数的相乘因子。直到n小于5为止。
    public static int trailingZeroes1(int n) {
        int result = 0;
        while (n >= 5){
            result += n / 5;
            n /= 5;
        }
        return result;
    }

    //计算n多少个5，没有方法一好
    public int trailingZeroes2(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    public static int trailingZeroes3(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 30;
        System.out.println(trailingZeroes1(n));
        System.out.println(trailingZeroes3(5));
    }
}
