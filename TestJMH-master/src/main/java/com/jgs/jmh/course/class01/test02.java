package com.jgs.jmh.course.class01;

/**
 * @Auther：jinguangshuai
 * @Data：2023/3/30 - 03 - 30 - 11:00
 * @Description:com.mashibing.jmh.class01
 * @version:1.0
 */
public class test02 {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }

    }


    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void chooseSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int temp = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[temp]) {
                    temp = j;
                }
            }
            swap(arr, i, temp);
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int arr[] = new int[]{4, 3, 2, 1};
//        bubbleSort(arr);

//        swap1(arr, 0, 1);
//        for (int i : arr) {
//            System.out.println(i);
//        }
//
//        insertSort(arr);
        chooseSort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }
    }
}
