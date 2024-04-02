package com.jgs.jmh.class04;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Random;

/**
 * @Auther：jinguangshuai
 * @Data：2023/8/21 - 08 - 21 - 9:36
 * @Description:com.mashibing.jmh.class04
 * @version:1.0
 */
public class Test02 {

    public static int[] generateRandomInt(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (((maxSize + 1) * Math.random()) - (int) (maxSize * Math.random()));
            System.out.println(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        generateRandomInt(100, 100);
    }

    public static class MyMaxHeap {
        private int[] heap;
        private int heapSize;
        private final int limit;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full!");
            }
            heap[heapSize] = value;
            insertHeap(heap, heapSize++);
        }

        public void insertHeap(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void swap(int[] arr, int x, int y) {
            arr[x] = arr[x] ^ arr[y];
            arr[y] = arr[x] ^ arr[y];
            arr[x] = arr[x] ^ arr[y];
        }

        public int pop(int[] arr) {
            int ans = arr[0];
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);

            return ans;

        }

        public void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                // 左右两个孩子中，谁大，谁把自己的下标给largest
                // 右  ->  1) 有右孩子   && 2）右孩子的值比左孩子大才行
                // 否则，左
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
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

    public void insertHeap(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void swap(int[] arr, int x, int y) {
        arr[x] = arr[x] ^ arr[y];
        arr[y] = arr[x] ^ arr[y];
        arr[x] = arr[x] ^ arr[y];
    }

    public void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = ((left + 1) < heapSize) && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (arr[index] == arr[largest]) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public void sort(int[] arr) {
        if (arr.length == 0 || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            insertHeap(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }

    }


}
