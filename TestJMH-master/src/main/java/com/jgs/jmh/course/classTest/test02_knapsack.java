package com.jgs.jmh.course.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/4 - 03 - 04 - 15:12
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test02_knapsack {

    public static int getResult(int[] w, int[] v, int bag) {
        if (null == w || null == v || w.length == 0 || v.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] w = new int[]{1, 3, 5, 7, 9};
        int[] v = new int[]{9, 7, 5, 3, 1};
        System.out.println(getResult(w, v, 10));
    }

}
