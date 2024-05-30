package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/29 - 05 - 29 - 18:50
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是
 * * 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 */
public class test142_minimumTotal {
    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1) return triangle.get(0).get(0);
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int col = triangle.get(i).size();
            for (int j = 0; j < col; j++) {
                //避免上层左侧或右侧越界
                if (j - 1 >= 0 && j < col - 1) {
                    int num1 = dp[i - 1][j - 1] + triangle.get(i).get(j);
                    int num2 = dp[i - 1][j] + triangle.get(i).get(j);
                    dp[i][j] = Math.min(num1, num2);
                } else {
                    //避免上层右侧越界
                    if (j < col - 1) {
                        dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j];
                    } else {
                        //如果越界，则说明当前值已经到达本层右侧边界位置，需要上一层j-1位置的数与之相加
                        //例如(1,1)(2,2)
                        dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j - 1];
                    }
                }
                if (i == n - 1) {
                    result = Math.min(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    //官方解法：动态规划使用 n^2的额外空间复杂度
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1) return triangle.get(0).get(0);
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            //解决左边界问题
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            //解决中间值
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            //解决右边界赋值问题
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int result = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }

    // f[i][j] =  f[i-1][0] + c[i][0]     j = 0
    // f[i][j] =  f[i-1][j-1] + c[i][j]     j = i
    // f[i][j] =  min(f[i-1][j-1],f[i-1][j]) + c[i][j]     0<j<i
    //f[i][j]只与f[i-1][...]有关，与f[i-2][...]及之前状态无关，我们可以不存储这些状态
    public static int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            //将i根据奇偶性映射到其中一个一维数组，i-1就映射到另外一个一维数组
            //判断是0位置，还是1位置
            int cur = i % 2;
            int prev = 1 - cur;
            //左边界
            dp[cur][0] = dp[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[cur][j] = Math.min(dp[prev][j - 1], dp[prev][j]) + triangle.get(i).get(j);
            }
            //有边界
            dp[cur][i] = dp[prev][i - 1] + triangle.get(i).get(i);
        }
        int min = dp[(n - 1) % 2][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[(n - 1) % 2][i]);
        }
        return 0;
    }

    //官方复杂度：空间复杂度为n
    //当计算位置（i，j）时，f[0]-f[j]仍然是第i-1行的值，f[j+1]到f[i]是第i行的值，
    //可以通过 f[j] = min(f[j-1],f[j]) + c[i][j]进行转移
    //每一行从后往前进行遍历，每次更新前一项的值
    public static int minimumTotal4(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        int min = dp[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    //1.从下往上遍历
    //2.当前值加下一层才相邻最小值，更新
    //3.更新到第一层，最小值就是路径和
    //不使用额外的空间，但会修改三角形的值
    public static int minimumTotal5(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }


    public static int minimumTotal6(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        // //获取最后一行的值
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        //向上进行深度优先遍历
        dfs(triangle, dp, n - 2);
        return dp[0];
    }

    public static void dfs(List<List<Integer>> triangle, int[] dp, int index) {
        if (index < 0) {
            return;
        }
        List<Integer> list = triangle.get(index);
        for (int i = 0; i < list.size(); i++) {
            dp[i] = Math.min(dp[i], dp[i + 1]) + list.get(i);
        }
        dfs(triangle, dp, index - 1);
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(-1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(-1);
        list3.add(-3);
//        List<Integer> list4 = new ArrayList<>();
//        list4.add(4);
//        list4.add(1);
//        list4.add(8);
//        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
//        triangle.add(list4);
        System.out.println(minimumTotal1(triangle));

    }
}
