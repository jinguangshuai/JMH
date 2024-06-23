package com.jgs.jmh.course.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/12 - 03 - 12 - 15:15
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test11_washCoffeCup {
    public static int process(int[] arr, int a, int b, int index, int washLine) {
        //当来到最后一杯咖啡的时候
        if (index == arr.length - 1) {
            return Math.min(Math.max(arr[index], washLine) + a, arr[index] + b);
        }
        //剩不止一杯咖啡
        //wash是我当前的咖啡杯，洗完的 时间
        //洗干净的时间
        int wash = Math.max(arr[index], washLine) + a;// 洗 index一杯，结束的时间点
        // index+1....变干净的最早时间
        int next1 = process(arr, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        //自然晾干的时间
        int dry = arr[index] + b;//index这一杯挥发结束的时间点
        int next2 = process(arr, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);

    }


    //将上述process改为动态规划版本
    public static int dpWays(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }
        int N = drinks.length;
        int limit = 0;//咖啡机什么时候可用
        //最大限制为所有的咖啡杯全部用咖啡机去洗
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit, drinks[i]) + a;
        }

        int[][] dp = new int[N][limit + 1];
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(Math.max(washLine, drinks[N - 1]) + a, drinks[N - 1] + b);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine, drinks[index]) + a;
                //防止wash越界
                if (wash <= limit) {
                    p1 = dp[index + 1][wash];
                }
                int dry = drinks[index] + b;
                int next2 = dp[index + 1][washLine];
                int p2 = Math.max(dry, next2);

                dp[index][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];

    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a = 3;
        int b = 10;
        System.out.println(process(arr, a, b, 0, 0));
        System.out.println(dpWays(arr, a, b));

    }
}
