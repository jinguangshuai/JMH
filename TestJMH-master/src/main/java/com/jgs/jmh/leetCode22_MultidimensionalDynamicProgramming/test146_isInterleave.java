package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/4 - 06 - 04 - 16:20
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空
 子字符串
 ：

 s = s1 + s2 + ... + sn
 t = t1 + t2 + ... + tm
 |n - m| <= 1
 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 注意：a + b 意味着字符串 a 和 b 连接。
 */
public class test146_isInterleave {

    public static boolean isInterleave1(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        if (s1Len + s2Len != s3Len) {
            return false;
        }
        //构建动态数组
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1Len; i++) {
            for (int j = 0; j <= s2Len; j++) {
                int p = i + j - 1;
                // s3的前i+j个元素取决于s1的前i个元素和s2的前j个元素是否能组成s3，
                //如果s1的第i个元素和s3的第i+j个元素相同，那么dp[i][j]取决于s1的第i-1个元素和s2的第j个元素能否形成s3的前i+j-1个元素
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                // s3的前i+j个元素取决于s1的前i个元素和s2的前j个元素是否能组成s3，
                //如果s2的第j个元素和s3的第i+j个元素相同，那么dp[i][j]取决于s2的第j-1个元素和s1的第i个元素能否形成s3的前i+j-1个元素
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[s1Len][s2Len];
    }

    //滚动数组优化空间复杂度  数组dp的第i行仅取决于第i-1行，所以可以使用滚动数组进行优化
    public static boolean isInterleave2(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        if (s1Len + s2Len != s3Len) {
            return false;
        }
        boolean[] dp = new boolean[s2Len + 1];
        dp[0] = true;
        for (int i = 0; i <= s1Len; i++) {
            for (int j = 0; j <= s2Len; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] || s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[s2Len];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(isInterleave1(s1, s2, s3));
        System.out.println(isInterleave2(s1, s2, s3));
    }
}
