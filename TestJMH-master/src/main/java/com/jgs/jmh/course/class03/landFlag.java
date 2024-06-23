package com.jgs.jmh.course.class03;

/**
 * @Auther：jinguangshuai
 * @Data：2023/8/18 - 08 - 18 - 11:30
 * @Description:com.mashibing.jmh.class03
 * @version:1.0
 */
public class landFlag {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] landFlagPartition(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R + 1;
        int index = L;
        int num = arr[R];
        while (index < more) {
            if (arr[index] < num) {
                swap(arr, index++, ++less);
            } else if (arr[index] == num) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
//        swap(arr, more, R);

        return new int[]{less + 1, more - 1};
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // < 区 的右边界
        int more = R;     // > 区 的左边界
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less); //index当前数  ++less小于区域右移一位的数，小于区域右扩一位  交换，交换完成index++
            } else { // >
                swap(arr, index, --more);   //index当前数  --more大于区域左移一位的数，大于区域左移一位   交换，交换完成index不变
            }
        }
        // L...Less   less+1...more-1   more...R-1  R
        // L...Less   less+1...more   more+1...R-1
        //R位置的数和当前大于区域左边界的第一个数,等同于R和more位置的数据做交换
        swap(arr, more, R);
        //less+1就是等于区域的第一个数   more就是等于区域的右边界
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] ints = landFlagPartition(new int[]{2, 3, 4, 6, 3}, 0, 4);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
