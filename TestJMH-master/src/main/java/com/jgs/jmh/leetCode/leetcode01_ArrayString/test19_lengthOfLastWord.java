package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/22 - 03 - 22 - 11:09
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大
 * 子字符串
 * 。
 */
public class test19_lengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        String[] s1 = s.split(" ");
        return s1[s1.length-1].length();
    }

    public static int lengthOfLastWord2(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for(int i = chars.length-1; i>= 0;i--){
            if(chars[i] != ' '){
                result++;
            }
            if(chars[i] == ' ' && result != 0){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
    }
}
