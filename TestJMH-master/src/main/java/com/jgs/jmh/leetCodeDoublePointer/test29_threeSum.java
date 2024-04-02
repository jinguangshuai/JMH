package com.jgs.jmh.leetCodeDoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/27 - 03 - 27 - 16:45
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class test29_threeSum {
    //排序 + 双指针解法
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int m = nums.length;
        for (int i = 0; i < m; i++) {
            int left = i + 1, right = m - 1;
            //如果nums[i] = nums[i-1]，说明该数字重复，会导致结果重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //如果nums[i]>0，则后续所有数字都大于0，无法得出三数之和为0的答案
            if (nums[i] > 0) {
                return result;
            }
            //双指针，左指针位于第一位数字之后，右指针位于最后一位
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> elment = new ArrayList<>();
                    elment.add(nums[i]);
                    elment.add(nums[left]);
                    elment.add(nums[right]);
                    result.add(elment);
                    //当且仅当 三数之和为0的时候，如果右指针和前一位数字相同时，会导致结果重复，需要右指针左移
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    //当且仅当 三数之和为0的时候，如果左指针和后一位数字相同时，会导致结果重复，需要左指针右移
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                }
            }
        }
        return result;
    }

    //排序 + 双指针解法  官方解法
    public static List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            // 枚举 b
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] + nums[first] > 0) {
                    third--;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    //对数器
    public static int[] generate(int maxSize, int maxValue) {
        maxSize = (int) (Math.random() * (maxSize + 1));
        int[] result = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return result;
    }


    public static void main(String[] args) {
        int[] generate = generate(100, 30);


        int[] nums = {0, 0, 0, 0, 0};
        List<List<Integer>> lists = threeSum(generate);
        List<List<Integer>> lists2 = threeSum2(generate);
        System.out.println(lists);
        System.out.println(lists2);
    }
}
