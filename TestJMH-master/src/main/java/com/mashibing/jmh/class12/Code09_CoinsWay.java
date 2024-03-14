package com.mashibing.jmh.class12;

import java.util.HashMap;

public class Code09_CoinsWay {

	// arr中都是正数且无重复值，返回组成aim的方法数
	public static int ways(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return process(arr, 0, aim);
	}

	// 如果自由使用arr[index...]的面值，组成rest这么多钱，返回方法数 （1 , 6）
	public static int process(int[] arr, int index, int rest) {
		if (index == arr.length) { // 无面值的时候
			return rest == 0 ? 1 : 0;
		}
		// 有面值的时候
		int ways = 0;
		// arr[index] 当钱面值
		for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
			ways += process(arr, index + 1, rest - zhang * arr[index]);
		}
		return ways;
	}

	public static int ways2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		//一开始所有的过程都没有计算
		//dp[...][...] = -1;
		int[][] dp = new int[arr.length+1][aim+1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(arr, 0, aim,dp);
	}

	// 如果自由使用arr[index...]的面值，组成rest这么多钱，返回方法数 （1 , 6）
	//如果index和rest的参数组合是没算过的，dp[index][rest]==-1
	//如果index和rest的参数组合是算过的，dp[index][rest]>-1
	public static int process2(int[] arr, int index, int rest,int[][] dp) {
		if(dp[index][rest]!=-1){
			return dp[index][rest];
		}
		if (index == arr.length) { // 无面值的时候
			dp[index][rest] =  rest == 0 ? 1 : 0;
			return dp[index][rest];
		}
		// 有面值的时候
		int ways = 0;
		for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
			ways += process2(arr, index + 1, rest - zhang * arr[index],dp);
		}
		dp[index][rest] = ways;
		return dp[index][rest];
	}

	public static int ways3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) { // 大顺序 从下往上
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
					ways += dp[index + 1][rest - zhang * arr[index]];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}



	public static int ways4(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int i = N - 1; i >= 0; i--) { // 大顺序 从下往上
			for (int rest = 0; rest <= aim; rest++) {
			    //process2(int[] arr, int index, int rest,int[][] dp)
				//for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                //	ways += process(arr, ], rest - zhang * arr[index]);
                //}
				dp[i][rest] = dp[i + 1][rest];
				//去除枚举剩余变量 如果i=10 rest=100,arr[10]=3
				// process2[10][100]=dp[10][100]=dp[11][100] + dp[11][97] + + dp[11][94] +...dp[11][1]
                //同理 process2[10][97]=dp[10][97] = dp[11][97] + dp[11][94] + ...dp[11][1]
                //如果 dp[10][100] = dp[11][100] + dp[10][97]

                //      dp[i][rest] += dp[i][rest - arr[i]];
                // 等同于dp[i][rest] = dp[i][rest] + dp[i][rest - arr[i]]
                // 等同于dp[i][rest] = dp[i+1][rest] + dp[i][rest - arr[i]]
				if (rest - arr[i] >= 0) {
					dp[i][rest] += dp[i][rest - arr[i]];
				}
			}
		}
		return dp[0][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 2, 3, 1 };
		int sum = 350;
		System.out.println(ways(arr, sum));
		System.out.println(ways2(arr, sum));
		System.out.println(ways3(arr, sum));
		System.out.println(ways4(arr, sum));
	}

}
