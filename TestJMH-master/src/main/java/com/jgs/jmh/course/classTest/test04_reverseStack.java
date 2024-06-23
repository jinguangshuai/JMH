package com.jgs.jmh.course.classTest;

import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/6 - 03 - 06 - 14:27
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test04_reverseStack {
    public static int getLowNum(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = getLowNum(stack);
        stack.push(result);
        return last;
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int result = getLowNum(stack);
        reverse(stack);
        stack.push(result);
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
