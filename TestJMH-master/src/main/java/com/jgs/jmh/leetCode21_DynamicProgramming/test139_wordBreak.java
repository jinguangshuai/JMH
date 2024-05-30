package com.jgs.jmh.leetCode21_DynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/29 - 05 - 29 - 8:54
 * @Description:com.jgs.jmh.leetCode21_DynamicProgramming
 * @version:1.0
 */

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class test139_wordBreak {

    //暴力递归超时
    static boolean flag = false;
    public static boolean wordBreak1(String s, List<String> wordDict) {
        process(0, s, wordDict);
        return flag;
    }
    public static void process(int index, String s, List<String> wordDict) {
        if (index > s.length()) {
            return;
        }
        if (index == s.length()) {
            flag = true;
            return;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            String element = wordDict.get(i);
            if (index + element.length() > s.length()) {
                continue;
            }
            if (s.substring(index, index + element.length()).equals(element)) {
                process(index + element.length(), s, wordDict);
            }
        }
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //依次遍历待匹配的单词所有字符
        //以leetCode
        //i = 4 j从0到3遍历
        //遍历leet(0,4) eet(1,4) et(2,4) t(3,4) 发现存在（0-4）leet dp[4] = true
        // i = 8的时候，
        //遍历leetcode(0,8) eetcode(1,8) etcode(2,8) tcode(3,8) code(4-8)存在，且dp[4]为true，满足答案，dp[8] = true
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && words.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        String s = "leetcode";
//        String s = "abcd";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
//        list.add("a");
//        list.add("abc");
//        list.add("b");
//        list.add("cd");
//        list.add("cat");
        System.out.println(wordBreak2(s, list));
    }
}
