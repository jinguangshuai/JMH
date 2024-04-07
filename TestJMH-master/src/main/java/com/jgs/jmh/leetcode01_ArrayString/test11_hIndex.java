package com.jgs.jmh.leetcode01_ArrayString;

import java.util.Arrays;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/19 - 03 - 19 - 10:31
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 *
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 */
public class test11_hIndex {
    //计数排序
    public static int hIndex(int[] citations) {
        //记录每个论文出现的次数  计数排序
        int len = citations.length;
        int[] cnt = new int[len + 1];
        for (int n : citations) {
            cnt[Math.min(n, len)]++;
        }
        int count = 0;
        for (int i = len; i > 0; i--) {
            count += cnt[i];
            //论文引用次数如果大于当前引用次数i的总论文数
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }

    //二分法解决h指数问题
    //h的取值是 len,len-1....，每次取中值就是假设mid作为h,
    //如果有cnt篇文章引用不少于h。那么判断cnt和此时h的大小。小了就往低找
    public static int hIndex2(int[] citations) {
        int left = 0;
        int right = citations.length;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int count = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= mid) {
                    count++;
                }
            }
            if (count >= mid) {
                left = mid;
            } else if (count < mid) {
                right = mid - 1;
            }
        }
        return left;
    }


    //贪心算法
    public static int hIndex3(int[] citations) {
        if (null == citations || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int m = citations.length;
        int h = 0;
        int mid = 0;
        for (int i = m -1; i >=0; i--) {
            if(citations[i] > mid){
                h++;
                mid++;
            }
        }
        return h;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,0,1,5,6};
        System.out.println(hIndex(arr));
    }
}
