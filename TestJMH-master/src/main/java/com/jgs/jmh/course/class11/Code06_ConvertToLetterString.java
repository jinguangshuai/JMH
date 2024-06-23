package com.jgs.jmh.course.class11;

public class Code06_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0...i-1]已经转化完了，固定了
	// i之前的位置，如何转化已经做过决定了, 不用再关心
	// i... 有多少种转化的结果
	public static int process(char[] str, int i) {
		//第一种理解就是第i个位置为空字符串，前边的i-1个字符为一种拼接方案
		//第二张理解为前边i-1个位置已经固定，第i位置也随之固定，这种为一种拼接方案
		//基准情况是指递归函数可以直接返回的情况，而递归规则则是递归函数对基准情况之外的情况进行递归处理的情况。
		if (i == str.length) { // base case
			return 1;
		}
		// i没有到终止位置
		if (str[i] == '0') {
			return 0;
		}
		// str[i]字符不是‘0’
		if (str[i] == '1') {
			int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
			if (i + 1 < str.length) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		if (str[i] == '2') {
			int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
			// (i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
			if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		// str[i] == '3' ~ '9'则只有当前位置作为单独的字符
		return process(str, i + 1);
	}

	public static int dpWays(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (str[i] == '2') {
				dp[i] = dp[i + 1]; 
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number("11111"));
	}

}
