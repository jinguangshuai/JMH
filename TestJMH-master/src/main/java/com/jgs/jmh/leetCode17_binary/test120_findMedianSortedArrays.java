package com.jgs.jmh.leetCode17_binary;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/21 - 05 - 21 - 14:45
 * @Description:com.jgs.jmh.leetCode17_binary
 * @version:1.0
 */

/**
 * * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class test120_findMedianSortedArrays {
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        if (total % 2 == 1) {
            int midIndex = total / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = total / 2 - 1, midIndex2 = total / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) +
                    getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            //index1越界
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            //index2越界
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            // k = 1的情况
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            //正常情况
            int half = k / 2;
            //计算k/2-1的位置
            int nextIndex1 = Math.min(index1 + half, length1) - 1;
            int nextIndex2 = Math.min(index2 + half, length2) - 1;
            //nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
            //nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个

            int pivot1 = nums1[nextIndex1], pivot2 = nums2[nextIndex2];
            //取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
            //这样 pivot 本身最大也只能是第 k-1(下标为k-1) 小的元素

            //如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。
            // 把这些元素全部 "删除"，剩下的作为新的 nums1 数组
            //由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
            if (pivot1 <= pivot2) {
                //相当于k-k/2  nextIndex1 = k/2 - 1
                //nextIndex1 - index1 + 1 = k/2 - 1 - index1 + 1 = k/2 - index1
                k -= (nextIndex1 - index1 + 1);
                //新的nums1数组下标从 k/2-1 + 1 = k/2开始
                index1 = nextIndex1 + 1;
                //如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。
                // 把这些元素全部 "删除"，剩下的作为新的 nums2 数组
            } else {
                //相当于k-k/2  nextIndex2 = k/2 - 1
                //nextIndex2 - index2 + 1 = k/2 - 1 - index2 + 1 = k/2 - index2
                k -= (nextIndex2 - index2 + 1);
                //新的nums2数组下标从 k/2-1 + 1 = k/2开始
                index2 = nextIndex2 + 1;
            }
        }
    }


    //方法二：划分数组
    //中位数定义：将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。
    //nums1, nums2中的所有元素已经被划分为相同长度的两个部分，
    // 且前一部分中的元素总是小于或等于后一部分中的元素。中位数就是前一部分的最大值和后一部分的最小值的平均值：
    //要保证nums1的长度小于nums2，否则(m + n + 1) / 2 - i的值有可能为负值
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        // m为长度较小的数组，n为长度较大的数组  m<n
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;
        while (left <= right) {
            //前一部分包含 nums1[0...i-1]和nums2[0...j-1]
            //后一部分包含 nums1[i...m-1]和nums2[j...n]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            // nums_im1, nums_i, nums_jm1, nums_j 分别表示
            // nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            //如果左边界越界，则-1位置为负无穷
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            //如果有边界越界，则length位置为正无穷
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);
            //如果左部分的nums1的 i-1位置的值小于等于 右部分 nums2的值，则说明左部分的nums1和nums2都小于右部分的值
            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        //中位数的值为前一部分的最大值加上后一部分的最小值的平均值
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 9};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(findMedianSortedArrays1(nums1, nums2));
    }
}
