package com.jgs.jmh.leetCode.leetCode05_interval;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/11 - 04 - 11 - 8:54
 * @Description:com.jgs.jmh.leetCode05_interval
 * @version:1.0
 */

import java.util.*;

/**
 * 以数组 intervals 表示若干个区间的集合，
 * 其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class test49_merge {
    public static int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][2];
        }
        //区间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            //merged.get(merged.size() - 1)返回值为一维数组
            //如果不能合并，则新增一维数组
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                //合并，右侧设置为最大的区间
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static int[][] merge1(int[][] intervals) {
        if(null == intervals || intervals.length == 0 || intervals[0].length == 0){
            return new int[0][2];
        }
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1,int[] o2){
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            int left = intervals[i][0],right = intervals[i][1];
            if(list.size() == 0 || list.get(list.size()-1)[1] < left){
                list.add(new int[]{left,right});
            }else{
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1,4}, {0,4}};
        int[][] intervals = {{1,4}, {0,2}, {3,5}};
        int[][] merge = merge(intervals);
        System.out.println("------------");
    }

}
