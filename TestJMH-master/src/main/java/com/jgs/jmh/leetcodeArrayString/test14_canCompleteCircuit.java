package com.jgs.jmh.leetcodeArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/19 - 03 - 19 - 17:55
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 */
public class test14_canCompleteCircuit {

    //可用但是超时
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int m = gas.length;
        int n = cost.length;
        int result = -1;
        for (int i = 0; i < m; i++) {
            int index = i;
            int sum = gas[index];
            int count = 0;
            while (count <= n) {
                int nextIndex = ringIndex(index, m);
                if (sum >= cost[index] && sum >= 0) {
                    sum = sum - cost[index] + gas[nextIndex];
                    index = nextIndex;
                    count++;
                } else {
                    i = index;
                    break;
                }
            }
            if (count == (n + 1)) {
                result = i;
            }
        }
        return result;
    }
    public static int ringIndex(int index, int size) {
        return index >= size - 1 ? 0 : ++index;
    }


    //第二种思路
    //如果x到达不了y+1，那么x-y之间的点也不可能到达y+1，因为中间任何一点的油都是拥有前面的余量的，所以下次遍历直接从y+1开始
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    //第三种方法 贪心方法
    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0; i < gas.length; i++){
            sum = sum + gas[i] - cost[i];
            if(sum < min && sum < 0){
                min = sum;
                minIndex = i;
            }
        }
        if(sum < 0) return -1;
        return (minIndex + 1 )%gas.length;
    }


    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int i = canCompleteCircuit3(gas, cost);
        System.out.println(i);
    }
}
