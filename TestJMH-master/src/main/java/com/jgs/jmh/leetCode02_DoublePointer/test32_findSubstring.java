package com.jgs.jmh.leetCode02_DoublePointer;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/29 - 03 - 29 - 9:56
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * <p>
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * <p>
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案
 */
public class test32_findSubstring {

    //滑动窗口 + for循环   超时
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || null == words || words.length == 0) {
            return result;
        }
        int m = words.length;
        int n = s.length();
        //拼接字符串长度
        int wordLength = words[0].length();
        int length = m * wordLength;
        if (length > n) {
            return result;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(words[i]);
        }
        for (int i = 0; i < n - length + 1; i = i++) {
            int start = i;
            List<String> medianList = new ArrayList<>();
            medianList.addAll(list);
            for (int j = i; j < i + length; j = j + wordLength) {
                String substring = s.substring(j, wordLength + j);
                if (medianList.contains(substring)) {
                    medianList.remove(substring);
                } else {
                    break;
                }
                start = start + wordLength;
            }
            if (medianList.size() == 0) {
                result.add(i);
            }
        }
        return result;
    }

    //滑动窗口 优化版本
    //naive 的想法是，将 words 里面的单词组合成字符串，然后到 s 里面去找有没有跟它一样的子串。
    // 但是，这样子时间复杂度太高了，因为 words 的组合有 words.length! 种，总复杂度差不多就是 s.length * words.length! 了。
    //那我们反过来想，s 的子串最多只有 s.length 个，那我们只要判断 s 的每个子串是不是刚好由 words 数组里面的所有单词组成的就可以了。
    // 所以说，如果子串是刚好匹配 words 的单词的话，说明子串和 words 组成的字符串是两种组合情况，虽然排序不同，但包含的单词种类和数量是一样的。
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        //注意i的取值范围为单个单词的长度
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            //初始化待处理数据，长度为 m*n
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            //初始化待处理数据 与 字符串数组频次之差
            // 遍历words中的word，对窗口里每个单词计算差值
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                // 差值为0时，移除掉这个word
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            //m数组长度  n为数组单个单词的长度  ls为待处理字符串的长度
            //开始滑动窗口  start < ls - m * n + 1 前边已经截取 m*n长度的单词，后续须每次循环保持m*n长度的单词
            for (int start = i; start < ls - m * n + 1; start += n) {
                //保证右侧不重复滑入相同的单词 start = start + n，
                //如果start = i，以i=0举例，则word会截取6~9下标的单词，重复滑入
                if (start != i) {
                    //右边的单词滑进来
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    //左边的单词滑出去
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                // 窗口匹配的单词数等于words中对应的单词数
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }


    public static List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int m = words.length;
        int n = words[0].length();
        int ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) - 1);
                if (map.get(word) == 0) {
                    map.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    if (map.get(word) == 0) {
                        map.remove(word);
                    }
                    word = s.substring(start - n, start);
                    map.put(word, map.getOrDefault(word, 0) - 1);
                    if (map.get(word) == 0) {
                        map.remove(word);
                    }
                }
                if (map.isEmpty()) {
                    result.add(start);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        System.out.println(findSubstring2(s, words));
        System.out.println(findSubstring3(s, words));
    }

}
