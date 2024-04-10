package com.jgs.jmh.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/7 - 04 - 07 - 17:35
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;

/**
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
 * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 */
public class test40_isIsomorphic {
    //使用两个map，一个记录数量，一个记录位置，如果某个字符重复出现，则判断两个重复字符之间的距离是否相同
    public static boolean isIsomorphic(String s, String t) {
        if (null == s || s.length() == 0 || null == t || t.length() == 0) {
            return false;
        }
        int m = s.length(), n = t.length();
        if (m != n) return false;
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        HashMap<Character, Integer> sIndexMap = new HashMap<>();
        HashMap<Character, Integer> tIndexMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
            if (sMap.get(s.charAt(i)).intValue() != tMap.get(t.charAt(i)).intValue()) {
                return false;
            }
            if (sMap.get(s.charAt(i)) > 1) {
                if (i - sIndexMap.get(s.charAt(i)) != i - tIndexMap.get(t.charAt(i))) return false;
            }
            sIndexMap.put(s.charAt(i), i);
            tIndexMap.put(t.charAt(i), i);
        }
        return true;
    }

    //hash解法
    public static boolean isIsomorphic1(String s, String t) {
        if (null == s || s.length() == 0 || null == t || t.length() == 0) {
            return false;
        }
        if (s.length() != t.length()) return false;
        HashMap<Character, Character> sTot = new HashMap<>();
        HashMap<Character, Character> tTos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if ((sTot.containsKey(sChar) && sTot.get(sChar) != tChar) || (tTos.containsKey(tChar) && tTos.get(tChar) != sChar)) {
                return false;
            }
            sTot.put(sChar, tChar);
            tTos.put(tChar, sChar);
        }
        return true;
    }

    public static boolean isIsomorphic2(String s, String t) {
        if (null == s || s.length() == 0 || null == t || t.length() == 0) {
            return false;
        }
        if (s.length() != t.length()) return false;
        int[] sToT = new int[128];
        int[] tToS = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sToT[sChar] != tToS[tChar]) {
                return false;
            }
            sToT[sChar] = i + 1;
            tToS[tChar] = i + 1;
        }
        return true;
    }


    public static void main(String[] args) {
//        String s = "abcdefghijklmnopqrstuvwxyzva";
//        String t = "abcdefghijklmnopqrstuvwxyzck";

        String s = "aba";
        String t = "bab";
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphic1(s, t));
        System.out.println(isIsomorphic2(s, t));
    }
}
