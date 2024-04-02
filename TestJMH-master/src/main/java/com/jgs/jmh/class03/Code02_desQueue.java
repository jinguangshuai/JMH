package com.jgs.jmh.class03;

import java.util.*;
import java.util.stream.Collectors;

/**
 * * 求逆序对，并展示出来
 * *逆序对：
 * *1,2,5,8,3,5
 * *则逆序对为（5,3）、（8,3）、（8,5）
 */
public class Code02_desQueue {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 4, 3, 5};
        List<Map<Integer, Integer>> list = smallSum(arr);
        list.forEach(r -> {
            System.out.println(r);
        });
    }

    public static List<Map<Integer, Integer>> smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Collections.emptyList();
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    public static List<Map<Integer, Integer>> process(int[] arr, int l, int r) {
        if (l == r) {
            return Collections.emptyList();
        }
        int mid = l + ((r - l) >> 1);
        List<Map<Integer, Integer>> process = process(arr, l, mid);
        List<Map<Integer, Integer>> process1 = process(arr, mid + 1, r);
        List<Map<Integer, Integer>> process2 = merge(arr, l, mid, r);
        List<Map<Integer, Integer>> result = new ArrayList<>();
        result.addAll(process);
        result.addAll(process1);
        result.addAll(process2);
		List<Map<Integer, Integer>> collect = result.stream().distinct().collect(Collectors.toList());
		return collect;
    }

    public static List<Map<Integer, Integer>> merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        List<Map<Integer, Integer>> list = new ArrayList<>();

        while (p1 <= m && p2 <= r) {
//			arr[p1] > arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            for (int j = m+1; j < p2; j++) {
                if (arr[p1] > arr[j]) {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(arr[p1], arr[j]);
                    list.add(map);
                }
            }

        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return list;
    }

}
