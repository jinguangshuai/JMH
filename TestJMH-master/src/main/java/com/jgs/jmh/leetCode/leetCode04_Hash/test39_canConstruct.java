package com.jgs.jmh.leetCode.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/7 - 04 - 07 - 17:12
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.HashMap;

/**
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class test39_canConstruct {
    //hashmap计算每个字符的次数
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (null == ransomNote || ransomNote.length() == 0 || null == magazine || magazine.length() == 0) {
            return false;
        }
        int m = ransomNote.length(), n = magazine.length();
        HashMap<Character, Integer> magazineMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            magazineMap.put(magazine.charAt(i), magazineMap.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            if (!magazineMap.isEmpty() && magazineMap.containsKey(ransomNote.charAt(i))) {
                magazineMap.put(ransomNote.charAt(i), magazineMap.getOrDefault(ransomNote.charAt(i), 0) - 1);
                if (magazineMap.get(ransomNote.charAt(i)) == 0) {
                    magazineMap.remove(ransomNote.charAt(i));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean canConstruct1(String ransomNote, String magazine) {
        int[] t = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            t[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if ((t[c - 'a'] <= 0)) {
                return false;
            }
            t[c - 'a']--;
        }
        return true;
    }
    //无需额外空间解法
    //判断每个字符是否存在，如果存在则索引右移一位，避免下次查询相同字符，重复使用相同的索引
    public static boolean canConstruct2(String ransomNote, String magazine) {
        if (null == ransomNote || ransomNote.length() == 0 || null == magazine || magazine.length() == 0) {
            return false;
        }
        int[] arr = new int[26];
        for (char c : ransomNote.toCharArray()) {
            int i = magazine.indexOf(c, arr[c - 'a']);
            if (i == -1) {
                return false;
            }
            arr[c - 'a'] = i + 1;
        }
        return true;
    }



    public static void main(String[] args) {
        String ransomNote = "aaa";
        String magazine = "aab";
        System.out.println(canConstruct1(ransomNote, magazine));
    }
}
