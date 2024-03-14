package com.mashibing.jmh.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 17:44
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test10_MaxSubsequence {
    public static int maxSubsequence(char[] a, char[] b) {
        if (null == a || a.length == 0 || null == b || b.length == 0) {
            return 0;
        }
        int[][] dp = new int[a.length][b.length];
        dp[0][0] = a[0] == b[0] ? 1 : 0;
        //第一列的数字
        for (int i = 1; i < a.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], a[i] == b[0] ? 1 : 0);
        }
        //第一行的数字
        for (int j = 1; j < b.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], a[0] == b[j] ? 1 : 0);
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (a[i] == b[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[a.length - 1][b.length - 1];
    }

    public static void main(String[] args) {
        String a = "ab123";
        String b = "a1b2c3";
        System.out.println(maxSubsequence(a.toCharArray(), b.toCharArray()));
    }

}
