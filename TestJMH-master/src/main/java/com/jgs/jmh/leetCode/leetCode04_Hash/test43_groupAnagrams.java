package com.jgs.jmh.leetCode.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/9 - 04 - 09 - 15:13
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */
public class test43_groupAnagrams {

    //int数组存储每个字符的数量，表计算
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (null == strs || strs.length == 0) {
            return result;
        }
        int m = strs.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] arr = new int[26];
            char[] chars = strs[i].toCharArray();
            for (char c : chars) {
                arr[c - 'a']++;
            }
            list.add(arr);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> element = new ArrayList<>();
            if (!set.contains(i)) {
                set.add(i);
                element.add(strs[i]);
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (!set.contains(j)) {
                    int[] arri = list.get(i);
                    int[] arrj = list.get(j);
                    boolean flag = true;
                    for (int k = 0; k < arri.length; k++) {
                        if (arri[k] != arrj[k]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        set.add(j);
                        element.add(strs[j]);
                    }
                }
            }
            if (!element.isEmpty()) {
                result.add(element);
            }
        }
        return result;
    }

    // 对每个字符进行排序，然后判断是否相等
    public static List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (null == strs || strs.length == 0) {
            return result;
        }
        int m = strs.length;
        List<char[]> newStrs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            newStrs.add(chars);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            List<String> element = new ArrayList<>();
            if (!set.contains(i)) {
                set.add(i);
                element.add(strs[i]);
            }
            for (int j = i + 1; j < m; j++) {
                if (!set.contains(j)) {
                    if (Arrays.equals(newStrs.get(i), newStrs.get(j))) {
                        set.add(j);
                        element.add(strs[j]);
                    }
                }
            }
            if (!element.isEmpty()) {
                result.add(element);
            }
        }
        return result;
    }

    //字符排序
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //利用int数组进行排序
    public static List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            int[] arr = new int[26];
            for (char c : chars) {
                arr[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (arr[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(arr[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(str);
        List<List<String>> lists1 = groupAnagrams1(str);
        List<List<String>> lists2 = groupAnagrams2(str);
        List<List<String>> lists3 = groupAnagrams3(str);
        System.out.println(lists);
        System.out.println(lists1);
        System.out.println(lists2);
        System.out.println(lists3);

    }


}
