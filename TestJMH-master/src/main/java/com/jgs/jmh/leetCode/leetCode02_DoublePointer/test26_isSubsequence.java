package com.jgs.jmh.leetCode.leetCode02_DoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/26 - 03 - 26 - 15:25
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 */
public class test26_isSubsequence {

    //双指针
    public static boolean isSubsequence(String s, String t) {
        if (null == s || s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }
        int m = s.length(), n = t.length(), left = 0, right = 0;
        while (left < m && right < n) {
            if (s.charAt(left) == t.charAt(right)) {
                left++;
                right++;
            } else {
                right++;
            }
            if (left == m) {
                return true;
            }
        }
        return false;
    }

    public static List<String> generate(String target) {
        if (null == target || target.length() == 0) {
            return new ArrayList();
        }
        List<String> list = new ArrayList<>();
        return list;
    }

    public static void process(String s, int index, List<String> list, String path) {
        if (index == s.length()) {
            list.add(path);
            return;
        }
        process(s, index + 1, list, path);
        process(s, index + 1, list, path + String.valueOf(s.charAt(index)));
    }


    public static boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();
        //dp数组dp[i][j]表示字符串t以i位置开始第一次出现字符j的位置
        int[][] f = new int[m + 1][26];
        //初始化边界条件，dp[i][j] = m表示t中不存在字符j
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }
        //从后往前递推初始化dp数组
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {

                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            //t中没有s[i] 返回false
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            //否则直接跳到t中s[i]第一次出现的位置之后一位
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "agc";
        String t = "habgdc";
        System.out.println(isSubsequence3(s, t));
    }
}
