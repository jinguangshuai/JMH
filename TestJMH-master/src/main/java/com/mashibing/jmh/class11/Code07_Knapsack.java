package com.mashibing.jmh.class11;

public class Code07_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    // index... 最大价值
    public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) { //base case1
            return -1;
        }
        // 重量没超
        if (index == w.length) { //base case2
            return 0;
        }
        //主要判断第i位置取或者不取的问题
        //第i位置如果不取，则到i+1位置的其总价值为p1 = process(w, v, index + 1, alreadyW, bag)
        //第i位置如果取，则为第i位置的价值加上第i+1位置决策后的结果v[index] + process(w, v, index + 1, alreadyW + w[index], bag)
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);

    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    // 只剩下rest的空间了，
    // index...货物自由选择，但是不要超过rest的空间
    // 返回能够获得的最大价值
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest <= 0) { // base case 1
            return 0;
        }
        // rest >0
        if (index == w.length) { // base case 2
            return 0;
        }
        // 有货也有空间
        //主要判断第i位置取或者不取的问题
        //第i位置如果不取，则到i+1位置的其总价值为p1 = process(w, v, index + 1, rest);
        //第i位置如果取，则为第i位置的价值加上第i+1位置决策后的结果v[index] + process(w, v, index + 1, rest - w[index])
        int p1 = process(w, v, index + 1, rest);


        int p2 = Integer.MIN_VALUE;
        if (rest >= w[index]) {
            p2 = v[index] + process(w, v, index + 1, rest - w[index]);
        }

        //可修改为此种方式也可以
//		int p2Next = process(w, v, index + 1, rest - w[index]);
//		int p2 = -1;
//		if (p2Next != -1) {
//			p2 = v[index] + p2Next;
//		}

        return Math.max(p1, p2);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //当我的index等于终止位置时 dp[N][...] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {

            	//原始递归改为动态规划
//                int p1 = dp[index + 1][rest];
//                int p2 = -1;
//                if (rest - w[index] >= 0) {
//					p2 = v[index] + dp[index + 1][rest - w[index]];
//                }
//				dp[index][rest] = Math.max(p1,p2);


                //此时indexx位置不取货物
                dp[index][rest] = dp[index + 1][rest];
                //防止rest - w[index]小于0越界
                if (rest >= w[index]) {
                    dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }

}
