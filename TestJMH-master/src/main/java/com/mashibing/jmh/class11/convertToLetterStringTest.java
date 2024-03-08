package com.mashibing.jmh.class11;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/28 - 02 - 28 - 19:48
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class convertToLetterStringTest {

    public static int getResult(String str) {
        if (null == str || str.length() == 0){
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }

        if (str[index] == '1') {
            int result = process(str, index + 1);
            if (index + 1 < str.length) {
                result += process(str, index + 2);
            }
            return result;
        }

        if (str[index] == '2') {
            int result = process(str, index + 1);
            if (index + 1 < str.length && str[index + 1] >= '0' && str[index + 1] <= '6') {
                result += process(str, index + 2);
            }
            return result;
        }
        return process(str, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(getResult("11111"));
    }
}
