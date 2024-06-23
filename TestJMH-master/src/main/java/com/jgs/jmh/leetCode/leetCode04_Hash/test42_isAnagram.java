package com.jgs.jmh.leetCode.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/9 - 04 - 09 - 14:55
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.HashMap;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 */
public class test42_isAnagram {
    //双hash
    public static boolean isAnagram(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            if (sMap.containsKey(s.charAt(i)) && !tMap.containsKey(s.charAt(i))) {
                return false;
            }
            if (sMap.getOrDefault(s.charAt(i), 0) != tMap.getOrDefault(s.charAt(i), 0)) {
                return false;
            }
        }
        return true;
    }
    //双数组
    public static boolean isAnagram1(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        int[] arr = new int[26];
        for (int i = 0; i < m; i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    //toCharArray() 的速度比 charAt的速度更快！！！
    public static boolean isAnagram2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        int[] hash = new int[26];
        for (char c:s.toCharArray()) {
            hash[c - 'a']++;
        }
        for (char c:t.toCharArray()) {
            hash[c - 'a']--;
        }
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s= "aaa";
        String t = "bbb";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram1(s, t));
        System.out.println(isAnagram2(s, t));
    }
}
