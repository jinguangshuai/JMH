package com.jgs.jmh.leetCode.leetCode03_Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jgs
 * @Data：2024/4/3 - 04 - 03 - 14:58
 * @Description:com.jgs.jmh.leetCodeMatrix
 * @version:1.0
 */

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class test35_spiralOrder {
    //辅助数组+模拟
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        //记录该位置是否被提取过数字
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        //{0, 1}向右, {1, 0}向下, {0, -1}向左, {-1, 0}向上
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    //方法二：按层模拟
    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            //防止交界点重复
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            //左边界右移
            left++;
            //右边界左移
            right--;
            //上边界下移
            top++;
            //下边界上移
            bottom--;
        }
        return order;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;
        //上        下
        int top = 0, bottom = matrix.length - 1;
        //左        右
        int left = 0, right = matrix[0].length - 1;

        while (true) {
            for (int i = left; i <= right; i++) { // 左->右
                ans.add(matrix[top][i]);
            }
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++) { // 上->下
                ans.add(matrix[i][right]);
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) { // 右->左
                ans.add(matrix[bottom][i]);
            }
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) { // 下->上
                ans.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return ans;
    }

    public static List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        int left = 0 , right = n - 1;
        int top = 0 , bottom = m - 1;
        while (true){
            //从左向右 保存最后一个数字 (0,0)->(0,3)
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            if(++top > bottom) break;
            //从上往下，保存最后一个数字(1,3) -> (3,3)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            if(--right < left) break;
            //从右往左 保存最后一个数字(3,2) -> (3,0)
            for (int i = right; i >= left ; i--) {
                result.add(matrix[bottom][i]);
            }
            if(--bottom < top) break;
            //从下往上 保存最后一个数字(2,0) -> (1,0)
            for (int i = bottom; i >= top ; i--) {
                result.add(matrix[i][left]);
            }
            if(++left > right) break;
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> list = spiralOrder(matrix);
        List<Integer> list1 = spiralOrder1(matrix);
        List<Integer> list2 = spiralOrder2(matrix);
        List<Integer> list3 = spiralOrder3(matrix);
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
    }
}
