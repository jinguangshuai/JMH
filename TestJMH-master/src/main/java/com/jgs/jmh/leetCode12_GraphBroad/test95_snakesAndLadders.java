package com.jgs.jmh.leetCode12_GraphBroad;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/9 - 05 - 09 - 15:12
 * @Description:com.jgs.jmh.leetCode12_GraphBroad
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，
 * * 从左下角开始 （即，从 board[n - 1][0] 开始）每一行交替方向。
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 * 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * 选定目标方格 next ，目标方格的编号符合范围 [curr + 1, min(curr + 6, n2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n2 的方格时，游戏结束。
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，
 * * 那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n2 的方格上没有蛇或梯子。
 * <p>
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也不能继续移动。
 * <p>
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，
 * 但不能顺着方格 3 上的梯子前往方格 4 。
 * 返回达到编号为 n2 的方格所需的最少移动次数，如果不可能，则返回 -1。
 */
public class test95_snakesAndLadders {
    public static int snakesAndLadders(int[][] board) {
        if (null == board || board.length == 0) {
            return 0;
        }
        int m = board.length;
        boolean[] visited = new boolean[m * m + 1];
        Queue<int[]> queue = new LinkedList<>();
        //表示当前位于起点1，移动次数为0
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            //一个骰子的大小为1到6
            for (int i = 1; i <= 6; i++) {
                int next = p[0] + i;
                if (next > m * m) {
                    break;
                }
                //获取下一步的行列
                int[] rc = getRowAndCol(next, m);
                if (board[rc[0]][rc[1]] > 0) {
                    next = board[rc[0]][rc[1]];
                }
                if (next == m * m) {
                    return p[1] + 1;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, p[1] + 1});
                }
            }
        }
        return -1;
    }

    public static int[] getRowAndCol(int id, int n) {
        //获取行数
        int row = (id - 1) / n;
        //获取列数
        int col = (id - 1) % n;
        //如果为逆序，则处于 n - 1 - col
        //此时row为从下往上的层数，
        //例如row为0，为倒数第一层，为偶数，从左往右
        //例如row为1，为倒数第一层，为奇数，从右往左
        if (row % 2 == 1) {
            col = n - 1 - col;
        }
        //从最后一行开始遍历 n - 1 - row
        return new int[]{n - 1 - row, col};
    }

    public static void main(String[] args) {
//        int[][] board = {{-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 35, -1, -1, 13, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 15, -1, -1, -1, -1}};

        int[][] board = {{-1, 1, 2, -1},
                        {2, 13, 15, -1},
                        {-1, 10, -1, -1},
                        {-1, 6, 2, 8}};
        System.out.println(snakesAndLadders(board));
    }


}
