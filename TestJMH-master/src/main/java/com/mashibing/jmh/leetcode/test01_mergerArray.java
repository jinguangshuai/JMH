package com.mashibing.jmh.leetcode;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/12 - 03 - 12 - 17:43
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */
public class test01_mergerArray {
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int a = m - 1, b = n - 1, c = m + n - 1;
        while (a >= 0 && b >= 0) {
            nums1[c--] = nums1[a] > nums2[b] ? nums1[a--] : nums2[b--];
        }
        while (a >= 0) {
            nums1[c--] = nums1[a--];
        }
        while (b >= 0) {
            nums1[c--] = nums2[b--];
        }
        return nums1;
    }


    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        int a = 0, b = 0;
        int c = 0;
        int[] sort = new int[m + n];
        while (a < m && b < n) {
            sort[c++] = nums1[a] > nums2[b] ? nums2[b++] : nums1[a++];
        }
        while (a < m) {
            sort[c++] = nums1[a++];
        }
        while (b < n) {
            sort[c++] = nums2[b++];
        }
        for (int i = 0; i < sort.length; i++) {
            nums1[i] = sort[i];
        }
        return nums1;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
//        int[] merge = merge(nums1, m, nums2, n);
//        for (int i = 0; i < merge.length; i++) {
//            System.out.println(merge[i]);
//        }

        int[] merge2 = merge2(nums1, m, nums2, n);
        for (int i = 0; i < merge2.length; i++) {
            System.out.println(merge2[i]);
        }
        System.out.println();
    }
}
