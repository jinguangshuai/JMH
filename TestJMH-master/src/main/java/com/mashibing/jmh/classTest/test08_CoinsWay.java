package com.mashibing.jmh.classTest;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 15:22
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test08_CoinsWay {
    public static int getResult(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(0, arr, aim);

    }

    public static int process(int index, int[] arr, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;

        }
        int result = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            result += process(index + 1, arr, rest - i * arr[index]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(getResult(arr, 6));
    }
}
