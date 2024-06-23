package com.jgs.jmh.leetCode.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/5 - 06 - 05 - 14:23
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 * * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class test148_maxProfit {

    //动态规划
    //任意一天结束之后，处于五种状态
    //（1）未进行任何操作（2）只进行一次买操作（3）进行一次买和卖操作（4）完成一笔交易，又进行一次买操作（5）完成两笔交易
    public static int maxProfit1(int[] prices) {
        int m = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < m; i++) {
            //第i天我们不进行任何操作，或者以price[i]的价格买入股票
            buy1 = Math.max(buy1, -prices[i]);
            //第i天我们不进行任何操作，或者在进行一次买的操作的前提下以price[i]的价格卖出
            sell1 = Math.max(sell1, buy1 + prices[i]);
            //第i天我们不进行任何操作，或者在完成一次交易的前提下在以price[i]的价格买入股票
            buy2 = Math.max(buy2, sell1 - prices[i]);
            //第i天我们不进行任何操作，或者在完成一次交易并重新买入股票的前提下以price[i]的价格卖出股票
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


    public static void main(String[] args) {
        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        System.out.println(maxProfit1(prices));
    }
}
