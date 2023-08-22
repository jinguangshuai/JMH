package com.mashibing.jmh.class03;

import java.sql.Array;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2023/8/14 - 08 - 14 - 14:24
 * @Description:com.mashibing.jmh.class03
 * @version:1.0
 */
public class mergeSort {

    public void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //p2越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        //p1越界
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //赋值
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }


    public void mergeSort(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }

    }


}
