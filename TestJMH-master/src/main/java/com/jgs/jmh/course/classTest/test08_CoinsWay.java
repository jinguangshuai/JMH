package com.jgs.jmh.course.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 15:22
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test08_CoinsWay {
    public static int getResult(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(0, arr, aim);

    }

    public static int process(int index, int[] arr, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;

        }
        int result = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            result += process(index + 1, arr, rest - i * arr[index]);
        }
        return result;
    }

    public static int getResult2(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return dpWays2(0, arr, aim, dp);
    }

    public static int dpWays2(int index, int[] arr, int rest, int[][] dp) {
        if (index == arr.length) {
            dp[arr.length][rest] = rest == 0 ? 1 : 0;
        }
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int result = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            result += dpWays2(index + 1, arr, rest - i * arr[index], dp);
        }
        dp[index][rest] = result;
        return dp[index][rest];
    }


    public static int dpWays3(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int k = 0; k * arr[i] <= rest; k++) {
                    ways += dp[i + 1][rest - k * arr[i]];
                }
                dp[i][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    public static int dpWays4(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(getResult(arr, sum));
        System.out.println(getResult2(arr, sum));
        System.out.println(dpWays3(arr, sum));
        System.out.println(dpWays4(arr, sum));
    }
}
