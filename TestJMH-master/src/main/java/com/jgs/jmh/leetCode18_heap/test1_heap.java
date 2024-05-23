package com.jgs.jmh.leetCode18_heap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/22 - 05 - 22 - 11:20
 * @Description:com.jgs.jmh.leetCode18_heap
 * @version:1.0
 */

/**
 * * 堆基础知识
 */
public class test1_heap {
    //构建大根堆  自下而上
    public static void maxHeapInsert(int[] arr, int index) {
        int father = (index - 1) / 2;
        while (father >= 0) {
            if (arr[index] > arr[father]) {
                swap(arr, index, father);
                index = father;
                father = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    //构建大根堆
    public void buildMaxHeapInsert(int[] arr, int heapSize) {
        for (int i = 0; i < arr.length; i++) {
            maxHeapInsert(arr, i);
        }
    }

    //构建小根堆  自下而上
    public static void minHeapInsert(int[] arr, int index) {
        int father = (index - 1) / 2;
        while (father >= 0) {
            if (arr[index] < arr[father]) {
                swap(arr, index, father);
                index = father;
                father = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    //构建小根堆
    public void buildMinHeapInert(int[] arr, int heapSize) {
        for (int i = 0; i < arr.length; i++) {
            minHeapInsert(arr, i);
        }
    }

    //构建大根堆  自上而下
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

    //构建大根堆
    public void buildMaxHeapify(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }
    }

    //构建小根堆  自上而下
    public static void minHeapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1, right = index * 2 + 2, minimum = index;
        if (left < heapSize && arr[left] < arr[minimum]) {
            minimum = left;
        }
        if (right < heapSize && arr[right] < arr[minimum]) {
            minimum = right;
        }
        if (minimum != index) {
            swap(arr, index, minimum);
            minHeapify(arr, minimum, heapSize);
        }
    }

    //构建小根堆
    public void buildMinHeapify(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            minHeapify(arr, i, heapSize);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }



    //1.首先将排序的数组构造成一个大根堆，此时整个数组的最大值为堆结构的顶端
    //2.将顶端的数与末尾的数进行交换，此时，未尾的数为最大值，剩余待排序数组个数为n-1
    //3.将剩余的n-1个数再构造成大根堆，在将顶端的数与n-1位置的数交换，如此反复便能得到有序数组
    //注意升序使用大根堆，降序使用小根堆（默认为升序）
    // 堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N*logN)
        for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i); // O(logN)
        }
//		for (int i = arr.length - 1; i >= 0; i--) {
//			heapify(arr, i, arr.length);
//		}
        int heapSize = arr.length;
        //arr[0] 和 arr[arr.length-1]交换，0位置最大值和末尾交换，并且size减1
        swap(arr, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
    }
    //自下而上（相对于数组而言）
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // arr[index]位置的数，能否往下移动，自上而下（相对于数组而言）
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


}
