package com.jgs.jmh.leetCode.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/12 - 04 - 12 - 14:26
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.HashMap;
import java.util.Stack;

/**
 * * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class test52_isValid {
    //hashmap + stack
    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        char[] chars = s.toCharArray();
        int m = chars.length;
        if (m % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            if (map.containsKey(chars[i])) {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != chars[i]) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //模拟
    public static boolean isValid1(String s) {
        char[] chars = new char[s.length() + 1];
        int top = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                chars[++top] = ']';
            } else if (c == '{') {
                chars[++top] = '}';
            } else if (c == '(') {
                chars[++top] = ')';
            } else if (top == 0 || chars[top--] != c) {
                return false;
            }
        }
        return top == 0;
    }

    public static void main(String[] args) {
        String s = "(({}))";
        System.out.println(isValid(s));
        System.out.println(isValid1(s));
    }
}
