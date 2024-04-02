package com.jgs.jmh.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/7 - 03 - 07 - 10:59
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test05_NQueue {
    public static int getResult(int n) {
        if (n < 1) {
            return 0;
        }
        int[] arr = new int[n];
        return process(arr, 0, n);
    }

    public static int process(int[] arr, int index, int n) {
        if (index == n) {
            return 1;
        }
        int result = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(arr, index, j)) {
                arr[index] = j;
                result += process(arr, index + 1, n);
            }
        }
        return result;
    }

    public static boolean isValid(int[] arr, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == arr[k] || Math.abs(i - k) == Math.abs(j - arr[k])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getResult(4));
    }

}
