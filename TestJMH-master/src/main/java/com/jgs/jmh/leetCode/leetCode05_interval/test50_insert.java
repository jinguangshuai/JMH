package com.jgs.jmh.leetCode.leetCode05_interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/11 - 04 - 11 - 14:34
 * @Description:com.jgs.jmh.leetCode05_interval
 * @version:1.0
 */

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi]
 * 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。
 * 同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
 *
 * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
 *
 * 返回插入之后的 intervals。
 *
 * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
 */
public class test50_insert {

    //和官方解决思路一致
    // （1）新增区间在左边界（2）新增区间在中间：一、中间区间相交，合并区间；二、中间区间不相交，新增集合（3）新增区间在右边界
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (null == intervals || intervals.length == 0 || intervals[0].length == 0) {
            if (null != newInterval && newInterval.length != 0) {
                return new int[][]{{newInterval[0], newInterval[1]}};
            }
            return new int[0][2];
        }
        if (null == newInterval || newInterval.length == 0) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            //开始遍历就不相交
            if (flag && newInterval[1] < intervals[0][0]) {
                list.add(new int[]{newInterval[0], newInterval[1]});
                flag = false;
            }
            //处于相交状态
            if (flag && !(right < newInterval[0] || left > newInterval[1])) {
                left = Math.min(left, newInterval[0]);
                right = Math.max(right, newInterval[1]);
                list.add(new int[]{left, right});
                flag = false;
                //遍历过程中两两不相交
            } else if (flag && i + 1 < intervals.length && newInterval[0] > right && newInterval[1] < intervals[i + 1][0]) {
                list.add(new int[]{left, right});
                list.add(new int[]{newInterval[0], newInterval[1]});
                flag = false;
            }
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        //遍历到最后不相交
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            list.add(new int[]{newInterval[0], newInterval[1]});
        }
        return list.toArray(new int[list.size()][]);
    }

    //官方解法
    // （1）新增区间在左边界（2）新增区间在中间：一、中间区间相交，合并区间；二、中间区间不相交，新增集合（3）新增区间在右边界
    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        //整个区间都无相交，在最后添加新的区间
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        return ansList.toArray(new int[ansList.size()][]);
//        int[][] ans = new int[ansList.size()][2];
//        for (int i = 0; i < ansList.size(); ++i) {
//            ans[i] = ansList.get(i);
//        }
//        return ans;
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {6, 9}};
//        int[] newInterval = {2, 5};
//        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
//        int[] newInterval = {4, 8};
        int[][] intervals = {{2, 3}, {6, 7}, {8, 9}};
        int[] newInterval = {4, 5};
        int[][] insert = insert(intervals, newInterval);
        for (int i = 0; i < insert.length; i++) {
            for (int j = 0; j < insert[0].length; j++) {
                System.out.println(insert[i][j]);
            }
        }
    }
}
