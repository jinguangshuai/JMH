package com.jgs.jmh.leetcodeArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/20 - 03 - 20 - 11:06
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

import java.util.Arrays;
import java.util.Random;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class test15_candy {
    //原始解法 不推荐，时间太长
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                arr[i] = arr[i - 1] + 1;
                if (i + 1 != n && ratings[i] < ratings[i + 1]) {
                    arr[i + 1] = arr[i] + 1;
                }
            } else if (ratings[i] < ratings[i - 1]) {
                if (arr[i - 1] <= arr[i]) {
                    arr[i - 1] = arr[i] + 1;
                }
                int index = i;
                while (index - 2 >= 0) {
                    if (ratings[index - 1] < ratings[index - 2] && arr[index - 2] <= arr[index - 1]) {
                        arr[index - 2] = arr[index - 1] + 1;
                        index--;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        return sum;
    }


    /**
     * 分两个阶段
     * 1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
     * 2、起点下标 ratings.length - 2 从右往左，
     * * 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，
     * * 这样才符合 它比它左边的大，也比它右边大
     */
    public static int candy2(int[] ratings) {
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        int right = 0;
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            result += Math.max(candy[i], right);
        }
        return result;
    }

    //空间复杂度为0（1）最佳解法
    public static int candy3(int[] ratings) {
        int n = ratings.length;
        int ret = 1;    //用于记录答案
        //pre用于记录前一个同学分得的糖果数量
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                //处于递增序列中
                dec = 0;    //递减序列长度在递增序列中始终为0
                //当前同学和上一个同学分数相等时，直接分配1个就行，这样满足最小
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;//inc用于记录上一个递增序列的长度

            } else {
                //处于递减序列中
                dec++;
                if (dec == inc) {
                    //当递减序列长度和递增序列长度相等时，把递增序列的最后一个同学分配到递减序列中
                    dec++;
                }
                ret += dec; //这里加的dec相当于把递减序列翻转后加的每个同学的糖果数量
                pre = 1;    //递减数列的最后一个值一定分配为1
            }
        }
        return ret;

    }

    public static int candy4(int[] ratings) {
        int result = 0;
        int n = ratings.length;

        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }else {
                left[i] = 1;
            }
        }
        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }else {
                right[i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            result += Math.max(left[i], right[i]);
        }
        return result;

    }

    public static int[] generate(int maxSize,int maxValue){
        maxSize = new Random().nextInt(maxSize);
        int[] arr = new int[maxSize+1];
        for (int i = 0; i < maxSize+1; i++) {
            arr[i] = new Random().nextInt(maxValue);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] ratings = {3, 2, 1, 1, 4, 3, 3};

        for (int i = 0; i < 10; i++) {
            int[] generate = generate(10, 10);
            System.out.println(candy3(generate));
            System.out.println(candy4(generate));
        }

    }

}
