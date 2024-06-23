package com.jgs.jmh.leetCode.leetCode05_interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/10 - 04 - 10 - 15:12
 * @Description:com.jgs.jmh.leetCode05_interval
 * @version:1.0
 */

/**
 * 给定一个  无重复元素 的 有序 整数数组 nums 。返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，
 * 并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 */
public class test48_summaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return result;
        }
        int m = nums.length;
        int left = 0, right = 0;
        while (right < m) {
            while (right + 1 < m && nums[right] + 1 == nums[right + 1]) {
                right++;
            }
            if (left == right) {
                result.add(String.valueOf(nums[left]));
            } else {
                result.add(nums[left] + "->" + nums[right]);
            }
            right++;
            left = right;
        }
        return result;
    }


    //遍历数组，双指针，low和fast标识区间起终点，判断条件与上一个元素差值为1
    public static List<String> summaryRanges1(int[] nums) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < nums.length) {
            int slow = index;
            index++;
            while (index < nums.length && nums[index] == nums[index - 1] + 1){
                index++;
            }
            int fast = index - 1;
            StringBuilder sb = new StringBuilder(Integer.toString(nums[slow]));
            if(slow < fast){
                sb.append("->");
                sb.append(Integer.valueOf(nums[fast]));
            }
            result.add(sb.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 3, 4, 6, 8, 9};
        List<String> list = summaryRanges(nums);
        List<String> list1 = summaryRanges1(nums);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
