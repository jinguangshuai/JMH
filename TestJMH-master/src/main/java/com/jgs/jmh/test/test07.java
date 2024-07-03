package com.jgs.jmh.test;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/22 - 06 - 22 - 19:26
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test07 {

    public static int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int m = nums.length;
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //首先将8升的水倒到3升的桶中，在将3升的水倒到5升的桶中，再将8升的水桶的水桶倒到3升的桶中，
    // 再将3升的水桶倒到5升的水桶，此时3升的水桶还有1升水，再将5升的水桶倒到8升的水桶，
    // 再将3升的水桶的1升水倒到5升的水桶中，再将8升的水桶倒到3升的水桶，之后将3升的水桶倒到5升的水桶，此时8升和5升的水桶各有4升水

    public static int getResult(int[][] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int m = arr.length;
        int n = arr[0].length;
        int result = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (arr[row][col] == 1) {
                    dfs(arr, row, col);
                    result++;
                }
            }
        }
        return result;
    }

    public static void dfs(int[][] arr, int row, int col) {
        int m = arr.length;
        int n = arr[0].length;
        if (row < 0 || row >= m || col < 0 || col >= n || arr[row][col] == 0) {
            return;
        }
        arr[row][col] = 0;
        dfs(arr, row + 1, col);
        dfs(arr, row - 1, col);
        dfs(arr, row, col + 1);
        dfs(arr, row, col - 1);
    }


}
