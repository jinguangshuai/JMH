package com.mashibing.jmh.leetCodeDoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/28 - 03 - 28 - 16:00
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

import java.util.HashSet;

/**
 * * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class test31_lengthOfLongestSubstring {

    //滑动窗口优化
    //如果从 i位置到j位置不重复，j+1位置存在重复字符，记录当前子串的长度
    //那么下一次遍历，可以确定起始位置i+1~j位置不重复，可以从j+1继续判断是否存在重复元素
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int m = s.length();
        int result = 0;
        int index = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (i > 0) {
                set.remove(s.charAt(i - 1));
            }
            while (index < m && !set.contains(s.charAt(index))) {
                set.add(s.charAt(index));
                index++;
            }
            result = Math.max(result, index - i);
        }
        return result;
    }


    public static int lengthOfLongestSubstring2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int m = s.length();
        HashSet<Character> set = new HashSet<>();
        int result = 0;
        int start = 0;
        for (int i = 0; i < m; i++) {
            if (i > 0 && set.contains(s.charAt(i - 1))) {
                set.remove(s.charAt(i - 1));
            }
            while (start < m && !set.contains(s.charAt(start))) {
                set.add(s.charAt(start));
                start++;
            }
            result = Math.max(result, start - i);
        }
        return result;
    }


    public static void main(String[] args) {
        String s = " ";
        System.out.println(lengthOfLongestSubstring2(s));
    }

}
