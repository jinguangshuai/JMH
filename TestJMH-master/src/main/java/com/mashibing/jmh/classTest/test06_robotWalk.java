package com.mashibing.jmh.classTest;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.image.Kernel;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/7 - 03 - 07 - 14:31
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test06_robotWalk {

    public static int getResult(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return process(N, M, K, P);
    }

    public static int process(int N, int cur, int rest, int P) {
        if (rest == 0) { //base case1
            return cur == P ? 1 : 0;

        }
        if (cur == 1) {//base case2
            return process(N, 2, rest - 1, P);
        }
        if (cur == N) {//base case3
            return process(N, N - 1, rest - 1, P);

        }
        return process(N, cur - 1, rest - 1, P) + process(N, cur + 1, rest - 1, P);
    }

    public static int getResult2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] arr = new int[N + 1][K + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                arr[row][col] = -1;
            }
        }
        return process2(N, M, K, P, arr);
    }

    //缓存已经走过的路
    public static int process2(int N, int cur, int rest, int P, int[][] arr) {
        if (arr[cur][rest] != -1) {
            return arr[cur][rest];
        }

        if (rest == 0) { //base case1
            arr[cur][rest] = cur == P ? 1 : 0;
            return arr[cur][rest];

        }
        if (cur == 1) {//base case2
            arr[cur][rest] = process2(N, 2, rest - 1, P,arr);
            return arr[cur][rest];
        }
        if (cur == N) {//base case3
            arr[cur][rest] = process2(N, N - 1, rest - 1, P,arr);
            return arr[cur][rest];

        }
        arr[cur][rest] = process2(N, cur - 1, rest - 1, P,arr) + process2(N, cur + 1, rest - 1, P,arr);
        return arr[cur][rest];
    }


    public static void main(String[] args) {
        System.out.println(getResult2(5, 2, 3, 3));
    }

}
