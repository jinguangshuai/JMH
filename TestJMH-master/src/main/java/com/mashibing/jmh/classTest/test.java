package com.mashibing.jmh.classTest;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/8 - 03 - 08 - 9:42
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test {
    public static int getResult(int N, int M, int K, int P) {
        if (N < 1 || M > N || M < 1 || P < 1 || P > N || K < 1) {
            return 0;

        }
        return process(N, M, K, P);

    }

    public static int process(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return process(N, 2, rest - 1, P);
        }
        if (cur == N) {
            return process(N, N - 1, rest - 1, P);
        }
        return process(N, cur - 1, rest - 1, P) + process(N, cur + 1, rest - 1, P);
    }


    public static int dp(int N, int M, int K, int P){
        if (N < 1 || M > N || M < 1 || P < 1 || P > N || K < 1) {
            return 0;
        }
        int[][] dp = new int[N+1][K+1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                dp[row][col] = -1;
            }
        }
        return dpWay(N,M,K,P,dp);

    }

    public static int dpWay(int N, int cur, int rest, int P,int[][] dp) {
        if(dp[cur][rest]!=-1){
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = process(N, 2, rest - 1, P);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = process(N, N - 1, rest - 1, P);
            return dp[cur][rest];
        }
        dp[cur][rest] = process(N, cur - 1, rest - 1, P) + process(N, cur + 1, rest - 1, P);
        return dp[cur][rest];
    }

    public static void main(String[] args) {
        System.out.println(getResult(5, 2, 3, 3));
        System.out.println(dp(5, 2, 3, 3));
    }

}
