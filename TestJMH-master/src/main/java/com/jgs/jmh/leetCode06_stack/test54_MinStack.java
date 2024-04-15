package com.jgs.jmh.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/15 - 04 - 15 - 14:17
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * <p>
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class test54_MinStack {

    Deque<Integer> stack;
    Deque<Integer> help;

    public test54_MinStack() {
        stack = new LinkedList<>();
        help = new LinkedList<>();
        help.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        help.push(Math.min(stack.peek(), help.peek()));
    }

    public void pop() {
        stack.pop();
        help.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return help.peek();
    }

}
