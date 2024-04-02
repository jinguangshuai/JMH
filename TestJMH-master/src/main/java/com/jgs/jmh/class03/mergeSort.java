package com.jgs.jmh.class03;

import org.w3c.dom.ranges.Range;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2023/8/14 - 08 - 14 - 14:24
 * @Description:com.mashibing.jmh.class03
 * @version:1.0
 */
public class mergeSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 3, 4, 8, 3};
        process(arr, 0, arr.length - 1);
//        mergeSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    public static void process(int[] arr, int L, int R) {
        if (null == arr && arr.length < 2) {
            return;
        }
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=M){
            help[i++] = arr[p1++];
        }
        while (p2<=R){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
    }

}
