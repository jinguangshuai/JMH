package com.mashibing.jmh.class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/23 - 02 - 23 - 14:59
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class PrintAllSubSequenceTest {

    //打印全部子序列
    public static void print(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        printSubSequence(0, result, chars, " ");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    //打印全部不重复子序列
    public static void printNoRepeat(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        printSubSequence(0, result, chars, " ");
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < result.size(); i++) {
            set.add(result.get(i));
        }
        for(String s:set){
            System.out.println(s);
        }
    }

    public static void printSubSequence(int index, List<String> result, char[] chars, String path) {
        if (index == chars.length) {
            result.add(path);
            return;
        }
        String no = path;
        printSubSequence(index + 1, result, chars, no);
        String yes = path + String.valueOf(chars[index]);
        printSubSequence(index + 1, result, chars, yes);

    }

    public static void main(String[] args) {
        printNoRepeat("abc");
    }

}
