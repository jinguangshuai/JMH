package com.jgs.jmh.leetCode.leetCode14_backtrack;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 17:36
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

/**
 * * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */
public class test105_totalNQueens {
    //利用数组 下标表示行，具体数值代表列
    public static int totalNQueens1(int n) {
        if (n < 1) return 0;
        int[] arr = new int[n];
        return process(0, n, arr);
    }

    public static int process(int index, int n, int[] arr) {
        if (index == n) {
            return 1;
        }
        int result = 0;
        for (int col = 0; col < n; col++) {
            if (isPalce(arr, index, col)) {
                arr[index] = col;
                result += process(index + 1, n, arr);
            }
        }
        return result;
    }

    public static boolean isPalce(int[] arr, int row, int col) {
        // i < row避免在同一行  col != arr[i] 避免在同一列
        // 不在同一条斜线  |行-行| ！= |列-列|
        for (int i = 0; i < row; i++) {
            if (col == arr[i] || Math.abs(arr[i] - col) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }

    //递归回溯解法
    public static int totalNQueens2(int n) {
        Set<Integer> columns = new HashSet<>();
        //右下斜线 行-列 = 行-列
        //右下斜线 可用 行-列表示每一方向单一的斜线
        Set<Integer> diagonals1 = new HashSet<>();
        //左下斜线 行+列 = 行 + 列
        //左下斜线 可用 行+列表示每一方向单一的斜线
        Set<Integer> diagonals2 = new HashSet<>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    //row已经控制皇后不在同一行，只需要保证不在同一列，用columns保证即可
    public static int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) continue;
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) continue;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }
    //位运算解决n皇后问题
    public static int totalNQueens3(int n) {
        return solve(n, 0, 0, 0, 0);
    }
    public static int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int result = 0;
            int availablePosition = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePosition != 0) {
                //单独取出最右侧位置的1，剩下位置都是0，计算方式为 自己取反+1在与自身
                int position = availablePosition & (-availablePosition);
                //去除最右侧的1
                availablePosition = availablePosition & (availablePosition - 1);
                result += solve(n, row + 1,
                        //列限制与当前放皇后位置进行或运算
                        columns | position,
                        //左斜线限制与当前放皇后位置进行或运算
                        (diagonals1 | position) << 1,
                        //右斜线限制与当前放皇后位置进行或运算
                        (diagonals2 | position) >> 1 );
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(totalNQueens1(n));
        System.out.println(totalNQueens2(n));
        System.out.println(totalNQueens3(n));
    }
}
