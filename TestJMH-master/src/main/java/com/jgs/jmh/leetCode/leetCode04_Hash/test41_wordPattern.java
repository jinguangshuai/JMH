package com.jgs.jmh.leetCode.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/9 - 04 - 09 - 14:26
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 */
public class test41_wordPattern {
    public static boolean wordPattern(String pattern, String s) {
        int m = pattern.length();
        String[] sArray = s.split(" ");
        int n = sArray.length;
        if (m != n)
            return false;
        HashMap<Character, String> patternTosMap = new HashMap<>();
        HashMap<String, Character> sToPatternmap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char pChar = pattern.charAt(i);
            String sstring = sArray[i];
            if ((patternTosMap.containsKey(pChar) && !patternTosMap.get(pChar).equals(sstring))
                    || (sToPatternmap.containsKey(sstring) && sToPatternmap.get(sstring) != pChar)) {
                return false;
            }
            patternTosMap.put(pChar, sstring);
            sToPatternmap.put(sstring, pChar);
        }
        return true;
    }

    //根据map put的结果进行判断
    public static boolean wordPattern1(String pattern, String str) {
        String[] words = str.split(" ");
        //字符和单词是互相映射，数量必须相等
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < words.length; i++) {
            /*
                如果key不存在，插入成功，返回null；如果key存在，返回之前对应的value。

                以pattern = "abba", str = "dog cat cat dog"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("dog",3)返回0，两者相等，
                结果为 true。

                以pattern = "abba", str = "dog cat cat fish"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("fish",3)返回null，两者不相等，
                结果为 false。
            */
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd";
        String s = "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";
        System.out.println(wordPattern(pattern, s));
        System.out.println(wordPattern1(pattern, s));
    }
}
