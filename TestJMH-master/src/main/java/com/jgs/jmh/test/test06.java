package com.jgs.jmh.test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/22 - 06 - 22 - 17:52
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test06 {

    public static int maxInterviews(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int count = 0;
        int current = 0;
        Arrays.sort(matrix, (int[] x, int[] y) -> (x[1] - y[1]));
        for (int[] element : matrix) {
            if (current <= element[0]) {
                count++;
                current = element[1] + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 5}, {2, 8}, {3, 4}, {4, 7}, {5, 10}};
        System.out.println(maxInterviews(matrix));
    }
}
