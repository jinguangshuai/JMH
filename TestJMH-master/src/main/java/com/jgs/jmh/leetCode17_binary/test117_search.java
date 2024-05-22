package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/20 - 05 - 20 - 16:19
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class test117_search {
    public static int search1(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            //如果nums[left] <= nums[mid]
            if(nums[left] <= nums[mid]){
                //如果nums[left] <= target && nums[mid] > target 说明左侧为单调递增区间
                if(nums[left] <= target && nums[mid] > target){
                    right = mid - 1;
                    //否则的话，右移
                }else {
                    left = mid + 1;
                }
            }else {
                //nums[mid] < target && nums[right] >= target 说明右侧为单调递增区间
                if(nums[mid] < target && nums[right] >= target){
                    left = mid + 1;
                    //否则的话左移
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {5,1,2,3,4};
        int target = 1;
        System.out.println(search1(nums, target));
    }
}
