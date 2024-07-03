package com.jgs.jmh.test;

import java.util.function.Predicate;

/**
 * @Auther：jgs
 * @Data：2024/6/24 - 06 - 24 - 21:33
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test08 {

    //相对位置发生改变
    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int index = 0;

        while (index <= right) {
            if (arr[index] % 2 == 1) {
                if (index > left) {
                    swap(arr, index, left);
                }
                left++;
            } else {
                if (index < right) {
                    swap(arr, index, right);
                    right--;
                } else {
                    right--;
                }
            }
            index++;
        }
    }

    //相对位置不变,时间复杂度比较高
    public static void reverse2(int[] arr) {
        int left = 0, right = 0;
        while (left < arr.length) {
            //找到数组的第一个偶数
            while (left < arr.length && (arr[left] & 1) != 0) {
                left++;
            }
            right = left + 1;
            //找到数组的第一个奇数
            while (right < arr.length && (arr[right] & 1) == 0) {
                right++;
            }
            //将left-> right-1的数往后移一位，left+1 -> right
            //将原下标位right的元素赋值给left
            if (right < arr.length) {
                int temp = arr[right];
                for (int k = right - 1; k >= left; k--) {
                    arr[k + 1] = arr[k];
                }
                arr[left++] = temp;
            } else {
                break;
            }
        }
    }

    //冒泡思想
    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 != 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void swap(int[] arr, int x, int y) {
        arr[x] = arr[x] ^ arr[y];
        arr[y] = arr[x] ^ arr[y];
        arr[x] = arr[x] ^ arr[y];
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        reOrderArray(array);

        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
