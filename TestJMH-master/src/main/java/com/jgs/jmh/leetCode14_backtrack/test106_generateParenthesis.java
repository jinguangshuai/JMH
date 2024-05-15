package com.jgs.jmh.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/15 - 05 - 15 - 14:35
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.*;

/**
 * *22. 括号生成
 * * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class test106_generateParenthesis {
    //暴力递归
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack1(0, n, sb, result);
        return result;
    }

    public static void backtrack1(int index, int n, StringBuilder sb, List<String> result) {
        if (index == 2 * n) {
            //验证括号是否合法
            Stack<Character> stack = new Stack<>();
            String s = sb.toString();
            if (s.charAt(0) == ')') return;
            for (int i = 0; i < 2 * n; i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    stack.push(ch);
                } else {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        return;
                    }
                }
            }
            if (stack.isEmpty()) result.add(s);
        } else {
            backtrack1(index + 1, n, sb.append("("), result);
            sb.deleteCharAt(sb.length() - 1);
            backtrack1(index + 1, n, sb.append(")"), result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //暴力递归，优化验证括号的合法性
    public static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack2(0, n, sb, result);
        return result;
    }

    public static void backtrack2(int index, int n, StringBuilder sb, List<String> result) {
        if (index == 2 * n) {
            //验证括号是否合法
            if (valid(sb.toString())) {
                result.add(sb.toString());
                return;
            }
        } else {
            backtrack2(index + 1, n, sb.append("("), result);
            sb.deleteCharAt(sb.length() - 1);
            backtrack2(index + 1, n, sb.append(")"), result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static boolean valid(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    //递归回溯
    //判断括号是否合法可用左括号的数量和右括号的数量进行判断
    //左括号最大可为n，右括号的数量一定要小于左括号的数量，否则无法闭合，
    //直到最后左括号等于右括号的数量
    public List<String> generateParenthesis3(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack3(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack3(List<String> ans, StringBuilder cur, int left, int right, int n) {
        if (cur.length() == 2 * n) {
            ans.add(cur.toString());
            return;
        }
        if (left < n) {
            cur.append('(');
            backtrack3(ans, cur, left + 1, right, n);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (right < left) {
            cur.append(')');
            backtrack3(ans, cur, left, right + 1, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    //方法三：按括号序列的长度递归
    static ArrayList[] cache = new ArrayList[100];

    public static List<String> generateParenthesis4(int n) {
        List<String> list = generate4(n);
        return list;
    }

    public static List<String> generate4(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            //括号序列可以用(a)b表示，其中a与b分别是一个合法的括号序列（可以为空）。
            for (int i = 0; i < n; i++) {
                //枚举generate(i) a所有的可能性
                for (String left : generate4(i)) {
                    //枚举generate(n - 1 - i) b所有的可能性
                    for (String right : generate4(n - 1 - i)) {
                        //遍历a与b所有的可能性进行拼接，即可得到结果
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }
    //比如n=1时为“（）”，那么n=2时，“0（1）2”，有0,1,2三个位置可以插入一个完整的“（）”，
    // 分别得到“（）（）”，“（（））”，以及“（）（）”，去除重复的就得到了n=2时的结果。
    public static List<String> generateParenthesis5(int n) {
        if (n == 1) {
            return Arrays.asList("()");
        }
        Set<String> hs = new HashSet<>();
        for (String s : generateParenthesis5(n - 1)) {
            //是因为每个元素超过s.length() / 2的截取之后，得到的答案都是重复的
            for (int i = 0; i <= s.length() / 2; i++) {
                hs.add(s.substring(0, i) + "()" + s.substring(i, s.length()));
            }
        }
        return new ArrayList(hs);
    }


    public static void main(String[] args) {
        int n = 3;
        List<String> list = generateParenthesis5(n);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
