package com.jgs.jmh.leetCode.leetCode04_Hash;

import java.util.HashMap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/9 - 04 - 09 - 16:25
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 */
public class test44_twoSum {
    //hash记录下标位置
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int m = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (map.containsKey(nums[i]) && (target - nums[i] == nums[map.get(nums[i])])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        for (int i = 0; i < m; i++) {
            int elment = target - nums[i];
            if (map.containsKey(elment) && map.get(elment) != i) {
                result[0] = i;
                result[1] = map.get(elment);
                return result;
            }
        }
        return result;
    }
    //遍历一次  运用hash判断是否存在target - nums[i]
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        int m = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    //遍历一次  运用hash判断是否存在target - nums[i] 双指针遍历
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        int m = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0, right = m - 1;
        while(left <= right){
            if (map.containsKey(target - nums[left])) {
                result[0] = map.get(target - nums[left]);
                result[1] = left;
                return result;
            }
            map.put(nums[left], left);
            if (map.containsKey(target - nums[right])) {
                result[0] = map.get(target - nums[right]);
                result[1] = right;
                return result;
            }
            map.put(nums[right], right);
            left++;
            right--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] ints = twoSum(nums, target);
        int[] ints1 = twoSum1(nums, target);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
