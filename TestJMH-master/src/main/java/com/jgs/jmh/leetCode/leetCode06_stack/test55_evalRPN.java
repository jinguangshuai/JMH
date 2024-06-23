package com.jgs.jmh.leetCode.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/15 - 04 - 15 - 14:51
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.Stack;

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * <p>
 * 注意：
 * <p>
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 */
public class test55_evalRPN {
    //栈计算
    public static int evalRPN(String[] tokens) {
        if (null == tokens || tokens.length == 0) {
            throw new RuntimeException();
        }
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            if (Character.isDigit(s.charAt(0)) || s.length() > 1) {
                stack.push(s);
            } else {
                String p1 = stack.pop();
                String p2 = stack.pop();
                int element = getResult(p1, p2, s);
                stack.push(String.valueOf(element));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static int getResult(String p1, String p2, String c) {
        int num1 = Integer.parseInt(p1);
        int num2 = Integer.parseInt(p2);
        if (c.equals("/")) {
            return num2 / num1;
        } else if (c.equals("*")) {
            return num2 * num1;
        } else if (c.equals("+")) {
            return num2 + num1;
        } else {
            return num2 - num1;
        }
    }

    //数组模拟
    public static int evalRPN1(String[] tokens) {
        int n = tokens.length;
        int[] arr = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    arr[index] += arr[index + 1];
                    break;
                case "-":
                    index--;
                    arr[index] -= arr[index + 1];
                    break;
                case "*":
                    index--;
                    arr[index] *= arr[index + 1];
                    break;
                case "/":
                    index--;
                    arr[index] /= arr[index + 1];
                    break;
                default:
                    index++;
                    arr[index] = Integer.parseInt(token);
            }
        }
        return arr[index];
    }







    public static void main(String[] args) {
        String[] str = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(str));
        System.out.println("----------------------");
    }
}
