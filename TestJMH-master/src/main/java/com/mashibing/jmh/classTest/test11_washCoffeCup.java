package com.mashibing.jmh.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/12 - 03 - 12 - 15:15
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test11_washCoffeCup {

    public static int getResult(int[] arr, int a, int b, int washLine) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        return 0;

    }

    public static int process(int index, int[] arr, int a, int b, int washLine) {
        //当来到最后一杯咖啡的时候
        if (index == arr.length - 1) {
            return Math.min(Math.max(arr[index], washLine) + a, arr[index] + b);
        }
        //洗干净的时间
        int wash = Math.max(arr[index], washLine) + a;
        int next1 = process(index + 1, arr, a, b, wash);
        int p1 = Math.max(wash, next1);

        //自然晾干的时间
        int dry = arr[index] + b;
        int next2 = process(index + 1, arr, a, b, washLine);
        int p2 = Math.max(dry, next2);
        return Math.min(p1, p2);

    }
}
