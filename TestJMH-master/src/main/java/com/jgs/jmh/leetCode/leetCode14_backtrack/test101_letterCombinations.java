package com.jgs.jmh.leetCode.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 9:22
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合*
 * * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class test101_letterCombinations {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return result;
        }
        String[] map = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        StringBuilder sb = new StringBuilder();
        process(digits, 0, result, map, sb);
        return result;
    }

    public static void process(String digits, int index, List<String> result, String[] map, StringBuilder sb) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        } else {
            int num = digits.charAt(index) - '2';
            String sympol = map[num];
            for (int i = 0; i < sympol.length(); i++) {
                sb.append(sympol.charAt(i));
                process(digits, index + 1, result, map, sb);
                sb.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "234";
        List<String> list = letterCombinations(digits);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
