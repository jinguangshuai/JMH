package com.mashibing.jmh.class03;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/8 - 01 - 08 - 10:40
 * @Description:com.mashibing.jmh.class03
 * @version:1.0
 */
public class quickSort {

    public static void process(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        int[] mArr = quickSort(arr, L, R);
        process(arr, L, mArr[0] - 1);
        process(arr, mArr[1] + 1, R);
    }

    public static int[] quickSort(int[] arr, int L, int R) {
        if (null == arr || arr.length < 2) {
            return new int[]{-1, 1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int index = L;
        int less = L - 1;
        int more = R;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 7, 8, 5, 3};

        process(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
