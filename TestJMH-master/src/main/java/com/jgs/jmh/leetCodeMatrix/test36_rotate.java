package com.jgs.jmh.leetCodeMatrix;

/**
 * @Auther：jgs
 * @Data：2024/4/4 - 04 - 04 - 10:44
 * @Description:com.jgs.jmh.leetCodeMatrix
 * @version:1.0
 */
public class test36_rotate {

    //辅助数组
    public static int[][] rotate(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new int[][]{{}};
        }
        int m = matrix.length;
        int[][] result = new int[m][m];
        int row = 0, col = m - 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                //第一行赋值给新矩阵最后一列，第二行赋值给矩阵倒数第二列
                result[j][col] = matrix[row][j];
            }
            col--;
            row++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = result[i][j];
            }
        }
        return matrix;
    }

    //旋转数组
    public static int[][] rotate2(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new int[][]{{}};
        }
        int m = matrix.length;
        int row = 0, col = m - 1;
        //上下交换  第一行和最后一行交换，第二行和倒数第二行交换，依次类推
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < m; j++) {
                int temp = matrix[row][j];
                matrix[row][j] = matrix[col][j];
                matrix[col][j] = temp;
            }
            row++;
            col--;
        }
        //上下对角线选择一种即可
        //上对角线交换
//        for (int i = 0; i < m - 1; i++) {
//            for (int j = i + 1; j < m; j++) {
//                if (i != j) {
//                    int temp = matrix[i][j];
//                    matrix[i][j] = matrix[j][i];
//                    matrix[j][i] = temp;
//                }
//            }
//        }
        //下对角线交换
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    //原地旋转
    public static int[][] rotate3(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new int[][]{{}};
        }
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
//        int[][] matrix = {{5,1,9,11}, {2,4,8,10}, {13,3,6,7},{15,14,12,16}};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] rotate = rotate(matrix);
        int[][] rotate2 = rotate2(matrix1);
        int[][] rotate3 = rotate2(matrix2);
        for (int i = 0; i < rotate3.length; i++) {
            for (int j = 0; j < rotate3[0].length; j++) {
                System.out.println(rotate3[i][j]);
            }
        }
    }
}
