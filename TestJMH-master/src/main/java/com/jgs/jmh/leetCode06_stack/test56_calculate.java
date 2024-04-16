package com.jgs.jmh.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/15 - 04 - 15 - 16:19
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * s = "(1+(4+5+2)-3)+(6+8)"
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 */
public class test56_calculate {

    //nums ： 存放所有的数字
    //ops ：存放所有的数字以外的操作，+/- 也看做是一种操作
    //适用于加减法
    public static int calculate(String s) {
        // 存放所有的数字
        Stack<Integer> digit = new Stack<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        digit.push(0);
        // 存放所有的操作，包括 +/-
        Stack<Character> symbol = new Stack<>();
        //去除空格
        s = s.replaceAll(" ", "");
        char[] chars = s.toCharArray();
        int m = chars.length;
        for (int i = 0; i < m; i++) {
            char c = chars[i];
            if (c == '(') {
                symbol.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!symbol.isEmpty()) {
                    char op = symbol.peek();
                    if (op != '(') {
                        calc(digit, symbol);
                    } else {
                        symbol.pop();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(c)) {
                    //取出当前数字
                    int num = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < m && Character.isDigit(chars[j])) {
                        num = num * 10 + (int) (chars[j] - '0');
                        j++;
                    }
                    digit.push(num);
                    i = j - 1;
                } else {
                    //如果当前字符为符号位，且前一位仍为符号位，则添加0
                    if (i > 0 && (chars[i - 1] == '(' || chars[i - 1] == '+' || chars[i - 1] == '-')) {
                        digit.push(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!symbol.isEmpty() && symbol.peek() != '(') {
                        calc(digit, symbol);
                    }
                    symbol.push(c);
                }
            }
        }
        while (!symbol.isEmpty()) {
            calc(digit, symbol);
        }
        return digit.pop();
    }

    public static void calc(Stack<Integer> digit, Stack<Character> sympol) {
        if (digit.isEmpty() || digit.size() < 2) return;
        if (sympol.isEmpty()) return;
        int b = digit.pop(), a = digit.pop();
        char op = sympol.pop();
        digit.push(op == '+' ? a + b : a - b);
    }


    //官方解法  括号展开 + 栈
    //因此，我们考虑使用一个取值为 {−1,+1} 的整数 sign代表「当前」的符号。根据括号表达式的性质，它的取值：
    public static int calculate1(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;
        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            //去除括号
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s));
        System.out.println(calculate1(s));
        System.out.println("----------------------");
    }

}
