package com.mashibing.jmh.leetCodeDoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/27 - 03 - 27 - 15:20
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 */
public class test28_maxArea {

    //双重for循环计算最大水量
    public static int maxArea(int[] height) {
        int m = height.length;
        int max = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                int width = j - i;
                int heighth = Math.min(height[i], height[j]);
                max = Math.max(max, width * heighth);
            }
        }
        return max;
    }

    //双指针计算最大水量  优化版本
    public static int maxArea2(int[] height) {
        int m = height.length;
        int left = 0, right = m - 1;
        int midMax = 0;
        while (left < right) {
            midMax = Math.max(midMax, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return midMax;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(height));
    }

}
