package com.jgs.jmh.leetCodeMatrix;

/**
 * @Auther：jgs
 * @Data：2024/4/3 - 04 - 03 - 14:34
 * @Description:com.jgs.jmh.leetCodeMatrix
 * @version:1.0
 */

/**
 *请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 */
public class test34_isValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][][] sub = new int[3][3][9];
        int m = board.length,n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char index = board[i][j];
                if('.' != index){
                    row[i][index - '0' - 1]++;
                    col[j][index - '0' - 1]++;
                    sub[i/3][j/3][index - '0' - 1]++;
                    if(row[i][index - '0' - 1] > 1 || col[j][index - '0' - 1] > 1 || sub[i/3][j/3][index - '0' - 1] > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
    }
}
