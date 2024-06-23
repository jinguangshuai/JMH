package com.jgs.jmh.course.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/4 - 03 - 04 - 15:01
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class test01_CardInLine {

    //范围上的解决方案
    public static int getResult(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;

        }
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    public static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int M = arr[L] + second(arr, L + 1, R);
        int N = arr[R] + second(arr, L, R - 1);
        return Math.max(M, N);
    }

    public static int second(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = first(arr, L + 1, R);
        int N = first(arr, L, R - 1);
        return Math.min(M, N);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,9,1};
        System.out.println(getResult(arr));
    }
}
