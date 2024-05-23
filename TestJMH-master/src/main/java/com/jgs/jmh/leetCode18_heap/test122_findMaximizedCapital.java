package com.jgs.jmh.leetCode18_heap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/22 - 05 - 22 - 16:15
 * @Description:com.jgs.jmh.leetCode18_heap
 * @version:1.0
 */

import java.util.*;

/**
 * * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，
 * * 力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * <p>
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * <p>
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * <p>
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * <p>
 * 答案保证在 32 位有符号整数范围内。
 */
public class test122_findMaximizedCapital {
    //递归
    public static int findMaximizedCapital1(int k, int w, int[] profits, int[] capital) {
        if (k == 0 || null == profits || profits.length == 0 || null == capital || capital.length == 0) {
            return w;
        }
        int n = profits.length;
        int curr = 0;
        //建立二维数组，第一位为成本，第二位为利润
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        //根据启动该项目的最小资本排序升序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        //建立大根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; i++) {
            while (curr < n && arr[curr][0] <= w) {
                queue.add(arr[curr][1]);
                curr++;
            }
            //取当前queue最大的值
            if (!queue.isEmpty()) {
                w += queue.poll();
            } else {
                break;
            }
        }
        return w;
    }

    public static void build(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //递归实现最大资本，时间超限
    public static int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        if (k == 0 || null == profits || profits.length == 0 || null == capital || capital.length == 0) {
            return 0;
        }
        return process(k, 0, w, profits, capital);
    }

    public static int process(int k, int index, int remain, int[] profits, int[] capital) {
        if (k == 0) return remain;
        if (index == profits.length) return remain;
        //不使用当前元素
        int p1 = process(k, index + 1, remain, profits, capital);
        //使用当前元素
        int p2 = Integer.MIN_VALUE;
        if (remain >= capital[index]) {
            p2 = process(k - 1, index + 1, remain + profits[index], profits, capital);
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int k = 2, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};
        System.out.println(findMaximizedCapital1(k, w, profits, capital));
    }
}
