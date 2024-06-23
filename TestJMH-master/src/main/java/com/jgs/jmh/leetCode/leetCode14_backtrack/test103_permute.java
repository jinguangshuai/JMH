package com.jgs.jmh.leetCode.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 14:55
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class test103_permute {
    //回溯法：一种通过探索所有可能的候选解来找出所有的解的算法。
    // 如果候选解被确认不是一个解（或者至少不是最后一个解），回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。

    //数字全排列回溯
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return result;
        }
        process(0, nums, result);
        return result;
    }
    public static void process(int index, int[] nums, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums){
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            process(index + 1, nums, result);
            swap(nums, i, index);
        }
    }
    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            List<Integer> list = permute.get(i);
            System.out.println("-----------");
            System.out.println(list);
        }
    }
}
