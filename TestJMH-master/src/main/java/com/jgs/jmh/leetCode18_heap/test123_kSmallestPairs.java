package com.jgs.jmh.leetCode18_heap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/22 - 05 - 22 - 17:40
 * @Description:com.jgs.jmh.leetCode18_heap
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class test123_kSmallestPairs {
    //利用自下而上的小根堆构建方法，
    // 每次构建堆之后获取最小堆，然后交换到末尾，根据新的heapSize构建新的小根堆，依次循环直至获取k个最小堆
    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        List[] arr = new List[m * n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                arr[count] = list;
                count++;
            }
        }
        int heapSize = arr.length;
        buildMinHeap(arr, heapSize);
        while (k > 0) {
            result.add(arr[0]);
            swap(arr, 0, --heapSize);
            buildMinHeap(arr, heapSize);
            k--;
        }
        return result;
    }

    public static void buildMinHeap(List[] arr, int heapSize) {
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }
    }

    public static void heapInsert(List[] arr, int index) {
        while (getSum(arr[index]) < getSum(arr[(index - 1) / 2])) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static int getSum(List<Integer> list) {
        return list.get(0) + list.get(1);
    }

    public static void swap(List[] arr, int i, int j) {
        List temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //方法一：优先级队列
    //堆中保存下标对（i,j）即可能成为下一个数对的a的下标i和b的下标j
    //每次出堆后，可能成为下一个数对的是(i+1,j)、(i,j+1)
    public static List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        //int[]数组存储两个数组的下标对，构建小根堆
        //nums1[x[0]] + nums2[x[1]] - (nums1[y[0] + nums2[y[1]])
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            return nums1[x[0]] + nums2[x[1]] - (nums1[y[0]] + nums2[y[1]]);
        });
        List<List<Integer>> result = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        //每次出堆后，可能成为下一个数对的是(i+1,j)、(i,j+1)，但是可能会出现重复下标对
        //例如对于（0,1）下一个下标对为（1,1）（0,2），对于（1,0）下一个下标对为（1,1）（2,0）
        //为了避免这个问题，我们提前将nums1的前k个索引对进行记录（0,0）（1,0）（2,0）...（k-1,0）
        //每次取出元素（x，y）是，我们只需将nums2的索引增加即可
        for (int i = 0; i < Math.min(m, k); i++) {
            //提前记录前k个索引对
            queue.add(new int[]{i, 0});
        }
        while (k > 0 && !queue.isEmpty()) {
            int[] ids = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[ids[0]]);
            list.add(nums2[ids[1]]);
            result.add(list);
            if (ids[1] + 1 < n) {
                queue.add(new int[]{ids[0], ids[1] + 1});
            }
            k--;
        }
        return result;
    }

    public static List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        //二分查找第k小的数对和的大小
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        //寻找满足前k对的数对和，以及数对和的最大值
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long count = 0;
            int start = 0, end = n - 1;
            //滑动窗口
            //nums1指针右移，nums2指针左移
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    //寻找到小于等于中间数的所有数对
                    count += end + 1;
                    start++;
                }
            }
            //如果数对的和小于k对，说明mid的取值范围过小，无法满足前k对的要求，边界右移
            if (count < k) {
                left = mid + 1;
                //如果数对的和大于等于k对，说明mid的取值范围过大，边界需要左移
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        //找到小于目标值pairSum的数对
        int pos = n - 1;
        for (int i = 0; i < m; i++) {
            //首先寻找到第一个小于pairSum的值，这样nums1下标的值与nums2下标小于pos的值都可以满足要求
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            //遍历pos，并记录k的数对数量
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                result.add(list);
            }
        }
        //如果小于pairSum的数组对小于pairSum，那么在等于pairSum的数组对中寻找剩余的数组对
        //找到等于pairSum的数组对
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            int start1 = i;
            //寻找第一个不重复的nums1
            while (i < m - 1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            //nums1下标固定，取出nums1+nums2大于pairSum的下标
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            int start2 = pos;
            //寻找第一个不重复的nums2
            while (pos > 0 && nums2[pos] == nums2[pos - 1]) {
                pos--;
            }
            //如果两个值相加不等于pairSum，则结束当前循环
            if (nums1[i] + nums2[pos] != pairSum) {
                continue;
            }
            //计算当前满足数组对和等于pairSum的最小值
            int count = (int) Math.min(k, (long) (i - start1 + 1) * (start2 - pos + 1));
            for (int j = 0; j < count && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[pos]);
                result.add(list);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
//        List<List<Integer>> list = kSmallestPairs1(nums1, nums2, k);
        List<List<Integer>> list = kSmallestPairs3(nums1, nums2, k);
        System.out.println(list);
    }
}
