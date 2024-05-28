package com.jgs.jmh.leetCode20_math;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/28 - 05 - 28 - 10:42
 * @Description:com.jgs.jmh.leetCode20_math
 * @version:1.0
 */

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

/**
 * * 给你一个数组 points ，
 * * 其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class test136_maxPoints {

    //部分直线斜率求值存在误差，无法识别所有情况
    public static int maxPoints1(int[][] points) {
        int result = 0;
        if (null == points || points.length == 0) {
            return 0;
        }
        if (points.length == 1) return 1;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int k = 0;
                int c = 0;
                if (points[i][0] - points[j][0] != 0) {
                    //k有可能损失精度，例如2/9
                    k = (points[i][1] - points[j][1]) / (points[i][0] - points[j][0]);
                    c = -k * points[i][0] + points[i][1];
                }
                int count1 = 2;
                int count2 = 2;
                int count3 = 2;
                for (int l = j + 1; l < points.length; l++) {
                    if (isLine(k, c, points[l][0], points[l][1])) {
                        count1++;
                    }
                    if (points[i][0] == points[j][0]) {
                        if (points[l][0] == points[i][0]) count2++;
                    }
                    if (points[i][1] == points[j][1]) {
                        if (points[l][1] == points[i][1]) count3++;
                    }
                }
                result = Math.max(result, Math.max(count1, Math.max(count2, count3)));
            }
        }
        return result;
    }

    public static boolean isLine(int k, int c, int x, int y) {
        return y == k * x + c;
    }


    public static int maxPoints(int[][] points) {
        int n = points.length;
        //在点的总数量小于等于2的情况下，我们总可以用一条直线将所有点串联，此时我们直接返回点的总数量即可；
        if (n <= 2) return n;
        int result = 0;
        for (int i = 0; i < n; i++) {
            //当我们找到一条直线经过了图中超过半数的点时，我们即可以确定该直线即为经过最多点的直线
            if (result >= n - i || result > n / 2) {
                break;
            }
            //只用了斜率而没有用b，不是没有考虑同斜率不同线的问题，而是因为map的update是在循环里做的，一定是过i点的，所以只要斜率相同就一定是同一条线了
            //因为每个点都要过（points[i][0]，points[i][1]）
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    //规定分子为非负数，如果为负数，进行转换，否则会出现（-1/2 = 1/-2）的情况无法存储到一处
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    //获取x，y的最大公约数，保证最终的x，y为整数类型
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                //存储二元组的值，如果存在相同的值，当前值加1
                //题目描述   -10^4=<x，y<=10^4
                //所以我们可以用单个32位的变量表示这两个整数
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                //加上(point[i][0],point[i][1])坐标数量
                maxn = Math.max(maxn, num + 1);
            }
            result = Math.max(result,maxn);
        }
        return result;
    }
    //求取最大公约数
    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {
//        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points = {{-6, -1}, {3, 1}, {12, 3}};
        System.out.println(maxPoints1(points));

        System.out.println(gcd(3, 6));
    }
}
