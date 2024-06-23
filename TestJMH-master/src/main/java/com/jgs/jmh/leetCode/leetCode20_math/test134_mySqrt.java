package com.jgs.jmh.leetCode.leetCode20_math;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/28 - 05 - 28 - 8:59
 * @Description:com.jgs.jmh.leetCode20_math
 * @version:1.0
 */

/**
 * * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class test134_mySqrt {

    //为什么二分查找的终止条件是left <= right，如果不放这个条件，while遍历就会遍历到之前已经遍历过的区域，
    // 为什么要加上=的判断条件，因为将左指针和右指针相同的时候，就是判断指针本身和目标值是否相等。每当这里想不清楚的时候，
    // 就拿最简单的例子：1，2，3来做推理。要想知道3在不在他们中间，先比较3和2，然后3比2大，
    // left = mid + 1 = 3，right = right = 3;这时候left 和 right相等，他们的中间也就是3,3=3，
    // 于是3在他们中间，如果left < right 就返回，就会返回错误的答案。
    public static int mySqrt1(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    //牛顿迭代法
    //每次求每个点的直线方程，获取与x轴的交点，然后不断迭代逼近，设置返回的精度，最后返回结果即可
    public static int mySqrt2(int x) {
        if(x == 0){
            return 0;
        }
        double C = x,x0 = x;
        while (true){
            double xi = 0.5 * (x0 + C / x0);
            if(Math.abs(x0 - xi) < 1e-7){
                break;
            }
            x0 = xi;
        }
        return (int) x0;

    }

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(mySqrt2(x));
    }

}
