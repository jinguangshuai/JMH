package com.jgs.jmh.leetCode.leetCode03_Matrix;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/7 - 04 - 07 - 14:10
 * @Description:com.jgs.jmh.leetCodeMatrix
 * @version:1.0
 */

/**
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * <p>
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
 * * 其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 */
public class test38_gameOfLife {

    public static void gameOfLife(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int alive = isAlive(board, i, j, board[i][j]);
                matrix[i][j] = alive;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = matrix[i][j];
            }
        }
    }

    public static int isAlive(int[][] board, int i, int j, int num) {
        int m = board.length, n = board[0].length;
        int[] arr = new int[2];
        if (i - 1 >= 0) {
            if (board[i - 1][j] == 0) {
                arr[0]++;
            } else {
                arr[1]++;
            }
            if (j - 1 >= 0) {
                if (board[i - 1][j - 1] == 0) {
                    arr[0]++;
                } else {
                    arr[1]++;
                }
            }
            if (j + 1 < n) {
                if (board[i - 1][j + 1] == 0) {
                    arr[0]++;
                } else {
                    arr[1]++;
                }
            }
        }
        if (i + 1 < m) {
            if (board[i + 1][j] == 0) {
                arr[0]++;
            } else {
                arr[1]++;
            }
            if (j - 1 >= 0) {
                if (board[i + 1][j - 1] == 0) {
                    arr[0]++;
                } else {
                    arr[1]++;
                }
            }
            if (j + 1 < n) {
                if (board[i + 1][j + 1] == 0) {
                    arr[0]++;
                } else {
                    arr[1]++;
                }
            }
        }
        if (j - 1 >= 0) {
            if (board[i][j - 1] == 0) {
                arr[0]++;
            } else {
                arr[1]++;
            }
        }
        if (j + 1 < n) {
            if (board[i][j + 1] == 0) {
                arr[0]++;
            } else {
                arr[1]++;
            }
        }
        if (num == 0) {
            if (arr[1] == 3) {
                return 1;
            }
        } else {
            if (arr[1] < 2) {
                return 0;
            } else if (arr[1] == 2 || arr[1] == 3) {
                return 1;
            } else {
                return 0;
            }
        }
        return num;
    }

    //官方解法  优化规则
    // -1代表过去是活的，现在死了
    // 2代表过去是死的，现在活了
    public static void gameOfLife1(int[][] board) {
        //设置方向来遍历某个节点周围的另外几个节点
        int[] ner = new int[]{-1, 0, 1};
        //获取行和列
        int rows = board.length;
        int cols = board[0].length;
        //遍历每一个节点格子
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                //设置当前节点周围的存活细胞的数量
                int liveNer = 0;
                /**
                 * 当前节点是[ i , j ]
                 * [i-1,j-1]    [i-1,j]    [i-1,j+1]
                 * [ i ,j-1]    [ i ,j]    [ i ,j+1]
                 * [i+1,j-1]    [i+1,j]    [i+1,j+1]
                 * 那么以当前节点为中心，要求周围的节点，则最多是3*3形式
                 * 并且所有的行和列都是用当前节点+1或者-1或者不变构成
                 * 所以我们设置 ner = {-1,0,1} 来形成遍历
                 */
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //必须保证不计算当前节点(不计算自己)
                        if (!(ner[i] == 0 && ner[j] == 0)) {
                            //当前节点的相邻节点坐标
                            int r = row + ner[i];
                            int c = col + ner[j];
                            /**判断当前周围节点的存活状态，要求满足两个状态
                             * 1. 必须保证要在 board 矩阵中
                             * 2. 并且起始状态要是存活，则当前状态为1或者-1都可以(因为这两个状态都表示起始状态为活细胞)
                             **/
                            if ((r >= 0 && r < rows) && (c >= 0 && c < cols) && (Math.abs(board[r][c]) == 1)) {
                                liveNer++;
                            }
                        }
                    }
                }
                /**开始判断当前节点的存活状态
                 * 因为遍历到当前节点的时候，还没有开始修改细胞状态，所以还是0和1的细胞状态
                 * 那么只需要判断状态变化的即可，否则状态不变
                 **/
                if ((board[row][col] == 1) && (liveNer > 3 || liveNer < 2)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[row][col] = -1;
                }
                if (board[row][col] == 0 && (liveNer == 3)) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[row][col] = 2;
                }
            }
        }
        //再把额外的状态修改回去
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 2) {
                    board[row][col] = 1;
                }
                if (board[row][col] == -1) {
                    board[row][col] = 0;
                }
            }
        }
    }

    //自己手动写的
    public static void gameOfLife2(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        int[] ner = { -1, 0, 1 };
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int live = 0;
                for (int p = 0; p < 3; p++) {
                    for (int q = 0; q < 3; q++) {
                        if (!(ner[p] == 0 && ner[q] == 0)) {
                            int nextRow = row + ner[p];
                            int nextCol = col + ner[q];
                            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                                    && (Math.abs(board[nextRow][nextCol]) == 1)) {
                                live += 1;
                            }
                        }
                    }
                }
                // 规则
                if (board[row][col] == 1 && (live < 2 || live > 3)) {
                    board[row][col] = -1;
                }
                if (board[row][col] == 0 && live == 3) {
                    board[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 2) {
                    board[row][col] = 1;
                }
                if (board[row][col] == -1) {
                    board[row][col] = 0;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int[][] board1 = {{1, 1}, {1, 0}};
        gameOfLife2(board1);
        System.out.println("-----------------");
    }
}
