package com.mashibing.jmh.leetcodeArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/25 - 03 - 25 - 15:57
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你两个字符串 haystack 和 needle ，
 * * 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class test23_strStr {

    public static int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        int left = 0, right = 0;
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < m; i++) {
            if (haystack.charAt(i) == needle.charAt(left)) {
                right = i;
                while (haystack.charAt(right) == needle.charAt(left)) {
                    right++;
                    left++;
                    if (left == n) break;
                    if (right == m) break;
                }
                if (left == n) {
                    return i;
                } else {
                    left = 0;
                }
            }
        }
        return -1;
    }


    //kmp算法
    public static int strStr2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        // 计算模式串的next数组
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }


    public static int strStr3(String target, String p) {
        if (null == target || null == p) {
            return -1;
        }
        int m = target.length(), n = p.length();

        int[] next = new int[n];

        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = next[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = 0; i < m; i++) {
            while (j > 0 && target.charAt(i) != p.charAt(j)) {
                j = next[j - 1];
            }
            if (target.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        String haystack = "BBC ABCDAB ABCDABCDABDE";
        String needle = "ABCDABD";
        System.out.println(strStr2(haystack, needle));
        System.out.println(strStr3(haystack, needle));
    }
}
