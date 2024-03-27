package com.mashibing.jmh.leetcodeArrayString;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/22 - 03 - 22 - 15:19
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 *
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 */
public class test21_reverseWords {

    //获取每一个单词存入list，list反向遍历组装结果
    public static String reverseWords(String s) {
        if (null == s) {
            return "";
        }
        StringBuilder element = new StringBuilder();
        List<String> list = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != ' ') {
                element.append(s.charAt(i));
                if (i == n - 1) {
                    list.add(element.toString());
                    element.setLength(0);
                }
            } else if (s.charAt(i) == ' ' && i != 0 && element.length() != 0) {
                list.add(element.toString());
                element = new StringBuilder();
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i != 0) {
                result.append(list.get(i)).append(" ");
            } else {
                result.append(list.get(0));
            }
        }
        return result.toString();
    }

    //队列
    public static String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }
        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            left++;
        }
        //到达最后一个字符串位置之后，while循环结束，left++ = s.length();数组已经越界，但是最后一个字符串未添加
        //因此需要最后一个字符串在while循环外添加
        d.offerFirst(word.toString());
        return String.join(" ", d);
    }


    //新建数组写法 解法一般
    public static String reverseWords3(String s) {
        //源字符数组
        char[] initialArr = s.toCharArray();
        //新字符数组
        char[] newArr = new char[initialArr.length+1];//下面循环添加"单词 "，最终末尾的空格不会返回
        int newArrPos = 0;
        //i来进行整体对源字符数组从后往前遍历
        int i = initialArr.length-1;
        while(i>=0){
            while(i>=0 && initialArr[i] == ' '){i--;}  //跳过空格
            //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
            int right = i;
            while(i>=0 && initialArr[i] != ' '){i--;}
            //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
            for (int j = i+1; j <= right; j++) {
                newArr[newArrPos++] = initialArr[j];
                if(j == right){
                    newArr[newArrPos++] = ' ';//空格
                }
            }
        }
        //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
        if(newArrPos == 0){
            return "";
        }else{
            return new String(newArr,0,newArrPos-1);
        }
    }



    //双指针
    public static String reverseWords4(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    public static void main(String[] args) {
        String s = " hello  world ";
//        System.out.println(reverseWords(s));
//        System.out.println(reverseWords2(s));
//        System.out.println(reverseWords3(s));
        System.out.println(reverseWords4(s));
    }
}
