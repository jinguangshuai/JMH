package com.jgs.jmh.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/18 - 03 - 18 - 15:01
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */
public class test10_canJump2 {

    public static int[] generate(int maxSize, int maxValue) {
        maxSize = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[maxSize];
        for (int i = 0; i < arr.length; i++) {
            int value = (int) (Math.random() * (maxValue + 1) - Math.random() * (maxValue));
            if (value > 0) {
                arr[i] = value;
            }
        }
        return arr;
    }

    //反向贪心算法
    public static int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    //正向贪心算法
    public static int jump2(int[] nums) {
        int length = nums.length;
        int end = 0; //上次跳跃可达范围右边界（下次的最右起跳点）
        int maxPosition = 0;
        int step = 0;
        for (int i = 0; i < length - 1; i++) {
            //每次确认最大的位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            //end 表示可以跳到的最远位置
            if (i == end) {
                end = maxPosition; //目前能跳到的最远位置变成了下次起跳位置的有边界
                step++;            // 进入下一次跳跃
            }
        }
        return step;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 0, 1, 1, 6};
        int[] generate = generate(20, 20);
        System.out.println(jump2(nums));
    }


}