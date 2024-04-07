package com.jgs.jmh.leetcode01_ArrayString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/26 - 03 - 26 - 10:29
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。
 * * 必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 */
public class test24_fullJustify {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (null == words || words.length == 0) {
            return result;
        }
        int m = words.length;
        for (int i = 0; i < m; i++) {
            StringBuilder str = new StringBuilder();
            int length = 0;
            //处理每一行的单词，并且每个单词后边加“ ”，最后一个单词不加“ ”，记录当前的长度
            while (length < maxWidth && i < m) {
                length += words[i].length();
                str.append(words[i]).append(" ");
                length++;
                i++;
                if (i >= m || (length + words[i].length()) > maxWidth) {
                    i = i - 1;
                    str.delete(str.length() - 1, str.length());
                    length--;
                    break;
                }
            }
            if (length <= maxWidth && str.length() != 0) {
                String[] split = str.toString().split(" ");
                // 当前单词等于1 直接拼接空格，左对齐
                if (split.length == 1) {
                    for (int j = 0; j < maxWidth - length; j++) {
                        str.append(" ");
                    }
                    result.add(str.toString());
                } else {
                    // 计算每个单词间的空格
                    int elmentEmpty = maxWidth;
                    for (int j = 0; j < split.length; j++) {
                        elmentEmpty -= split[j].length();
                    }
                    //计算每个单词之间可插入的空格
                    int eneryElmentEmptyNums = elmentEmpty / (split.length - 1);
                    int[] num = new int[split.length - 1];
                    Arrays.fill(num, eneryElmentEmptyNums);
                    //如果多出空格则应该在前  （总空格 % 空格数） 个空格间每个多加一个空格
                    if (eneryElmentEmptyNums * (split.length - 1) < elmentEmpty) {
                        for (int j = 0; j < elmentEmpty % (split.length - 1); j++) {
                            num[j]++;
                        }
                    }
                    StringBuilder elment = new StringBuilder();
                    //当为最后一行时，左对齐
                    if(i == m -1){
                        for (int j = 0; j < split.length - 1; j++) {
                            elment.append(split[j]).append(" ");
                        }
                        elment.append(split[split.length - 1]);
                        int remainEmpty = maxWidth - elment.length();
                        if(remainEmpty > 0){
                            for (int j = 0; j < remainEmpty; j++) {
                                elment.append(" ");
                            }
                        }
                        result.add(elment.toString());
                    }else {
                        //既不是第一行也不是最后一行，且存在多个单词的情况
                        for (int j = 0; j < split.length; j++) {
                            elment.append(split[j]);
                            if (j != split.length - 1) {
                                for (int k = 0; k < num[j]; k++) {
                                    elment.append(" ");
                                }
                            }
                        }
                        result.add(elment.toString());
                    }
                }
            }
        }
        return result;
    }


    public static List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public static String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public static StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }


    public static void main(String[] args) {
        String[] words = {"My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."};
        List<String> list = fullJustify(words, 20);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
