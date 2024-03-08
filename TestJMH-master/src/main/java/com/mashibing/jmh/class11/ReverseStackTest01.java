package com.mashibing.jmh.class11;

import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/21 - 02 - 21 - 15:59
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class ReverseStackTest01 {

    //翻转栈中元素逆序重新压入
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getStack(stack);
        reverse(stack);
        stack.push(i);

    }

    //每次获取栈最底层的元素，并将其他元素原顺序放入栈
    public static int getStack(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getStack(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
