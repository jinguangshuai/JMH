package com.jgs.jmh.leetCode.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/10 - 04 - 10 - 10:30
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 */
public class test46_containsNearbyDuplicate {

    //hash表
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int m = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < m; i++) {
            if (map.containsKey(nums[i])) {
                if (k >= i - map.get(nums[i])) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    //滑动窗口
    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        int m = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < m ; i++){
            if(i > k){
                set.remove(nums[i-k-1]);
            }
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1};
        int k = 3;
        System.out.println(containsNearbyDuplicate(nums, k));
        System.out.println("---------------");
    }
}
