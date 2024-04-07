package com.jgs.jmh.leetCode02_DoublePointer;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/29 - 03 - 29 - 17:22
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
public class test33_minWindow {
    // s 字符串  t 待匹配字符串 判断s是否包含t
    //返回 s 中涵盖 t 所有字符的最小子串
    public static String minWindow1(String s, String t) {
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        int m = s.length(), n = t.length();
        int l = 0, r = -1;
        int ansL = -1, ansR = -1;
        int min = Integer.MAX_VALUE;
        //初始化待匹配字符串
        for (int i = 0; i < n; i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        while (r < m) {
            r++;
            //s的字符是否是 t所需，如果是，存储到新的cnt中
            if (r < m && tMap.containsKey(s.charAt(r))) {
                sMap.put(s.charAt(r), sMap.getOrDefault(s.charAt(r), 0) + 1);
            }
            //判断cnt中所有key是否可组成t
            while (check(sMap, tMap) && l <= r) {
                //判断是否为长度最小的字符串
                if (r - l + 1 < min) {
                    min = r - l + 1;
                    ansL = l;
                    ansR = l + min;
                }
                if (sMap.containsKey(s.charAt(l))) {
                    sMap.put(s.charAt(l), sMap.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public static boolean check(HashMap<Character, Integer> sMap, HashMap<Character, Integer> tMap) {
        Iterator iterator = tMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Character ch = (Character) entry.getKey();
            Integer in = (Integer) entry.getValue();
            if (sMap.getOrDefault(ch, 0) < in) {
                return false;
            }
        }
        return true;
    }

    //优化版本
    public static String minWindow2(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] tNeed = new int[128];
        int[] sHave = new int[128];

        //将目标字符串指定字符的出现次数记录，目标为t
        for (int i = 0; i < t.length(); i++) {
            tNeed[t.charAt(i)]++;
        }
        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
        int left = 0, right = 0, min = s.length() + 1, count = 0, start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            //说明该字符不被目标字符串需要，此时有两种情况
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            // 2.循环已经开始一段时间，此处又有两种情况
            //  2.1(循环走到某个位置，还未完全匹配字符串) 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
            //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
            //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
            if (tNeed[r] == 0) { //过滤不需要的字符
                right++;
                continue;
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (sHave[r] < tNeed[r]) {
                count++;
            }
            //已有字符串中目标字符出现的次数+1
            sHave[r]++;
            //移动右指针
            right++;
            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                if (tNeed[l] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (sHave[l] == tNeed[l]) {
                    count--;
                }
                //已有字符串中目标字符出现的次数-1
                sHave[l]--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) {
            return "";
        }
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }

    public static String minWindow3(String s, String t) {
        int[] sHave = new int[128];
        int[] tNeed = new int[128];
        int sLength = s.length(), tLength = t.length();
        int left = 0, right = 0;
        int min = sLength + 1, count = 0, start = 0;
        for (int i = 0; i < tLength; i++) {
            tNeed[t.charAt(i)]++;
        }
        while (right < sLength) {
            char r = s.charAt(right);
            if (tNeed[r] == 0) {
                right++;
                continue;
            }
            if (sHave[r] < tNeed[r]) {
                count++;
            }
            right++;
            sHave[r]++;
            while (count == tLength) {
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                if (tNeed[l] == 0) {
                    left++;
                    continue;
                }
                if (sHave[l] == tNeed[l]) {
                    count--;
                }
                sHave[l]--;
                left++;
            }
        }
        return min == sLength + 1 ? "" : s.substring(start, start + min);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow1(s, t));
        System.out.println(minWindow2(s, t));
        System.out.println(minWindow3(s, t));
    }
}
