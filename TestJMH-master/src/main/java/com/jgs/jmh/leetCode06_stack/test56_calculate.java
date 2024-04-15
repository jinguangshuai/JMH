package com.jgs.jmh.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/15 - 04 - 15 - 16:19
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.Stack;

/**
 * * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * s = "(1+(4+5+2)-3)+(6+8)"
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 */
public class test56_calculate {

    public static int calculate(String s) {
        //去除空格
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                sb.append(s.charAt(i));
            }
        }
        char[] chars = sb.toString().toCharArray();
        int m = chars.length;
        Stack<Integer> digit = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        for (int i = 0; i < m; i++) {
            char c = chars[i];
            switch (c) {
                case '(':
                    symbol.push('(');
                    break;
                case ')':
                    Character pop = symbol.pop();
                    while (!pop.equals("(")) {
                        Integer pop1 = digit.pop();
                        Integer pop2 = digit.pop();
                        if (pop.equals("+")) {
                            digit.push(pop2 + pop1);
                        } else if (pop.equals("-")) {
                            digit.push(pop2 - pop1);
                        }
                        pop = symbol.pop();
                    }
                    break;
                case '+':
                    if (i + 1 < m) {
                        if (chars[i + 1] == '(') {
                            symbol.push('+');
                        } else {
                            Integer pop1 = digit.pop();
                            Integer pop2 = getDigit(sb.toString(), i, m, chars);
                            digit.push(pop1 + pop2);
                        }
                    }
                    break;
                case '-':
                    if (i + 1 < m) {
                        if (chars[i + 1] == '(') {
                            symbol.push('-');
                        } else {
                            Integer pop1 = digit.pop();
                            Integer pop2 = getDigit(s, i, m, chars);
                            digit.push(pop1 - pop2);
                        }
                    }
                default:
                    digit.push(getDigit(sb.toString(), i, m, chars));
            }
        }
        return digit.pop();
    }

    public static int getDigit(String s, int start, int length, char[] chars) {
        //获取数字
        while (start + 1 < length && Character.isDigit(chars[start + 1])) {
            start++;
        }
        return Integer.parseInt(s.substring(start+1, start + 1));

    }

    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(calculate(s));
        System.out.println("----------------------");
    }

}
