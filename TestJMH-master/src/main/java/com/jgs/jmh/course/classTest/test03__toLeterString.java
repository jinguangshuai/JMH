package com.jgs.jmh.course.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/6 - 03 - 06 - 14:16
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test03__toLeterString {
    public static int getResult(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return 0;
        }
        return process(arr,0);
    }

    public static int process(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }

        if (arr[index] == 0) {
            return 0;
        }
        if (arr[index] == 1) {
            int result = process(arr, index + 1);
            if (index + 1 < arr.length) {
                result += process(arr, index + 2);
            }
            return result;
        }

        if (arr[index] == 2) {
            int result = process(arr, index + 1);
            if (index + 1 < arr.length && index + 1 >= 0 && index + 1 <= 6) {
                result += process(arr, index + 2);
            }
            return result;
        }

        return process(arr, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,1};
        System.out.println(getResult(arr));
    }

}
