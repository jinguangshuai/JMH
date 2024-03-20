package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/19 - 03 - 19 - 17:55
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */
public class test14_canCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int m = gas.length;
        int n = cost.length;
        for (int i = 0; i < m; i++) {
            int index = 0;
            while (index < n){


            }


            for (int j = i; j < n; j++) {

            }
        }
        return 0;
    }

    public static int ringIndex(int index, int size) {
        return index >= size - 1 ? 0 : index++;
    }
}
