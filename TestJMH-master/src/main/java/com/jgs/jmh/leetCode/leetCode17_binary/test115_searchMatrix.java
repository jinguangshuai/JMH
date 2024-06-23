package com.jgs.jmh.leetCode.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/20 - 05 - 20 - 10:27
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * <p>
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class test115_searchMatrix {
    //先进行行二分，在进行列二分
    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        //区间为左开右闭区间
        int left = -1, right = m - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            //如果当前行存在最小值小于等于target，说明在mid ~ right行，存在target
            if (matrix[mid][0] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (left == -1)
            return false;
        int[] result = matrix[left];
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (result[mid] > target) {
                right = mid - 1;
            } else if (result[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    //一次查找：若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素。
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = matrix[mid / n][mid % n];
            if(num < target){
                left = mid + 1;
            }else if(num > target){
                right = mid - 1;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
//        int[][] matrix = {{1, 3, 5}};
        int target = 4;
        System.out.println(searchMatrix1(matrix, target));
        System.out.println(searchMatrix2(matrix, target));
    }
}
