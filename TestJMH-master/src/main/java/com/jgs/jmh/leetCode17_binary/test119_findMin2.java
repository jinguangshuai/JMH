package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/21 - 05 - 21 - 14:25
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 你必须尽可能减少整个过程的操作步骤。
 */
public class test119_findMin2 {
    public static int findMin(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //如果mid小于right，mid是最小值右侧的元素，因此我们可以忽略二分查找区间的右半部分。 说明最小值在mid的左侧，right = mid ,
            if (nums[mid] < nums[right]) {
                right = mid;
                //如果mid 大于right，mid是最小值左侧的元素，因此我们可以忽略二分查找区间的左半部分。说明最小值在mid的右侧，left = mid + 1;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
                //如果相等，则将右端点左移一位，替换新的右端点
            } else {
                right -= 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {10,1,10,10,10};
        System.out.println(findMin(nums));
    }
}
