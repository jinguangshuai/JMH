package com.mashibing.jmh.leetcode;

import java.util.HashMap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 9:57
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */
public class test05_majorityElement {


    //时间复杂度为n^2，空间复杂度为0（1）
    public static int majorityElement(int[] nums) {
        int n = nums.length / 2;
        int k = 0;
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    k++;
                }
            }
            if (k > n) {
                result = i;
                break;
            } else {
                k = 0;
            }
        }
        return nums[result];

    }

    //时间复杂度为o（n），空间复杂度为0（n）
    public static int majorityElement2(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) > nums.length / 2) {
                    result = nums[i];
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return result;
    }


    public static int majorityElement3(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int m = nums[0];
        int time = 1;

        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == m){
                time++;
            }else {
                time--;
                if(time == 0){
                    m = nums[i];
                    time = 1;
                }
            }
        }
        return m;
    }






    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement3(nums));
    }
}
