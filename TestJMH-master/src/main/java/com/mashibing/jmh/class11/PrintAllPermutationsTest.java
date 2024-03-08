package com.mashibing.jmh.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/23 - 02 - 23 - 15:12
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class PrintAllPermutationsTest {

    public static void print(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        printAllPermutationsNoRepeat(0, chars, result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    //打印全排列
    public static void printAllPermutations(int i, char[] chars, List<String> result) {
        if (i == chars.length) {
            result.add(String.valueOf(chars));
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            printAllPermutations(i + 1, chars, result);
            swap(chars, i, j);
        }
    }

    //打印不重复的全排列
    public static void printAllPermutationsNoRepeat(int i, char[] chars, List<String> result) {
        if (i == chars.length) {
            result.add(String.valueOf(chars));
        }
        boolean[] flag = new boolean[26];
        for(int j = i; j < chars.length; j++){
            if(!flag[chars[j] - 'a']){
                flag[chars[j] - 'a'] = true;
                swap(chars, i, j);
                printAllPermutationsNoRepeat(i + 1, chars, result);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    public static void main(String[] args) {
        print("aacc");
    }

}
