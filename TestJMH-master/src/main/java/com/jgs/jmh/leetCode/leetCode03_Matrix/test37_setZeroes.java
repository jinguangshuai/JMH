package com.jgs.jmh.leetCode.leetCode03_Matrix;

import java.util.HashSet;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/7 - 04 - 07 - 10:13
 * @Description:com.jgs.jmh.leetCodeMatrix
 * @version:1.0
 */

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class test37_setZeroes {
    //额外数组
    public static void setZeroes(int[][] matrix) {
        if(null == matrix || matrix.length==0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rowSet.contains(i) || colSet.contains(j)){
                    if(matrix[i][j] != 0){
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    //使用两个标记变量
    //首先预处理出两个标记变量，接着使用除去第一行第一列的其他行与列 处理/变换 第一行与第一列，
    //然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可
    public static void setZeroes1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        //先处理第0列是否包含0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        //判断第0行是否包含0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        //从第一行第一列开始判断，如果当前值为0，则第i行第0列的值也为0,第0行第j列的数也为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //从第一行第一列开始，如果第i行第0列的数字为0  或者第0行第i列的数字为0，则设置当前行或者当前列的数字都为0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //设置第0列的数为0
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        //设置第0行的数为0
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    //使用一个标记变量
    //只使用一个标记变量记录第一列是否原本存在0。这样，第一列的第一个元素即可以标记第一行是否出现0。
    //但为了防止每一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素。

    //先判断第0列是否存在0，在判断剩余行和列
    //最后从最后一行开始设置值，设置剩余行和列，并且判断第0列是否设置为0
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            //判断第0列是否存在0
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            //判断如果第i行第j列数字为0，则设置第i行第0列的元素、第0行第j列的数字为0，只处理第一行，如果存在0设置为0
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }



    public static void main(String[] args) {
        int[][] matrix0 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix2 = {{0,1,2,0},{3,0,5,2},{1,3,1,5}};
        int[][] matrix3 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        setZeroes1(matrix2);
        setZeroes2(matrix3);
        System.out.println(111);
    }
}
