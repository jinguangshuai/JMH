package com.jgs.jmh.leetCode05_interval;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/12 - 04 - 12 - 9:58
 * @Description:com.jgs.jmh.leetCode05_interval
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend]
 * * 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，
 * * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。
 * * 可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 */
public class test51_findMinArrowShots {

    //使用额外空间，利用右边界进行排序，之后合并区间，最后返回区间的长度
    public static int findMinArrowShots(int[][] points) {
        if (null == points || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1]){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int left = points[i][0], right = points[i][1];
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                if (list.get(list.size() - 1)[1] >= left) {
                    list.get(list.size() - 1)[1] = Math.min(list.get(list.size() - 1)[1], right);
                }else{
                    list.add(new int[]{left, right});
                }
            }
        }
        return list.size();
    }

    //  无需额外空间，对区间右端进行排序，
    // 然后判断是否在一个区间，是否下一个区间的左边界在与之前区间的右边界相交
    // 如果不相交，更新新的前置区间  pre
    public static int findMinArrowShots1(int[][] points) {
        if (null == points || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //重要，如果使用 o1[1] - o2[1]<0 会出现数字越界问题
                if(o1[1] < o2[1]){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        int result = 1;
        int pre = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if(points[i][0] > pre){
                result++;
                pre = points[i][1];
            }
        }
        return result;
    }



    public static void main(String[] args) {
//        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int[][] points = {{-2147483646,-2147483645}, {2147483646,2147483647}};
        System.out.println(findMinArrowShots(points));
        System.out.println(findMinArrowShots1(points));
    }

}
