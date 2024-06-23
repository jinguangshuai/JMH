package com.jgs.jmh.leetCode.leetCode18_heap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/22 - 05 - 22 - 9:31
 * @Description:com.jgs.jmh.leetCode18_heap
 * @version:1.0
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class test121_findKthLargest {

    public static class comp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    //优先级队列，建立大根堆
    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = queue.poll();
        }
        return nums[k - 1];
    }

    //构建大根堆
    public static int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        //构建大根堆
        buildMaxHeap(nums, heapSize);
        //做 k-1次排序，则最后k-1个数字为前k-1个大数字，当前nums[0]为第k大元素
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public static void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }
    }

    //自下而上 建立大根堆
    public static void maxHeapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1, right = index * 2 + 2, largest = index;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != index) {
            swap(arr, index, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }


    //建立大根堆，获取前k-1个数字，最后返回nums[0]
    public static int findKthLargest3(int[] nums, int k) {
        int heapSize = nums.length;
        buildHeap3(nums);
        for (int i = nums.length - 1; i >= nums.length - (k - 1); i--) {
            swap(nums, 0, i);
            --heapSize;
            heapify3(nums, 0, heapSize);//注意每次是和0位置进行交换
        }
        return nums[0];
    }

    public static void buildHeap3(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    public static void heapify3(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index)
                break;
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }


    public static int findKthLargest4(int[] nums, int k) {
        int n = nums.length;
        //只要某次划分的q为倒数第k个下标的时候，就可以确定答案
        //q位置为arr[q]大于等于 arr[l..q-1]，并且小于等于a[q+1...r]的元素
        return quickSort(nums, 0, n - 1, n - k);
    }

    public static int quickSort(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[k];
        }
        int x = nums[left], i = left - 1, j = right + 1;
        while (i < j) {
            do {
                i++;
            } while (nums[i] < x);

            do {
                j--;
            } while (nums[j] > x);

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        //如果k小于目标下标，说明在左区间范围内，就递归左区间
        if (k <= j) {
            return quickSort(nums, left, j, k);
        } else {
            return quickSort(nums, j + 1, right, k);
        }
    }


    //从下往上 构建大根堆
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //从上往下 构建大根堆
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) break;
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
//        int kthLargest = findKthLargest1(nums, k);
        System.out.println(findKthLargest4(nums,k));
//        System.out.println(kthLargest);
    }
}
