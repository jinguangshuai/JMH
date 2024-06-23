package com.jgs.jmh.course.class12;

public class Code05_PalindromeSubsequence {

	public static int lcse(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		//填写00位置的值
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		//填写第一列的所有数字
		for (int i = 1; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		//填写第一行的所有数字
		for (int j = 1; j < str2.length; j++) {
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				//先将Math.max(dp[i - 1][j], dp[i][j - 1])决断出大小
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[str1.length - 1][str2.length - 1];
	}

	public static void main(String[] args) {
		String a = "abcde";
		String b = "ace";
		System.out.println(lcse(a.toCharArray(), b.toCharArray()));
	}

}
