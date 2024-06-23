package com.jgs.jmh.leetCode.leetCode16_Kandane;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/17 - 05 - 17 - 16:13
 * @Description:com.jgs.jmh.leetCode_Kandane
 * @version:1.0
 */

/**
 * * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组
 * 是数组中的一个连续部分。
 */
public class test112_maxSubArray {

    public static int maxSubArray1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int current = nums[0], global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            global = Math.max(current, global);
        }
        return global;
    }

    public static int maxSubArray2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    //线段树
    public static class Status {
        public int lSum;//以l为左端点的最大子段和
        public int rSum;//以r为右端点的最大子段和
        public int maxSum;//l-r内的最大字段和
        public int indexSum;//l-r内的区间和

        public Status(int lSum, int rSum, int maxSum, int indexSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.maxSum = maxSum;
            this.indexSum = indexSum;
        }
    }
    //方法二：分治
    public static int maxSubArray3(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).maxSum;
    }
    //二分
    public static Status getInfo(int[] nums, int left, int right) {
        if (left == right) return new Status(nums[left], nums[left], nums[left], nums[left]);
        int mid = (left + right) >> 1;
        Status lSub = getInfo(nums, left, mid);
        Status rSub = getInfo(nums, mid + 1, right);
        return pushUp(lSub, rSub);
    }
    public static Status pushUp(Status left, Status right) {
        int indexSum = left.indexSum + right.indexSum;
        //左区间的最大字段和等于  左区间的最大字段和  或者  左区间的区间和加上右区间的最大字段和lSum
        int lSum = Math.max(left.lSum, left.indexSum + right.lSum);
        //右区间的最大字段和等于  右区间的最大字段和  或者  右区间的区间和加上左区间的最大字段和rSum
        int rsum = Math.max(right.rSum, right.indexSum + left.rSum);
        //区间最大字段和为 左右区间最大字段和（没有跨越区间）  或者 左区间的右端点字段和+右区间的左端点字段和（跨越区间）
        int maxSum = Math.max(Math.max(left.maxSum, right.maxSum), left.rSum + right.lSum);
        return new Status(lSum, rsum, maxSum, indexSum);
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1};
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray2(nums));
        System.out.println(maxSubArray3(nums));
    }
}
