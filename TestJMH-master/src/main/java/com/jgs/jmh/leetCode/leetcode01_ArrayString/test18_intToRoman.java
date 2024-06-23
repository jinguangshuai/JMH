package com.jgs.jmh.leetCode.leetcode01_ArrayString;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/21 - 03 - 21 - 17:30
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 */
public class test18_intToRoman {

    static Map<Integer, Character> symbolValues = new HashMap<Integer, Character>() {{
        put(1, 'I');
        put(5, 'V');
        put(10, 'X');
        put(50, 'L');
        put(100, 'C');
        put(500, 'D');
        put(1000, 'M');
    }};

    public static String intToRoman(int num) {
        int first = (int) (num / (Math.pow(10, 3)));
        int second = (int) (num / (Math.pow(10, 2))) - first * 10;
        int third = (int) (num / (Math.pow(10, 1))) - first * 100 - second * 10;
        int forth = (int) (num / (Math.pow(10, 0))) - first * 1000 - second * 100 - third * 10;
        StringBuilder result = new StringBuilder();
        if (first != 0) {
            for (int i = 0; i < first; i++) {
                result.append("M");
            }
        }
        if (second != 0) {
            if (second < 4) {
                for (int i = 0; i < second; i++) {
                    result.append("C");
                }
            }
            if (second == 4) result.append("CD");
            if (second > 4 && second < 9) {
                result.append("D");
                for (int i = 0; i < second - 5; i++) {
                    result.append("C");
                }
            }
            if (second == 9) result.append("CM");
        }
        if (third != 0) {
            if (third < 4) {
                for (int i = 0; i < third; i++) {
                    result.append("X");
                }
            }
            if (third == 4) result.append("XL");
            if (third > 4 && third < 9) {
                result.append("L");
                for (int i = 0; i < third - 5; i++) {
                    result.append("X");
                }
            }
            if (third == 9) result.append("XC");
        }
        if (forth != 0) {
            if (forth < 4) {
                for (int i = 0; i < forth; i++) {
                    result.append("I");
                }
            }
            if (forth == 4) result.append("IV");
            if (forth > 4 && forth < 9) {
                result.append("V");
                for (int i = 0; i < forth - 5; i++) {
                    result.append("I");
                }
            }
            if (forth == 9) result.append("IX");
        }

        return String.valueOf(result);
    }

    //解法一
    static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    //解法二
    static String[] thousands = {"", "M", "MM", "MMM"};
    static String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    static String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    static String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static String intToRoman3(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }


    public static void main(String[] args) {
        System.out.println(intToRoman(99));
    }

}
