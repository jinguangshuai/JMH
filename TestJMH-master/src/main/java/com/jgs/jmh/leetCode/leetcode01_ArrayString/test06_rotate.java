package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/15 - 03 - 15 - 12:18
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 */
public class test06_rotate {

    //不占用额外的空间，但是太慢
    public static void rotate(int[] nums, int k) {
        if (null == nums || nums.length == 0) {
            return;
        }
        int m = nums.length;
        if (k % m != 0) {
            int n = k % m;
            for (int j = 0; j < n; j++) {
                int temp = nums[m - 1];
                for (int i = m - 1; i > 0; i--) {
                    nums[i] = nums[i - 1];
                }
                nums[0] = temp;
            }
        }
    }

    //环形数组
    public static void rotate2(int[] nums, int k) {
        if (null == nums || nums.length == 0) {
            return;
        }
        int m = nums.length;
        if (k % m != 0) {
            int n = k % m;
            int count = 0;
            for (int i = 0; i < m & count < m; i++) {
                //开始0位置
                int current = i;
                //当前位置的值
                int preValue = nums[i];
                do {
                    //计算下一个需要替换的位置
                    int next = (current + n) % m;
                    //下一个位置的值记为临时变量
                    int temp = nums[next];
                    //将前位置的值赋值给下一位置的值
                    nums[next] = preValue;
                    //下一位置的值作为前临时值
                    preValue = temp;
                    //下一位置值作为当前位置
                    current = next;
                    count++;
                } while (i != current);
            }
        }
    }


    //反转数组
    public void rotate3(int[] nums, int k) {
        // 处理k大于数组长度的情况
        k = k % nums.length;
        // 反转整个数组
        reverse(nums, 0, nums.length - 1);
        // 反转数组的前k个元素
        reverse(nums, 0, k - 1);
        // 反转数组的剩余部分
        reverse(nums, k, nums.length - 1);
    }

    // 反转数组指定区间内的元素
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static int[] generate(int maxSize, int maxValue) {

        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1) - Math.random() * (maxValue));
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

//        int[] nums = generate(20, 20);
        rotate2(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
