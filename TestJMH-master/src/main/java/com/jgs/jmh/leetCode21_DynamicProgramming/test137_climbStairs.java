package com.jgs.jmh.leetCode21_DynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/28 - 05 - 28 - 15:01
 * @Description:com.jgs.jmh.leetCode21_DynamicProgramming
 * @version:1.0
 */

/**
 * * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class test137_climbStairs {

    public static int climbStairs1(int n) {
        return n <= 0 ? 0 : process(0, n);
    }

    public static int process(int cur, int n) {
        if (cur == n) {
            return 1;
        }
        if (cur > n) return 0;
        return process(cur + 1, n) + process(cur + 2, n);
    }

    public static int climbStairs2(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //进阶爬楼梯  总共有m级别，可爬的步数集合为[4,5]
    public static int climbStairs3(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int m = arr[0];
        for (int i = 0; i < arr.length; i++) {
            m = Math.min(m, arr[i]);
        }
        for (int i = m; i < n + 1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length; j++) {
                if (i - arr[j] < 0) continue;
                temp += dp[(i - arr[j])];
            }
            dp[i] = temp;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 44;
//        System.out.println(climbStairs1(n));
//        System.out.println(climbStairs2(n));
        int m = 100;
        int[] arr = {4, 5};
        System.out.println(climbStairs3(m, arr));
    }
}
