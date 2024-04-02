package com.jgs.jmh.leetcodeArrayString;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/21 - 03 - 21 - 16:40
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 */
public class test17_romanToInt {

    public static int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 1) return map.get(String.valueOf(chars[0]));
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (map.get(String.valueOf(chars[i])) != getRule(chars, i, i + 1, map)) {
                result += getRule(chars, i, i + 1, map);
                if (i == n - 3) result += map.get(String.valueOf(chars[n - 1]));
                i = i + 1;
                if (i >= n - 1) return result;
            } else {
                result += map.get(String.valueOf(chars[i]));
                if (i == n - 2) result += map.get(String.valueOf(chars[n - 1]));
            }
        }
        return result;
    }
    public static int getRule(char[] chars, int index, int nextIndex, HashMap<String, Integer> map) {
        if (chars[index] == 'I') {
            if (chars[nextIndex] == 'V') {
                return 4;
            } else if (chars[nextIndex] == 'X') {
                return 9;
            }
        }
        if (chars[index] == 'X') {
            if (chars[nextIndex] == 'L') {
                return 40;
            } else if (chars[nextIndex] == 'C') {
                return 90;
            }
        }
        if (chars[index] == 'C') {
            if (chars[nextIndex] == 'D') {
                return 400;
            } else if (chars[nextIndex] == 'M') {
                return 900;
            }
        }
        return map.get(String.valueOf(chars[index]));
    }




    //官方做法
    static Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static int romanToInt2(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "MDCXCV";
        System.out.println(romanToInt(s));
    }
}
