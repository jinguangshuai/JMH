package com.jgs.jmh.leetCode20_math;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/28 - 05 - 28 - 9:42
 * @Description:com.jgs.jmh.leetCode20_math
 * @version:1.0
 */

/**
 * * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 */
public class test135_myPow {

    //快速幂 + 递归
    //每次计算都以N/2的形式进行快速幂   例如求解x的77次方 x->x2->x4->x9->x19->x38->x77
    public static double myPow1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul1(x, N) : 1.0 / quickMul1(x, -N);
    }

    public static double quickMul1(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul1(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public static double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul2(x, N) : 1.0 / quickMul2(x, -N);
    }
    //对x不断地进行平方，得到x2，x4，x8，x16...
    public static double quickMul2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }


    public static void main(String[] args) {
        double x = 2;
        int n = 77;
        System.out.println(myPow2(x, n));
    }
}
