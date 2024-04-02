package com.jgs.jmh.leetCodeDoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/27 - 03 - 27 - 10:54
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 */
public class test27_twoSum {

    public static int[] twoSum1(int[] numbers, int target) {
        if (null == numbers || numbers.length == 0) {
            return new int[2];
        }
        int[] result = new int[2];
        int left = 0, right = 1;
        int m = numbers.length;
        while (left < m) {
            while (numbers[left] + numbers[m - 1] < target) {
                left++;
                right++;
            }
            if (right > m - 1) {
                left++;
                if (left < m - 1) {
                    right = left + 1;
                } else {
                    right = m - 1;
                }
            }
            if (left > m - 1) {
                return result;
            }
            if (numbers[left] + numbers[right] == target) {
                if (right == m - 1) {
                    result[0] = left + 1;
                    result[1] = m;
                } else {
                    result[0] = left + 1;
                    result[1] = right + 1;
                }
                return result;
            }
            right++;
        }
        return result;
    }

    //双指针做法
    public static int[] twoSum2(int[] numbers, int target) {
        if (null == numbers || numbers.length == 0) {
            return new int[2];
        }
        int[] result = new int[2];
        int m = numbers.length;
        int left = 0, right = m - 1;

        while (left < m) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }else if(sum > target){
                right--;
            }else {
                left++;
            }
        }
        return result;
    }

    //二分法
    //在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，
    // 第二个数等于目标值减去第一个数的差。利用数组的有序性质，可以通过二分查找的方法寻找第二个数。
    // 为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
    //
    public static int[] twoSum3(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] numbers = {3,24,50,79,88,150,345};
        int[] ints = twoSum3(numbers, 200);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
