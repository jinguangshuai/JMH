package com.jgs.jmh.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/22 - 03 - 22 - 11:21
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 */

/**
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 */

/**
 * * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class test20_longestCommonPrefix {

    //基本做法
    public static String longestCommonPrefix(String[] strs) {
        int minStringLength = strs[0].length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            minStringLength = Math.min(minStringLength, strs[i].length());
        }
        for (int i = 0; i < minStringLength; i++) {
            boolean flag = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    flag = false;
                }
            }
            if (flag) {
                result.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }


    //暴力递归实现
    public static String longestCommonPrefix2(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        return process(strs, 0, strs.length - 1);
    }

    public static String process(String[] strings, int start, int end) {
        if (start == end) {
            return strings[start];
        }
        int mid = start + (end - start) / 2;
        String left = process(strings, 0, mid);
        String right = process(strings, mid + 1, end);
        return getCommonString(left, right);
    }

    public static String getCommonString(String left, String right) {
        int minLength = Math.min(left.length(), right.length());
        for (int i = 0; i < minLength; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, minLength);
    }


    //二分法
    public static String longestCommonPrefix3(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        int n = strs.length;
        int minLength = strs[0].length();
        for (int i = 1; i < n; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }

        int left = 0, right = minLength;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (isCommonString(strs, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return strs[0].substring(0, left);
    }

    public static boolean isCommonString(String[] strings, int mid) {
        //取出0到mid的字符串
        String str0 = strings[0].substring(0, mid);
        int count = strings.length;
        //遍历 1~count-1区间的字符串
        for (int i = 1; i < count; i++) {
            String str = strings[i];
            //判断mid之前的每个字符串是否相同
            for (int j = 0; j < mid; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String[] str = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(str));
        System.out.println(longestCommonPrefix2(str));
        System.out.println(longestCommonPrefix3(str));
    }
}
