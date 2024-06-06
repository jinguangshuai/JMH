package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/4 - 06 - 04 - 18:37
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

import java.util.Arrays;

/**
 * * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class test147_minDistance {

    //动态规划
    //A = horse，B = horsep；此时 i = e，j = p，对应的操作是A插入，dp[i][j] = dp[i][j - 1] + 1。
    //A = horsep，B = horse；此时 i = p，j = e，对应的操作是A删除，dp[i][j] = dp[i - 1][j] + 1。
    //A = horseb，B = horsec；此时 i = b，j = c，对应的操作是A替换，dp[i][j] = dp[i - 1][j - 1] + 1。
    //这样是不会有dp[i][j]要依赖到dp[i+1][j]的。
    public static int minDistance1(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 * len2 == 0) return len1 + len2;
        int[][] dp = new int[len1 + 1][len2 + 1];
        //初始化边界
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < len2 + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                //dp[i - 1][j]表示A的前i-1个字符 B的前j个字符，对于A的第i个字符可以在B的末尾添加相同字符，可表示A的第i个字符已经消去
                int left = dp[i - 1][j] + 1;
                //dp[i][j - 1]表示A的前i个字符 B的前j-1个字符，对于B的第j个字符，我们可以在A的末尾加一个字符，可表示B的第j个字符消去
                int right = dp[i][j - 1] + 1;
                //dp[i - 1][j - 1]表示A的前i-1个字符 B的前j-1个字符，可以同时修改A的第i个字符和B的第j个字符（前提是A的i-1！=B的j-1）
                int leftRight = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    leftRight += 1;
                }
                dp[i][j] = Math.min(left, Math.min(right, leftRight));
            }
        }
        return dp[len1][len2];
    }

    //滚动数组，因为dp[i][j]只和dp[i-1][j-1]、dp[i][j-1]、dp[i-1][j]有关
    //我们只需要保留上一行的数据，而不需要保存全部dp矩阵
    //用一个临时值来代替dp[i-1][j-1]
    public static int minDistance2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 * len2 == 0) return len1 + len2;
        int[] dp = new int[len2 + 1];
        //初始化边界
        for (int i = 0; i < len2 + 1; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < len1; i++) {
            // i~j+1需要1步
            int pre = dp[0];
            ++dp[0];
            for (int j = 0; j < len2; j++) {
                int temp = dp[j + 1];
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[j + 1] = pre;
                } else {
                    dp[j + 1] = Math.min(dp[j] + 1, Math.min(dp[j + 1] + 1, pre + 1));
                }
                pre = temp;
            }
        }
        return dp[len2];
    }

    //记忆化搜索
    static char[] s, t;
    static int[][] dp;
    public static int minDistance3(String word1, String word2) {
        s = word1.toCharArray();
        t = word2.toCharArray();
        int n = s.length, m = t.length;
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(n - 1, m - 1);
    }
    private static int dfs(int i, int j) {
        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        // 记忆化搜索
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s[i] == t[j]) {
            return dp[i][j] = dfs(i - 1, j - 1);
        }
        return dp[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
        System.out.println(minDistance3(word1, word2));
    }
}
